package cn.shisan.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleDto {

    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "角色ID")
    private List<Long> roleIds;

}
