package com.shisan.note.service.admin;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.shisan.note.dto.admin.PermissionTree;
import com.shisan.note.dto.query.PermissionQueryDto;
import com.shisan.note.entity.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {

    List<PermissionTree> findTree();

    /**
     * 用户菜单
     *
     * @param userId 用户ID
     * @return 用户菜单
     */
    List<PermissionTree> userMenu(Long userId);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    Permission findById(Long id);

    /**
     * 新增，插入所有字段
     *
     * @param permission 新增的记录
     * @return 返回影响行数
     */
    int insert(Permission permission);

    /**
     * 修改，忽略null字段
     *
     * @param permission 修改的记录
     * @return 返回影响行数
     */
    int update(Permission permission);

    /**
     * 删除记录
     */
    int delete(Long id);

    /**
     * 获取用户角色权限（启用的）
     *
     * @param userId 用户id
     * @param type   类型 1菜单 2api
     * @return 用户权限
     */
    List<Permission> findByUserId(Long userId, Integer type);

    /**
     * 根据父类id查询资源权限
     *
     * @param parentId 父类id
     * @return 资源权限
     */
    List<Permission> findByParentId(Long parentId);

    /**
     * 分页
     */
    PageInfo<Permission> pageList(PageQuery<PermissionQueryDto> query);

    /**
     * 是否管理员
     *
     * @author lijing
     * @Date 2026/1/15 10:37
     */
    boolean isAdmin(Long userId);

}