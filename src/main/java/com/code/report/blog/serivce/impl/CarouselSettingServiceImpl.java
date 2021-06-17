package com.code.report.blog.serivce.impl;

import com.code.report.blog.controller.vo.FilterVO;
import com.code.report.blog.infra.dto.CarouselSettingDTO;
import com.code.report.blog.infra.exception.CommonException;
import com.code.report.blog.infra.mapper.CarouselSettingMapper;
import com.code.report.blog.serivce.CarouselSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-04-14 11:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CarouselSettingServiceImpl implements CarouselSettingService {
    @Autowired
    private CarouselSettingMapper carouselSettingMapper;

    @Override
    public CarouselSettingDTO create(CarouselSettingDTO carouselSettingDTO) {
        if (ObjectUtils.isEmpty(carouselSettingDTO)) {
            throw new CommonException("error.carousel.setting.null");
        }
        if (carouselSettingMapper.insertSelective(carouselSettingDTO) != 1) {
            throw new CommonException("error.carousel.setting.insert");
        }
        return carouselSettingDTO;
    }

    @Override
    public CarouselSettingDTO update(CarouselSettingDTO carouselSettingDTO) {
        if (ObjectUtils.isEmpty(carouselSettingDTO)) {
            throw new CommonException("error.carousel.setting.null");
        }
        if (ObjectUtils.isEmpty(carouselSettingDTO.getId())) {
            return create(carouselSettingDTO);
        }
        if (carouselSettingMapper.updateByPrimaryKey(carouselSettingDTO) != 1) {
            throw new CommonException("error.carousel.setting.insert");
        }
        return carouselSettingDTO;
    }

    @Override
    public void delete(Long id) {
        carouselSettingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CarouselSettingDTO> list(FilterVO filterVO) {
        return carouselSettingMapper.selectByFileter(filterVO);
    }

    @Override
    public CarouselSettingDTO queryById(Long id) {
        return carouselSettingMapper.selectByPrimaryKey(id);
    }
}
