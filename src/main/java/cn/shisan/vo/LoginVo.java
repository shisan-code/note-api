package com.shisan.note.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author lijing
 * @Date 2026/1/13 14:50
 */
@Builder
@Data
public class LoginVo {

    @ApiModelProperty("token")
    private String accessToken;
    @ApiModelProperty("过期时间")
    private Long expiration;
    @ApiModelProperty("登录用户信息")
    private AuthUserVo authUser;
}
