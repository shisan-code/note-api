package com.shisan.note.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

}
