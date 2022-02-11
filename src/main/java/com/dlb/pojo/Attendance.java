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
@TableName("attendance")
@ApiModel(value = "Attendance对象", description = "")
public class Attendance {

    @ApiModelProperty("考勤表id")
    @TableId(value = "at_id", type = IdType.AUTO)
    private Integer atId;

    @ApiModelProperty("学生id")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("班级id")
    @TableField("c_id")
    private Integer cId;

    @ApiModelProperty("迟到次数")
    @TableField("be_late")
    private Integer beLate;

    @ApiModelProperty("早退次数")
    @TableField("leave_early")
    private Integer leaveEarly;

    @ApiModelProperty("未签到次数")
    @TableField("no_sign")
    private Integer noSign;

    @ApiModelProperty("异常次数")
    @TableField("abnormal")
    private Integer abnormal;

    @ApiModelProperty("请假次数")
    @TableField("leave")
    private Integer leave;

    @ApiModelProperty("开始记录时间")
    @TableField("begin_time")
    private LocalDateTime beginTime;

    @ApiModelProperty("结束记录时间")
    @TableField("end_time")
    private LocalDateTime endTime;


}
