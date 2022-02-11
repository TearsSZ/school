package com.dlb.service.impl;

import com.dlb.pojo.Login;
import com.dlb.mapper.LoginMapper;
import com.dlb.service.ILoginService;
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
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

}
