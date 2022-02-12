package com.dlb.controller;


import com.dlb.pojo.Leave;
import com.dlb.pojo.Login;
import com.dlb.pojo.vo.RespEntity;
import com.dlb.service.ILeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
@Api(tags = "请假服务")
@RestController
@RequestMapping("/dlb/leave")
@Slf4j
public class LeaveController {
    @Autowired
    private ILeaveService leaveService;

    @ApiOperation("我要请假")
    @RequestMapping("/add")
    public RespEntity a(Leave leave, HttpServletRequest request){
        Login user = (Login) request.getSession().getAttribute("user");
        leave.setCId(user.getCId());//班级
        leave.setUId(user.getUId());//请假人
        leave.setUName(user.getUsername());//请假人姓名--纠正前端数据（防止替别人请假）
        leave.setCurrentTime(LocalDateTime.now());//请假时间
        leaveService.insertByOne(leave);
        log.info("请假申请={}",leave);
        return new RespEntity(200,"提交完成");
    }

    @ApiOperation("查看请假详情")
    @RequestMapping("/look/{lid}")
    public RespEntity b(@PathVariable Integer lid){
        Leave leave = leaveService.queryByUid(lid);
        if (leave!=null){
            return new RespEntity(200,"查询成功",leave);
        }
        return new RespEntity(201,"查询失败");
    }

    @ApiOperation("未审批")//查询uid=1并且状态=0，安照时间排序
    @RequestMapping("/noApprove/{uid}")
    public RespEntity c(@PathVariable Integer uid){
        List<Leave> leaveList = leaveService.queryNoApproveList(uid);
        if (leaveList.isEmpty()){
            return new RespEntity(201,"未查询到申请");
        }
        return new RespEntity(200,"查询到以下数据",leaveList);
    }

    @ApiOperation("已审批")//查询uid=1并且状态>0，安照时间排序
    @RequestMapping("/Approve/{uid}")
    public RespEntity d(@PathVariable Integer uid){
        List<Leave> leaves = leaveService.queryApproveList(uid);
        if (leaves.isEmpty()){
            return new RespEntity(201,"未查询到已审批");
        }
        return new RespEntity(200,"查询到以下数据",leaves);
    }




}

