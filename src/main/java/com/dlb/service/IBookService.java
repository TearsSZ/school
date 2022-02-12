package com.dlb.service;

import com.dlb.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
public interface IBookService extends IService<Book> {

    List<Book> findCondition(Integer sId, Integer cfId);

    List<Book> selectDim(String keyword);
}
