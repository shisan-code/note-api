package com.shisan.note.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

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

    @ApiModelProperty("创建时间")
    private LocalDateTime created;

    @ApiModelProperty("修改时间")
    private LocalDateTime modified;


}
