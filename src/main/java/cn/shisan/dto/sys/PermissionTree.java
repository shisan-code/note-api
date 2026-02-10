package cn.shisan.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PermissionTree {

    @ApiModelProperty("权限ID")
    private Long id;

    @ApiModelProperty("父ID")
    private Long parentId;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("url 地址")
    private String url;

    @ApiModelProperty("类型 1菜单 2api")
    private Integer type;

    @ApiModelProperty("状态 1启用 2停用")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("子级权限")
    private List<PermissionTree> children;
}
