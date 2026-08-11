package cn.shisan.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserPermissionQueryDto {

    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "类型 1菜单 2api")
    private Integer type;
}
