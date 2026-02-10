package com.shisan.note.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryDto {

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("用户状态 1=激活，2=禁用")
    private Integer status;
}
