package com.shisan.note.service;

import cn.shisan.common.domain.common.PageQuery;
import cn.shisan.common.domain.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.dto.admin.UserDto;
import com.shisan.note.dto.auth.LoginDto;
import com.shisan.note.dto.auth.UserRegister;
import com.shisan.note.dto.admin.UserRoleDto;
import com.shisan.note.dto.admin.UserStatusDto;
import com.shisan.note.dto.query.UserQueryDto;
import com.shisan.note.entity.User;
import com.shisan.note.vo.LoginVo;

public interface UserService extends IService<User> {

    /**
     * 注册用户
     * @param register 用户信息
     */
    void register(UserRegister register);

    /**
     * 用户登录
     * @param login 用户信息
     */
    LoginVo login(LoginDto login);

    /**
     * 设置用户角色
     * @param userRoleDto
     */
    void setRole(UserRoleDto userRoleDto);

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     */
    User findByUserName(String userName);

    /**
     * 分页
     */
    PageResult<UserDto> pageList(PageQuery<UserQueryDto> query);

    /**
     * 修改用户状态
     * @param statusDto 参数
     */
    void updateStatus(UserStatusDto statusDto);
}
