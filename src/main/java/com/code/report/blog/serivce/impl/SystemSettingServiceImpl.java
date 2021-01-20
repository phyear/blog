package com.code.report.blog.serivce.impl;

import com.code.report.blog.infra.dto.SystemSettingDTO;
import com.code.report.blog.infra.mapper.SystemSettingMapper;
import com.code.report.blog.serivce.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaotianxin
 * @date 2021-01-20 15:11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemSettingServiceImpl implements SystemSettingService {
    @Autowired
    private SystemSettingMapper systemSettingMapper;

    @Override
    public SystemSettingDTO selectSetting() {
        return systemSettingMapper.selectSettingA();
    }
}
