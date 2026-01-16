package com.shisan.note.service.admin;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.shisan.note.dto.admin.UserDto;
import com.shisan.note.dto.admin.UserRoleDto;
import com.shisan.note.dto.admin.UserStatusDto;
import com.shisan.note.dto.query.UserQueryDto;
import com.shisan.note.entity.admin.User;

public interface UserService extends IService<User> {

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
    PageInfo<UserDto> pageList(PageQuery<UserQueryDto> query);

    /**
     * 修改用户状态
     * @param statusDto 参数
     */
    void updateStatus(UserStatusDto statusDto);
}
