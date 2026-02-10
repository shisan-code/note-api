package com.shisan.note.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionQueryDto {

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("类型 1菜单 2api")
    private Integer type;

    @ApiModelProperty("状态 1启用 2停用")
    private Integer status;
}
