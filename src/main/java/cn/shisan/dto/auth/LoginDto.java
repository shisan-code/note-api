package cn.shisan.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginDto {

    @Schema(title = "用户名称")
    private String username;

    @Schema(title = "密码")
    private String password;

}
