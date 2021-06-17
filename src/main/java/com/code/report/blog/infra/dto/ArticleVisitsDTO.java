package com.code.report.blog.infra.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author zhaotianxin
 * @date 2021-04-15 19:52
 */
@Table(name = "tb_article_visits")
public class ArticleVisitsDTO {
    @Id
    @GeneratedValue(generator  = "JDBC")
    private Long id;

    private Long articleId;

    private Long visitsCount;

    private Long likeCount;

    private Date creationDate;

    private Date lastUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount(Long visitsCount) {
        this.visitsCount = visitsCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
