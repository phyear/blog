package com.code.report.blog.infra.dto;

import java.util.Date;

/**
 * @author zhaotianxin
 * @date 2021-04-14 10:47
 */
public abstract class CommonDTO  {
   private Date creationDate;

   private Date lastUpdateDate;

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
