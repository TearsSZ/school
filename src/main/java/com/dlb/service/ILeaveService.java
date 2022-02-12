package com.dlb.service;

import com.dlb.pojo.Leave;
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
public interface ILeaveService extends IService<Leave> {

    void insertByOne(Leave leave);

    Leave queryByUid(Integer lId);

    List<Leave> queryNoApproveList(Integer uid);

    List<Leave> queryApproveList(Integer uid);
}
