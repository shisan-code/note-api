package cn.shisan.controller.sys;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.common.domain.common.PageQuery;
import com.github.pagehelper.PageInfo;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.sys.UserDto;
import cn.shisan.dto.sys.UserRoleDto;
import cn.shisan.dto.sys.UserStatusDto;
import cn.shisan.dto.query.UserPermissionQueryDto;
import cn.shisan.dto.query.UserQueryDto;
import cn.shisan.domain.entity.sys.Permission;
import cn.shisan.domain.entity.sys.User;
import cn.shisan.service.sys.PermissionService;
import cn.shisan.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "用户管理API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;
    private final PermissionService permissionService;

    @ApiOperation("用户分页")
    @PostMapping("/page")
    public JResult<PageInfo<UserDto>> page(@RequestBody PageQuery<UserQueryDto> query) {
        PageInfo<UserDto> page = userService.pageList(query);
        return success(page);
    }

    @ApiOperation("用户详情")
    @PostMapping("/info/{userName}")
    public JResult<User> info(@PathVariable String userName) {
        User page = userService.findByUserName(userName);
        return success(page);
    }

    @ApiOperation("设置用户角色")
    @PostMapping("/setRole")
    public JResult<String> setRole(@RequestBody UserRoleDto userRoleDto) {
        userService.setRole(userRoleDto);
        return success();
    }

    @ApiOperation("修改用户状态")
    @PostMapping("/updateStatus")
    public JResult<String> updateStatus(@RequestBody UserStatusDto statusDto) {
        userService.updateStatus(statusDto);
        return success();
    }

    @ApiOperation("用户资源权限（启用有效的） ")
    @PostMapping("/permission")
    public JResult<List<Permission>> permissionList(@RequestBody UserPermissionQueryDto query) {
        List<Permission> permission = permissionService.findByUserId(query.getUserId(), query.getType());
        return success(permission);
    }

}