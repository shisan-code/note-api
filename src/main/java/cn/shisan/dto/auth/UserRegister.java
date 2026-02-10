package com.shisan.note.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRegister {

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("手机号码")
    private String email;

    @ApiModelProperty("个性签名")
    private String signature;
}
