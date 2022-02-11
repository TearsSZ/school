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
@TableName("dormitory_record")
@ApiModel(value = "DormitoryRecord对象", description = "")
public class DormitoryRecord {

    @ApiModelProperty("宿舍打卡记录表id")
    @TableId(value = "dp_id", type = IdType.AUTO)
    private Integer dpId;

    @ApiModelProperty("打卡学生")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("每日打卡记录用JSON形式存储")
    @TableField("clock_in")
    private String clockIn;

    @ApiModelProperty("每日打卡日期")
    @TableField("punch_time")
    private LocalDateTime punchTime;


}
