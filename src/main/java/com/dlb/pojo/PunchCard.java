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
@TableName("punch_card")
@ApiModel(value = "PunchCard对象", description = "")
public class PunchCard {

    @ApiModelProperty("打卡id")
    @TableId(value = "pc_id", type = IdType.AUTO)
    private Integer pcId;

    @ApiModelProperty("学生打卡")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("每日打卡记录json")
    @TableField("clock_in")
    private String clockIn;

    @ApiModelProperty("本条记录日期")
    @TableField("p_date")
    private LocalDateTime pDate;


}
