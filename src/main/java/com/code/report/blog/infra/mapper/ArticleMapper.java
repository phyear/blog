package com.code.report.blog.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.report.blog.infra.dto.ArticleDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:14
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDTO> {
}
