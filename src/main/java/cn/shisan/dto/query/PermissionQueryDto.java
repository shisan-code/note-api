package cn.shisan.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PermissionQueryDto {

    @Schema(title = "权限名称")
    private String name;

    @Schema(title = "类型 1菜单 2api")
    private Integer type;

    @Schema(title = "状态 1启用 2停用")
    private Integer status;
}
