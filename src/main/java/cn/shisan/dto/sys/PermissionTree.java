package cn.shisan.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class PermissionTree {

    @Schema(title = "权限ID")
    private Long id;

    @Schema(title = "父ID")
    private Long parentId;

    @Schema(title = "权限名称")
    private String name;

    @Schema(title = "url 地址")
    private String url;

    @Schema(title = "类型 1菜单 2api")
    private Integer type;

    @Schema(title = "状态 1启用 2停用")
    private Integer status;

    @Schema(title = "备注")
    private String remark;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "子级权限")
    private List<PermissionTree> children;
}
