package cn.shisan.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleQueryDto {

    @Schema(title = "角色名称")
    private String name;

    @Schema(title = "角色类型：0-超级管理员，1-普通角色")
    private Integer type;
}
