package com.dlb.service.impl;

import com.dlb.pojo.Book;
import com.dlb.mapper.BookMapper;
import com.dlb.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
