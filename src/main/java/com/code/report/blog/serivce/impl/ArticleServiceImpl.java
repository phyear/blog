package com.code.report.blog.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.code.report.blog.infra.dto.ArticleDTO;
import com.code.report.blog.infra.exception.CommonException;
import com.code.report.blog.infra.mapper.ArticleMapper;
import com.code.report.blog.serivce.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<ArticleDTO> selectAll() {
        QueryWrapper<ArticleDTO> queryWrapper = new QueryWrapper<>();
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        return baseCreate(articleDTO);
    }

    private ArticleDTO baseCreate(ArticleDTO articleDTO){
        if (articleMapper.insert(articleDTO) != 1) {
            throw new CommonException("error.insert.article");
        }
        return articleDTO;
    }

    @Override
    public ArticleDTO update(ArticleDTO articleDTO) {
        return baseUpdate(articleDTO);
    }

    private ArticleDTO baseUpdate(ArticleDTO articleDTO){
        if (articleMapper.updateById(articleDTO) != 1) {
            throw new CommonException("error.update.article");
        }
        return articleDTO;
    }


    @Override
    public void delete(Long id) {
        articleMapper.deleteById(id);
    }
}
