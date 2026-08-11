package cn.shisan.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDto {

    @Schema(title = "角色id")
    private Long roleId;

    @Schema(title = "权限id")
    private List<Long> permissionIds;

}
