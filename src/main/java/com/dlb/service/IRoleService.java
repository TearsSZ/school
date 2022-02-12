package com.dlb.service;

import com.dlb.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
public interface IRoleService extends IService<Role> {

    Role getRoleOne(Integer rId);
}
