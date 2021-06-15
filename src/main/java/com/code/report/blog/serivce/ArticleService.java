package com.code.report.blog.serivce;

import com.code.report.blog.controller.vo.ArticleVO;
import com.code.report.blog.infra.dto.ArticleDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:17
 */
public interface ArticleService {
    List<ArticleDTO> selectAll();

    ArticleDTO save(ArticleDTO articleDTO);

    ArticleDTO update(ArticleDTO articleDTO);

    void delete(Long id);

    PageInfo<ArticleVO> page(int page, int size);

    ArticleDTO queryById(Long id);

    List<ArticleDTO> hotArticle();

    ArticleVO query(Long id);
}
