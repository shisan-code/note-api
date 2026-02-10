package com.shisan.note.dto.note;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 笔记本
 * date 2025/6/12
 *
 * @author tuota
 */
@Data
public class NotebookDto {

    @ApiModelProperty("主键id，修改时使用")
    private Long id;

    @ApiModelProperty("笔记本名称")
    private String name;
}
