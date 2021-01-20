package com.code.report.blog.infra.dto;
import com.baomidou.mybatisplus.annotation.*;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:11
 */
@TableName(value = "fd_user")
public class UserDTO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String loginName;

    private String name;

    private String password;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long versionNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }
}
