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
@TableName("dormitory")
@ApiModel(value = "Dormitory对象", description = "")
public class Dormitory {

    @ApiModelProperty("宿舍记录id")
    @TableId(value = "d_id", type = IdType.AUTO)
    private Integer dId;

    @ApiModelProperty("学生id")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("班主任id")
    @TableField("t_id")
    private Integer tId;

    @ApiModelProperty("宿舍房间")
    @TableField("dorm_rooms")
    private String dormRooms;

    @ApiModelProperty("已归次数")
    @TableField("returned")
    private Integer returned;

    @ApiModelProperty("未归次数")
    @TableField("no_returned")
    private Integer noReturned;

    @ApiModelProperty("异常次数")
    @TableField("abnormal")
    private Integer abnormal;

    @ApiModelProperty("请假次数")
    @TableField("leave")
    private Integer leave;


}
