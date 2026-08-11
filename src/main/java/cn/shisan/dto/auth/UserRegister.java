package cn.shisan.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRegister {

    @Schema(title = "用户名称")
    private String name;

    @Schema(title = "用户名")
    private String userName;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "手机号码")
    private String phone;

    @Schema(title = "手机号码")
    private String email;

    @Schema(title = "个性签名")
    private String signature;
}
