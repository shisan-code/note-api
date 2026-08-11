package cn.shisan.controller.sys;

import cn.shisan.common.JResult;
import cn.shisan.common.PageQuery;
import com.github.pagehelper.PageInfo;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.sys.RoleDto;
import cn.shisan.dto.sys.RolePermissionDto;
import cn.shisan.dto.query.RoleQueryDto;
import cn.shisan.domain.entity.sys.Role;
import cn.shisan.domain.entity.sys.RolePermission;
import cn.shisan.service.sys.RolePermissionService;
import cn.shisan.service.sys.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "角色管理")
@RequestMapping("/api/role")
@RestController
@RequiredArgsConstructor
public class RoleController extends BaseController {

    private final RoleService roleService;
    private final RolePermissionService rolePermissionService;

    @Operation(summary = "角色添加")
    @PostMapping("/add")
    public JResult<String> add(@RequestBody RoleDto roleDto){
        roleService.insert(roleDto);
        return success();
    }

    @Operation(summary = "角色修改")
    @PostMapping("/update")
    public JResult<String> update(@RequestBody RoleDto roleDto){
        roleService.update(roleDto);
        return success();
    }

    @Operation(summary = "角色删除")
    @PostMapping("/delete")
    public JResult<String> delete(@RequestParam Long id){
        roleService.delete(id);
        return success();
    }

    @Operation(summary = "设置角色权限")
    @PostMapping("/setPermission")
    public JResult<Role> setPermission(@RequestBody RolePermissionDto rolePermissionDto){
        roleService.setPermission(rolePermissionDto);
        return success();
    }

    @Operation(summary = "获取角色权限id集合")
    @GetMapping("/findPermissionIds/{roleId}")
    public JResult<List<Long>> setPermission(@PathVariable Long roleId){
        List<RolePermission> rolePermissions = rolePermissionService.findByRoleId(roleId);
        return success(rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList()));
    }

    @Operation(summary = "角色查询")
    @GetMapping("/findById/{id}")
    public JResult<Role> findById(@PathVariable Long id){
        Role role = roleService.findById(id);
        return success(role);
    }

    @Operation(summary = "角色分页")
    @PostMapping("/page")
    public JResult<PageInfo<Role>> pageList(@RequestBody PageQuery<RoleQueryDto> query){
        query.setParams(Optional.ofNullable(query.getParams()).orElse(new RoleQueryDto()));
        PageInfo<Role> role = roleService.pageList(query);
        return success(role);
    }

    @Operation(summary = "角色列表")
    @PostMapping("/list")
    public JResult<List<Role>> list(@RequestBody RoleQueryDto query){
        List<Role> role = roleService.list(query);
        return success(role);
    }

    @Operation(summary = "用户角色")
    @GetMapping("/findByUserId/{userId}")
    public JResult<List<Role>> findByUserId(@PathVariable Long userId){
        List<Role> role = roleService.findByUserId(userId);
        return success(role);
    }

}
