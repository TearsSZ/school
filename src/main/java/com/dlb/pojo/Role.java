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
@TableName("role")
@ApiModel(value = "Role对象", description = "")
public class Role {

    @ApiModelProperty("角色表id")
    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer rId;

    @ApiModelProperty("角色")
    @TableField("role")
    private String role;


}
