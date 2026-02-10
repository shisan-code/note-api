package com.shisan.note.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDto {

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("权限id")
    private List<Long> permissionIds;

}
