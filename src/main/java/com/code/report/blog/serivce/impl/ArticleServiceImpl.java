package com.code.report.blog.serivce.impl;

import com.code.report.blog.controller.vo.ArticleVO;
import com.code.report.blog.infra.dto.ArticleDTO;
import com.code.report.blog.infra.dto.ArticleVisitsDTO;
import com.code.report.blog.infra.exception.CommonException;
import com.code.report.blog.infra.mapper.ArticleMapper;
import com.code.report.blog.infra.mapper.ArticleVisitsMapper;
import com.code.report.blog.infra.util.RedisCountUtils;
import com.code.report.blog.serivce.ArticleService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArticleVisitsMapper articleVisitsMapper;

    private static final String REDIS_KEY_VISITS = "article:visits:";
    private static final String REDIS_KEY_LIKE = "article:visits:";

    @Override
    public List<ArticleDTO> selectAll() {
        return articleMapper.selectAll();
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        if (ObjectUtils.isEmpty(articleDTO.getId())) {
            articleDTO.setType("ck");
            return baseCreate(articleDTO);
        } else {
            return baseUpdate(articleDTO);
        }
    }

    private ArticleDTO baseCreate(ArticleDTO articleDTO) {
        if (articleMapper.insertSelective(articleDTO) != 1) {
            throw new CommonException("error.insert.article");
        }
        return articleDTO;
    }

    @Override
    public ArticleDTO update(ArticleDTO articleDTO) {
        return baseUpdate(articleDTO);
    }

    private ArticleDTO baseUpdate(ArticleDTO articleDTO) {
        if (articleMapper.updateByPrimaryKeySelective(articleDTO) != 1) {
            throw new CommonException("error.update.article");
        }
        return articleDTO;
    }


    @Override
    public void delete(Long id) {
        if (articleMapper.deleteByPrimaryKey(id) != 1) {
            throw new CommonException("error.delete.article");
        }
    }

    @Override
    public PageInfo<ArticleVO> page(int page, int size) {
        PageInfo<ArticleDTO> pageInfo = PageMethod.startPage(page, size).doSelectPageInfo(() -> articleMapper.selectAllArticle(true));
        if (CollectionUtils.isEmpty(pageInfo.getList())) {
            return new PageInfo<>();
        }
        PageInfo<ArticleVO> map = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, map);
        List<ArticleDTO> result = pageInfo.getList();
        List<ArticleVO> articleVOS = modelMapper.map(result, new TypeToken<List<ArticleVO>>() {
        }.getType());
        map.setList(articleVOS);
        return map;
    }

    @Override
    public ArticleDTO queryById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ArticleDTO> hotArticle() {
        return articleMapper.selectHotArticle();
    }

    @Override
    public ArticleVO query(Long id) {
        ArticleDTO articleDTO = queryById(id);
        if (ObjectUtils.isEmpty(articleDTO)) {
            return null;
        }
        Long count = RedisCountUtils.getCount(REDIS_KEY_VISITS, id);
        Long likeCount = RedisCountUtils.notAutoIncrement(REDIS_KEY_LIKE, id);
        ArticleVO map = modelMapper.map(articleDTO, ArticleVO.class);
        map.setLikeCount(count);
        map.setVisitsCount(likeCount);
        updateCount(id, count, likeCount);
        return map;
    }

    private void updateCount(Long articleId, Long count, Long likeCount) {
        // 更新访问量
        ArticleVisitsDTO articleVisitsDTO = new ArticleVisitsDTO();
        articleVisitsDTO.setArticleId(articleId);
        ArticleVisitsDTO visitsDTO = articleVisitsMapper.selectOne(articleVisitsDTO);
        articleVisitsDTO.setLikeCount(likeCount);
        articleVisitsDTO.setVisitsCount(count);
        if (ObjectUtils.isEmpty(visitsDTO)) {
            articleVisitsMapper.insertSelective(articleVisitsDTO);
        } else {
            articleVisitsDTO.setId(visitsDTO.getId());
            if (articleVisitsMapper.update(articleId, count) != 1) {
                throw new CommonException("error.update.visits.count");
            }
        }

    }
}
