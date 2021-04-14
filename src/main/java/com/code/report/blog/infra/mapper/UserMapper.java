package com.code.report.blog.infra.mapper;

import com.code.report.blog.infra.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:14
 */
public interface UserMapper extends Mapper<UserDTO> {
    UserDTO selectByLoginName(@Param("name") String name);
}
