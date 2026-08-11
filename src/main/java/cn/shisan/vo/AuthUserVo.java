package cn.shisan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthUserVo {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "用户名称")
    private String name;

    @Schema(title = "用户名")
    private String userName;

    @Schema(title = "手机号码")
    private String phone;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "用户状态 1=激活，2=禁用")
    private Integer status;

    @Schema(title = "个性签名")
    private String signature;

}
