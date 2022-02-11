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
@TableName("subject_tab")
@ApiModel(value = "SubjectTab对象", description = "")
public class SubjectTab {

    @ApiModelProperty("科目id")
    @TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;

    @ApiModelProperty("科目")
    @TableField("subject")
    private String subject;


}
