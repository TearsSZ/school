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
@TableName("homework")
@ApiModel(value = "Homework对象", description = "")
public class Homework {

    @ApiModelProperty("上传作业id")
    @TableId(value = "h_id", type = IdType.AUTO)
    private Integer hId;

    @ApiModelProperty("发布作业id")
    @TableField("i_id")
    private Integer iId;

    @ApiModelProperty("学生id")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("作业内容详情")
    @TableField("details")
    private String details;

    @ApiModelProperty("媒体1")
    @TableField("media1")
    private String media1;

    @ApiModelProperty("媒体2")
    @TableField("media2")
    private String media2;

    @ApiModelProperty("作业提交时间")
    @TableField("put_time")
    private LocalDateTime putTime;

    @ApiModelProperty("审核时间")
    @TableField("approver_time")
    private LocalDateTime approverTime;

    @ApiModelProperty("审核状态0提交，1审核通过，2审核不通过")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("不通过原因")
    @TableField("cause")
    private String cause;


}
