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
@TableName("teacher")
@ApiModel(value = "Teacher对象", description = "")
public class Teacher {

    @ApiModelProperty("老师表id")
    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;

    @ApiModelProperty("班级id")
    @TableField("c_id")
    private Integer cId;

    @ApiModelProperty("姓名")
    @TableField("t_name")
    private String tName;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;


}
