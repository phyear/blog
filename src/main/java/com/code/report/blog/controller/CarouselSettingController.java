package com.code.report.blog.controller;

import com.code.report.blog.annotation.VerifyToken;
import com.code.report.blog.controller.vo.FilterVO;
import com.code.report.blog.infra.dto.ArticleDTO;
import com.code.report.blog.infra.dto.CarouselSettingDTO;
import com.code.report.blog.serivce.CarouselSettingService;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-04-14 11:50
 */
@Controller
@RequestMapping("/v1/carousel_setting")
public class CarouselSettingController {

    @Autowired
    private CarouselSettingService carouselSettingService;

    @PostMapping
    @VerifyToken
    public ResponseEntity<CarouselSettingDTO> create(@RequestBody CarouselSettingDTO carouselSettingDTO){
        return ResponseEntity.ok(carouselSettingService.create(carouselSettingDTO));
    }

    @PutMapping
    @VerifyToken
    public ResponseEntity<CarouselSettingDTO> update(
                                                     @RequestBody CarouselSettingDTO carouselSettingDTO){
        return ResponseEntity.ok(carouselSettingService.update( carouselSettingDTO));
    }

    @DeleteMapping
    @VerifyToken
    public ResponseEntity delete(Long id){
        carouselSettingService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/list")
    public ResponseEntity<List<CarouselSettingDTO>> list(@RequestBody FilterVO filterVO){
        return ResponseEntity.ok(carouselSettingService.list(filterVO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarouselSettingDTO> queryById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(carouselSettingService.queryById(id));
    }
}
