package com.shisan.note.controller.admin;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.common.domain.common.PageQuery;
import com.github.pagehelper.PageInfo;
import com.shisan.note.controller.BaseController;
import com.shisan.note.dto.admin.UserDto;
import com.shisan.note.dto.admin.UserRoleDto;
import com.shisan.note.dto.admin.UserStatusDto;
import com.shisan.note.dto.query.UserPermissionQueryDto;
import com.shisan.note.dto.query.UserQueryDto;
import com.shisan.note.entity.Permission;
import com.shisan.note.entity.User;
import com.shisan.note.service.admin.PermissionService;
import com.shisan.note.service.admin.UserService;
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