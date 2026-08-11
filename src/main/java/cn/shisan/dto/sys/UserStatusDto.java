package cn.shisan.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserStatusDto {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "用户状态 1=激活，2=禁用")
    private Integer status;

}
