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
@TableName("education")
@ApiModel(value = "Education对象", description = "")
public class Education {

    @ApiModelProperty("教育表id")
    @TableId(value = "e_id", type = IdType.AUTO)
    private Integer eId;

    @ApiModelProperty("安全教育分类")
    @TableField("se_id")
    private Integer seId;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("发布时间")
    @TableField("e_time")
    private LocalDateTime eTime;

    @ApiModelProperty("阅读量")
    @TableField("reading")
    private Integer reading;

    @ApiModelProperty("内容详情（如果有图片就放到list集合中以JSON格式存储）")
    @TableField("particulars")
    private String particulars;


}
