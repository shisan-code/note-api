package com.shisan.note.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色表
 */
@Data
public class RoleDto {

    @ApiModelProperty("角色ID")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色类型：0-管理员，1-普通角色")
    private Integer type;

    @ApiModelProperty("角色描述")
    private String remark;

}