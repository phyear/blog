package com.code.report.blog.infra.dto;


import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:11
 */
@TableName(value = "tb_article")
public class ArticleDTO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long versionNumber;

    private Date createdDate;

    private Date lastUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }
}
