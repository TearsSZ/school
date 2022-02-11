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
@TableName("issue_work")
@ApiModel(value = "IssueWork对象", description = "")
public class IssueWork {

    @ApiModelProperty("作业表id")
    @TableId(value = "i_id", type = IdType.AUTO)
    private Integer iId;

    @ApiModelProperty("班级id")
    @TableField("c_id")
    private Integer cId;

    @ApiModelProperty("科目id")
    @TableField("s_id")
    private Integer sId;

    @ApiModelProperty("老师id")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("作业详情介绍")
    @TableField("details")
    private String details;

    @ApiModelProperty("作业发布时间")
    @TableField("issue_time")
    private LocalDateTime issueTime;


}
