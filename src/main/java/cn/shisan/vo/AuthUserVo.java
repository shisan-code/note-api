package com.shisan.note.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthUserVo {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户状态 1=激活，2=禁用")
    private Integer status;

    @ApiModelProperty("个性签名")
    private String signature;

}
