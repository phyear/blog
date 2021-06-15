package com.code.report.blog.serivce;

import com.code.report.blog.controller.vo.FilterVO;
import com.code.report.blog.infra.dto.CarouselSettingDTO;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-04-14 11:34
 */
public interface CarouselSettingService {

    CarouselSettingDTO create(CarouselSettingDTO carouselSettingDTO);

    CarouselSettingDTO update(CarouselSettingDTO carouselSettingDTO);

    void delete(Long id);

    List<CarouselSettingDTO> list(FilterVO filterVO);

    CarouselSettingDTO queryById(Long id);
}
