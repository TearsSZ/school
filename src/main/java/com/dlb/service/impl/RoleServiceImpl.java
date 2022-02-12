package com.dlb.service.impl;

import com.dlb.pojo.Role;
import com.dlb.mapper.RoleMapper;
import com.dlb.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role getRoleOne(Integer rId) {
        return roleMapper.selectById(rId);
    }
}
