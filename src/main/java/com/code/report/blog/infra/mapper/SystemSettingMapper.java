package com.code.report.blog.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.report.blog.infra.dto.SystemSettingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhaotianxin
 * @date 2021-01-20 15:07
 */
@Mapper
public interface SystemSettingMapper extends BaseMapper<SystemSettingDTO> {
    @Select("select * from system_setting limit 1")
    SystemSettingDTO selectSettingA();
}
