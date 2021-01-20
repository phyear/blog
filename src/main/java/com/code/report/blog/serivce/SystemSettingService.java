package com.code.report.blog.serivce;

import com.code.report.blog.infra.dto.SystemSettingDTO;

/**
 * @author zhaotianxin
 * @date 2021-01-20 15:10
 */
public interface SystemSettingService {
    SystemSettingDTO selectSetting();
}
