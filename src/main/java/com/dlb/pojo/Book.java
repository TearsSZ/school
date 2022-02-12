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
@TableName("book")
@ApiModel(value = "Book对象", description = "")
public class Book {

    @ApiModelProperty("图书表id")
    @TableId(value = "b_id", type = IdType.AUTO)
    private Integer bId;

    @ApiModelProperty("科目id")
    @TableField("s_id")
    private Integer sId;

    @ApiModelProperty("分类id")
    @TableField("cf_id")
    private Integer cfId;

    @ApiModelProperty("标题")
    @TableField("titles")
    private String titles;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("来源")
    @TableField("source")
    private String source;

    @ApiModelProperty("发布时间")
    @TableField("release_time")
    private LocalDateTime releaseTime;


}
