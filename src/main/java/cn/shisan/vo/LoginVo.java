package cn.shisan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author lijing
 * @Date 2026/1/13 14:50
 */
@Builder
@Data
public class LoginVo {

    @Schema(title = "token")
    private String accessToken;
    @Schema(title = "过期时间")
    private Long expiration;
    @Schema(title = "登录用户信息")
    private AuthUserVo authUser;
}
