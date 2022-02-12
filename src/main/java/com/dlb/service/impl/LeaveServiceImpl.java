package com.dlb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dlb.pojo.Leave;
import com.dlb.mapper.LeaveMapper;
import com.dlb.service.ILeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements ILeaveService {

    @Resource
    private LeaveMapper leaveMapper;

    //插入一条数据
    @Override
    public void insertByOne(Leave leave) {
        baseMapper.insert(leave);
    }

    //根据id查询一条数据
    @Override
    public Leave queryByUid(Integer lId) {
        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        wrapper.eq("l_id",lId);
        return baseMapper.selectOne(wrapper);
    }

    //未审核数据 用户id=？ 并且status=0  按照时间排序
    @Override
    public List<Leave> queryNoApproveList(Integer uid) {
        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id",uid);
        wrapper.eq("status",0);
        wrapper.orderByDesc("current_time");//降序
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Leave> queryApproveList(Integer uid) {
        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id",uid);
        wrapper.ge("status",0);
        wrapper.orderByDesc("current_time");
        return baseMapper.selectList(wrapper);
    }
}
