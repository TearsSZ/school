package com.dlb.controller;


import com.dlb.pojo.Book;
import com.dlb.pojo.vo.RespEntity;
import com.dlb.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
@Api(tags = "图书搜索")
@RestController
@RequestMapping("/dlb/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @ApiOperation("按照科目和分类查找")
    @RequestMapping("/findBook/{s_id}/{cf_id}")
    public RespEntity a(@PathVariable("s_id") Integer sId
                       ,@PathVariable("cf_id") Integer cfId){
        List<Book> books = bookService.findCondition(sId,cfId);
        if (books.isEmpty()){
            return new RespEntity(201,"未查询到有发布的书籍");
        }
        return new RespEntity(200,"查询到以下数据",books);
    }

    @ApiOperation("标题模糊查询")
    @RequestMapping("/dim")
    public RespEntity b(String keyword){
        List<Book> books = bookService.selectDim(keyword);
        return new RespEntity(200,"模糊查询成功！",books);
    }

}

