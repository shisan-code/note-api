package cn.shisan.controller.sys;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.common.domain.common.PageQuery;
import com.github.pagehelper.PageInfo;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.sys.PermissionTree;
import cn.shisan.dto.query.PermissionQueryDto;
import cn.shisan.domain.entity.sys.Permission;
import cn.shisan.service.sys.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "权限资源管理")
@RequestMapping("/api/permission")
@RestController
@RequiredArgsConstructor
public class PermissionController extends BaseController {

    private final PermissionService permissionService;

    @ApiOperation("权限资源添加")
    @PostMapping("/add")
    public JResult<String> add(@RequestBody Permission permission){
        permissionService.insert(permission);
        return success();
    }

    @ApiOperation("权限资源修改")
    @PostMapping("/update")
    public JResult<String> update(@RequestBody Permission permission){
        permissionService.update(permission);
        return success();
    }

    @ApiOperation("权限资源删除")
    @PostMapping("/delete")
    public JResult<String> delete(@RequestParam Long id){
        permissionService.delete(id);
        return success();
    }

    @ApiOperation("权限资源查询")
    @GetMapping("/findById/{id}")
    public JResult<Permission> findById(@PathVariable Long id){
        Permission permission = permissionService.findById(id);
        return success(permission);
    }

    @ApiOperation("权限资源树查询")
    @GetMapping("/findTree")
    public JResult<List<PermissionTree>> findTree(){
        List<PermissionTree> permission = permissionService.findTree();
        return success(permission);
    }

    @ApiOperation("根据父类id查询资源权限")
    @GetMapping("/findByParentId/{parentId}")
    public JResult<List<Permission>> findByParentId(@PathVariable Long parentId){
        List<Permission> permission = permissionService.findByParentId(parentId);
        return success(permission);
    }

    @ApiOperation("分页列表")
    @PostMapping("/page")
    public JResult<PageInfo<Permission>> pageList(@RequestBody PageQuery<PermissionQueryDto> query){
        PageInfo<Permission> permission = permissionService.pageList(query);
        return success(permission);
    }
}
