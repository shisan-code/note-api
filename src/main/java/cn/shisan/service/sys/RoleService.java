package com.shisan.note.service.sys;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.shisan.note.dto.sys.RoleDto;
import com.shisan.note.dto.sys.RolePermissionDto;
import com.shisan.note.dto.query.RoleQueryDto;
import com.shisan.note.domain.entity.sys.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    Role findById(Long id);

    /**
     * 新增，插入所有字段
     *
     * @param roleDto 新增的记录
     *
     */
    void insert(RoleDto roleDto);

    /**
     * 修改，忽略null字段
     *
     * @param roleDto 修改的记录
     *
     */
    void update(RoleDto roleDto);

    /**
     * 删除记录
     */
    void delete(Long id);

    /**
     * 设置角色权限
     */
    void setPermission(RolePermissionDto rolePermissionDto);

    /**
     * 用户角色
     *
     * @author lijing
     * @Date 2026/1/15 10:34
     */
    List<Role> findByUserId(Long userId);

    /**
     * 分页
     */
    PageInfo<Role> pageList(PageQuery<RoleQueryDto> query);

    /**
     * 分页
     */
    List<Role> list(RoleQueryDto query);
}