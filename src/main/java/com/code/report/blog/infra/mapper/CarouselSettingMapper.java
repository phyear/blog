package com.code.report.blog.infra.mapper;

import com.code.report.blog.controller.vo.FilterVO;
import com.code.report.blog.infra.dto.CarouselSettingDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-04-14 11:33
 */
public interface CarouselSettingMapper extends Mapper<CarouselSettingDTO> {

    List<CarouselSettingDTO> selectByFileter(@Param("filterVO") FilterVO filterVO);
}
