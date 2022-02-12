package com.dlb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dlb.pojo.Book;
import com.dlb.mapper.BookMapper;
import com.dlb.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //根据科目sid和分类cfId进行查找书籍list  以发布时间排序
    @Override
    public List<Book> findCondition(Integer sId, Integer cfId) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("s_id",sId);
        wrapper.eq("cf_id",cfId);
        wrapper.orderByDesc("release_time");
        return baseMapper.selectList(wrapper);
    }

    //根据关键词模糊查询
    @Override
    public List<Book> selectDim(String keyword) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.like("titles",keyword);
        return baseMapper.selectList(wrapper);
    }
}
