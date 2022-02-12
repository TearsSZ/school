package com.dlb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dlb.pojo.Login;
import com.dlb.mapper.LoginMapper;
import com.dlb.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {
    @Resource
    private LoginMapper loginMapper;

    //根据手机号查找本条数据
    @Override
    public Login queryByPhone(String phone){
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        return baseMapper.selectOne(wrapper);
    }
    //根据手机号更新本条数据
    @Override
    public void saveByPhone(String phone, Login login) {
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        baseMapper.update(login,wrapper);
    }
}
