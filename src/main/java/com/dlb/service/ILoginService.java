package com.dlb.service;

import com.dlb.pojo.Login;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
public interface ILoginService extends IService<Login> {

    Login queryByPhone(String phone);

    void saveByPhone(String phone, Login login);
}
