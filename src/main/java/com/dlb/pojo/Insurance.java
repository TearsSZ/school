package com.dlb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("insurance")
@ApiModel(value = "Insurance对象", description = "")
public class Insurance {

    @ApiModelProperty("保险id")
    @TableId(value = "in_id", type = IdType.AUTO)
    private Integer inId;

    @ApiModelProperty("购买保险id")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("姓名")
    @TableField("u_name")
    private String uName;

    @ApiModelProperty("性别")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private String birthday;

    @ApiModelProperty("身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty("班级")
    @TableField("clazz")
    private String clazz;

    @ApiModelProperty("联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("与被保人关系")
    @TableField("relation")
    private String relation;

    @ApiModelProperty("状态0提交，1回访，2未回访")
    @TableField("status")
    private Integer status;


}
