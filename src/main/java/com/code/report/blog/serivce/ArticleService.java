package com.code.report.blog.serivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.report.blog.controller.vo.ArticleVO;
import com.code.report.blog.infra.dto.ArticleDTO;

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

    IPage<ArticleVO> page(Page page);

    ArticleDTO queryById(Long id);
}
