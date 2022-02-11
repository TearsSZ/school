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
@TableName("scores")
@ApiModel(value = "Scores对象", description = "")
public class Scores {

    @ApiModelProperty("成绩表id")
    @TableId(value = "sc_id", type = IdType.AUTO)
    private Integer scId;

    @ApiModelProperty("学生")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty("科目（语文）")
    @TableField("chinese")
    private String chinese;

    @ApiModelProperty("科目（数学）")
    @TableField("mathematics")
    private String mathematics;

    @ApiModelProperty("科目（英语）")
    @TableField("english")
    private String english;

    @ApiModelProperty("总分")
    @TableField("sum")
    private String sum;

    @ApiModelProperty("考试时间")
    @TableField("monthly_examination")
    private LocalDateTime monthlyExamination;


}
