package com.code.report.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.report.blog.annotation.VerifyToken;
import com.code.report.blog.controller.vo.ArticleVO;
import com.code.report.blog.infra.dto.ArticleDTO;
import com.code.report.blog.serivce.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @VerifyToken
    @GetMapping("/page")
    public ResponseEntity<IPage<ArticleVO>> pageList(Page page){
        return ResponseEntity.ok(articleService.page(page));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity pageList(@PathVariable(name = "id") Long id){
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @VerifyToken
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> queryById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(articleService.queryById(id), HttpStatus.OK);
    }
}
