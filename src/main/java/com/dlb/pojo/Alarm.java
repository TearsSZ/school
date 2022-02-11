package com.dlb.pojo;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("alarm")
@ApiModel(value = "Alarm对象", description = "")
public class Alarm {
    /**
     * AUTO(0),//自增
     *     NONE(1),//未设置主键
     *     INPUT(2),//手动输入
     *     ASSIGN_ID(3),
     *     ASSIGN_UUID(4);//全局唯一ID
     */
    @ApiModelProperty("报警id")
    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aId;

    @ApiModelProperty("谁报警")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("报警电话")
    @TableField("alarm_phone")
    private String alarmPhone;

    @ApiModelProperty("报警时间")
    @TableField(value = "a_time",fill = FieldFill.INSERT)//插入时自动创建时间
    private LocalDateTime aTime;

    /*
    @Version
    private Integer version;
    @TableField(fill = FieldFill.INSERT)//插入时自动创建时间
    private Date gmtCreated;
    @TableField(fill = FieldFill.UPDATE)//更新时自动创建时间
    private Date gmtModified;
    @TableLogic//逻辑删除
    private Integer deleted;
    * */


}
