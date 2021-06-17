package com.code.report.blog.serivce.impl;

import com.code.report.blog.serivce.UserService;
import com.code.report.blog.infra.dto.UserDTO;
import com.code.report.blog.infra.exception.CommonException;
import com.code.report.blog.infra.mapper.UserMapper;
import com.code.report.blog.infra.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author zhaotianxin
 * @date 2021-02-07 15:47
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(UserDTO userDTO) {
        if (ObjectUtils.isEmpty(userDTO.getLoginName())) {
            throw new CommonException("error.login.name.is.null");
        }
        if (ObjectUtils.isEmpty(userDTO.getPassword())) {
            throw new CommonException("error.password.is.null");
        }
        UserDTO userDTO1 = userMapper.selectByLoginName(userDTO.getLoginName());
        if(ObjectUtils.isEmpty(userDTO1)){
            throw new CommonException("error.user.not.exist");
        }
        if(!Objects.equals(userDTO1.getPassword(), EncryptUtil.encryptSHA(userDTO.getPassword()))){
            throw new CommonException("error.account.password.illegal");
        }
        // 生成JWT Token
        String jwt = JWTTokeUtil.createJWT(userDTO1, new HashMap<>());
        // 存入redis
        SpringUtil.getBean(RedisUtil.class).set("token:" + userDTO1.getId(),jwt);
        return jwt;
    }
}
