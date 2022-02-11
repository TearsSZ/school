package com.dlb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
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
@TableName("login")
@ApiModel(value = "Login对象", description = "")
public class Login {

    @ApiModelProperty("用户表id")
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("密码")
    @TableField("pwd")
    private String pwd;

    @ApiModelProperty("用户姓名")
    @TableField("username")
    private String username;

    @ApiModelProperty("头像")
    @TableField("portrait")
    private String portrait;

    @ApiModelProperty("支付密码")
    @TableField("pay_code")
    private String payCode;

    @ApiModelProperty("余额")
    @TableField("balance")
    private BigDecimal balance;

    @ApiModelProperty("班级id")
    @TableField("c_id")
    private Integer cId;

    @ApiModelProperty("角色")
    @TableField("r_id")
    private Integer rId;

    @ApiModelProperty("修改密码版本")
    @TableField("versions")
    private Integer versions;


}
