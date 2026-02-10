package cn.shisan.service.sys;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import cn.shisan.dto.sys.UserDto;
import cn.shisan.dto.sys.UserRoleDto;
import cn.shisan.dto.sys.UserStatusDto;
import cn.shisan.dto.query.UserQueryDto;
import cn.shisan.domain.entity.sys.User;

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
