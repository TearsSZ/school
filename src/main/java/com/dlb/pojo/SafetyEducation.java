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
@TableName("safety_education")
@ApiModel(value = "SafetyEducation对象", description = "")
public class SafetyEducation {

    @ApiModelProperty("安全教育表id")
    @TableId(value = "se_id", type = IdType.AUTO)
    private Integer seId;

    @ApiModelProperty("教育科目")
    @TableField("subject")
    private String subject;


}
