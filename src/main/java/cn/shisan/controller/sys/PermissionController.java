package cn.shisan.controller.sys;

import cn.shisan.common.JResult;
import cn.shisan.common.PageQuery;
import com.github.pagehelper.PageInfo;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.sys.PermissionTree;
import cn.shisan.dto.query.PermissionQueryDto;
import cn.shisan.domain.entity.sys.Permission;
import cn.shisan.service.sys.PermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "权限资源管理")
@RequestMapping("/api/permission")
@RestController
@RequiredArgsConstructor
public class PermissionController extends BaseController {

    private final PermissionService permissionService;

    @Operation(summary = "权限资源添加")
    @PostMapping("/add")
    public JResult<String> add(@RequestBody Permission permission){
        permissionService.insert(permission);
        return success();
    }

    @Operation(summary = "权限资源修改")
    @PostMapping("/update")
    public JResult<String> update(@RequestBody Permission permission){
        permissionService.update(permission);
        return success();
    }

    @Operation(summary = "权限资源删除")
    @PostMapping("/delete")
    public JResult<String> delete(@RequestParam Long id){
        permissionService.delete(id);
        return success();
    }

    @Operation(summary = "权限资源查询")
    @GetMapping("/findById/{id}")
    public JResult<Permission> findById(@PathVariable Long id){
        Permission permission = permissionService.findById(id);
        return success(permission);
    }

    @Operation(summary = "权限资源树查询")
    @GetMapping("/findTree")
    public JResult<List<PermissionTree>> findTree(){
        List<PermissionTree> permission = permissionService.findTree();
        return success(permission);
    }

    @Operation(summary = "根据父类id查询资源权限")
    @GetMapping("/findByParentId/{parentId}")
    public JResult<List<Permission>> findByParentId(@PathVariable Long parentId){
        List<Permission> permission = permissionService.findByParentId(parentId);
        return success(permission);
    }

    @Operation(summary = "分页列表")
    @PostMapping("/page")
    public JResult<PageInfo<Permission>> pageList(@RequestBody PageQuery<PermissionQueryDto> query){
        PageInfo<Permission> permission = permissionService.pageList(query);
        return success(permission);
    }
}
