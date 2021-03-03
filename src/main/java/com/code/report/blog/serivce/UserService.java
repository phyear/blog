package com.code.report.blog.serivce;

import com.code.report.blog.infra.dto.UserDTO;

/**
 * @author zhaotianxin
 * @date 2021-02-07 15:46
 */
public interface UserService {
    String login(UserDTO userDTO);
}
