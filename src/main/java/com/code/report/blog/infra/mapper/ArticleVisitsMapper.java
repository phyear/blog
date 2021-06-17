package com.code.report.blog.infra.mapper;

import com.code.report.blog.infra.dto.ArticleVisitsDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhaotianxin
 * @date 2021-04-15 19:59
 */
public interface ArticleVisitsMapper extends Mapper<ArticleVisitsDTO> {
    @Select("select visits_count from tb_article_visits where article_id = #{articleId}")
    Long visitsCount( @Param("articleId") Long articleId);

    @Select("select like_count from tb_article_visits where article_id = #{articleId}")
    Long likeCount( @Param("articleId") Long articleId);

    @Update("update tb_article_visits set visits_count = #{count} where id = #{id} and visits_count <= #{count}")
    int update( @Param("id") Long id,  @Param("count") Long count);
}
