package com.code.report.blog.controller;

import com.code.report.blog.infra.dto.SystemSettingDTO;
import com.code.report.blog.serivce.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaotianxin
 * @date 2021-01-20 15:09
 */
@RestController
@RequestMapping("/v1/system_setting")
public class SystemSettingController {
    @Autowired
    private SystemSettingService settingService;

    @GetMapping
    public ResponseEntity<SystemSettingDTO> list(){
        return ResponseEntity.ok(settingService.selectSetting());
    }
}
