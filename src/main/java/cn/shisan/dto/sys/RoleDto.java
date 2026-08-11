package cn.shisan.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色表
 */
@Data
public class RoleDto {

    @Schema(title = "角色ID")
    private Long id;

    @Schema(title = "角色名称")
    private String name;

    @Schema(title = "角色类型：0-管理员，1-普通角色")
    private Integer type;

    @Schema(title = "角色描述")
    private String remark;

}