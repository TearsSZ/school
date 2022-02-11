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
@TableName("visitor")
@ApiModel(value = "Visitor对象", description = "")
public class Visitor {

    @ApiModelProperty("访客记录表id")
    @TableId(value = "v_id", type = IdType.AUTO)
    private Integer vId;

    @ApiModelProperty("申请人姓名")
    @TableField("v_name")
    private String vName;

    @ApiModelProperty("身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("访问对象")
    @TableField("v_object")
    private String vObject;

    @ApiModelProperty("与被访问人关系")
    @TableField("relation")
    private String relation;

    @ApiModelProperty("访问人数")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("预约时间")
    @TableField("subscribe")
    private LocalDateTime subscribe;

    @ApiModelProperty("访客照片")
    @TableField("v_img")
    private String vImg;

    @ApiModelProperty("审批时间")
    @TableField("approver_time")
    private LocalDateTime approverTime;

    @ApiModelProperty("审批状态0提交，1通过，2拒绝")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("拒绝原因")
    @TableField("cause")
    private String cause;


}
