package com.shisan.note.dto.note;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 日记，每日一记
 * </p>
 *
 * @author lijing
 * @since 2025-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Diary对象", description="日记，每日一记")
public class DiaryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键（日记）id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "小记内容")
    private String content;

}
