package com.shisan.note.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleQueryDto {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色类型：0-超级管理员，1-普通角色")
    private Integer type;
}
