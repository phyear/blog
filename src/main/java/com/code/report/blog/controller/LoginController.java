package com.code.report.blog.controller;

import com.code.report.blog.infra.dto.UserDTO;
import com.code.report.blog.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaotianxin
 * @date 2021-02-07 15:44
 */
@RestController
@RequestMapping("/v1")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.login(userDTO));
    }
}
