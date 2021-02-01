package com.code.report.blog.controller;

import com.code.report.blog.infra.dto.ArticleDTO;
import com.code.report.blog.serivce.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaotianxin
 * @date 2021-01-19 20:32
 */
@RestController
@RequestMapping("/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> list(){
        return ResponseEntity.ok(articleService.selectAll());
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> create(@RequestBody ArticleDTO articleDTO){
        return ResponseEntity.ok(articleService.save(articleDTO));
    }
}
