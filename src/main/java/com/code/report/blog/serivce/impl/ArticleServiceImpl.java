package com.code.report.blog.serivce.impl;

import com.code.report.blog.controller.vo.ArticleVO;
import com.code.report.blog.infra.dto.ArticleDTO;
import com.code.report.blog.infra.exception.CommonException;
import com.code.report.blog.infra.mapper.ArticleMapper;
import com.code.report.blog.serivce.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<ArticleDTO> selectAll() {
        return articleMapper.selectAll();
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        if (ObjectUtils.isEmpty(articleDTO.getId())) {
            articleDTO.setVersionNumber(1L);
            return baseCreate(articleDTO);
        } else {
            return baseUpdate(articleDTO);
        }
    }

    private ArticleDTO baseCreate(ArticleDTO articleDTO) {
        if (articleMapper.insert(articleDTO) != 1) {
            throw new CommonException("error.insert.article");
        }
        return articleDTO;
    }

    @Override
    public ArticleDTO update(ArticleDTO articleDTO) {
        return baseUpdate(articleDTO);
    }

    private ArticleDTO baseUpdate(ArticleDTO articleDTO) {
        if (articleMapper.updateByPrimaryKey(articleDTO) != 1) {
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
        PageInfo<ArticleDTO> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(() -> articleMapper.selectAll());
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
}
