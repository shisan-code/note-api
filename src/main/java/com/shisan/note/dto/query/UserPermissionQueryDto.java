package com.shisan.note.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPermissionQueryDto {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("类型 1菜单 2api")
    private Integer type;
}
