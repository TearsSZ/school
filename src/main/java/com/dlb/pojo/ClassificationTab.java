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
@TableName("classification_tab")
@ApiModel(value = "ClassificationTab对象", description = "")
public class ClassificationTab {

    @ApiModelProperty("图书分类表id")
    @TableId(value = "cf_id", type = IdType.AUTO)
    private Integer cfId;

    @ApiModelProperty("分类类别")
    @TableField("classification")
    private String classification;


}
