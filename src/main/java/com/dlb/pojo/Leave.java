package com.dlb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
@Getter
@Setter
@TableName("leave")
@ApiModel(value = "Leave对象", description = "")
public class Leave {

    @ApiModelProperty("请假表id")
    @TableId(value = "l_id", type = IdType.AUTO)
    private Integer lId;

    @ApiModelProperty("请假人")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("请假人姓名")
    @TableField("u_name")
    private String uName;

    @ApiModelProperty("请假原因0事假，1病假")
    @TableField("cause")
    private Integer cause;

    @ApiModelProperty("病症")
    @TableField("disease")
    private String disease;

    @ApiModelProperty("情况说明")
    @TableField("explain")
    private String explain;

    @ApiModelProperty("开始时间")
    @TableField("begin_time")
    private LocalDateTime beginTime;

    @ApiModelProperty("结束时间")
    @TableField("end_time")
    private String endTime;

    @ApiModelProperty("请假时长")
    @TableField("duration")
    private Integer duration;

    @ApiModelProperty("审批人")
    @TableField("approver")
    private String approver;

    @ApiModelProperty("提交时间")
    @TableField("current_time")
    private String currentTime;

    @ApiModelProperty("状态0提交，1通过，2不通过")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("不通过原因")
    @TableField("no_pass_cause")
    private String noPassCause;


}
