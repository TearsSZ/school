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
@TableName("system_msg")
@ApiModel(value = "SystemMsg对象", description = "")
public class SystemMsg {

    @ApiModelProperty("消息推送id")
    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer mId;

    @ApiModelProperty("消息类型id")
    @TableField("ms_id")
    private Integer msId;

    @ApiModelProperty("推送内容")
    @TableField("details")
    private String details;

    @ApiModelProperty("推送时间")
    @TableField("m_time")
    private LocalDateTime mTime;


}
