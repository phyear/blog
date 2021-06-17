package com.code.report.blog.infra.mapper;

import com.code.report.blog.infra.dto.ArticleDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:14
 */
public interface ArticleMapper extends Mapper<ArticleDTO> {

    List<ArticleDTO> selectHotArticle();

    List<ArticleDTO> selectAllArticle(@Param("odesc") Boolean odesc);
}
