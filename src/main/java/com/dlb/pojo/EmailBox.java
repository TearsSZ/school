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
@TableName("email_box")
@ApiModel(value = "EmailBox对象", description = "")
public class EmailBox {

    @ApiModelProperty("校长信箱id")
    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer mId;

    @ApiModelProperty("发送内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("发送人")
    @TableField("u_id")
    private Integer uId;


}
