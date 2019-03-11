package com.mbyte.easy.mapper;

import java.util.List;

import com.mbyte.easy.entity.SysUserRoles;

public interface SysUserRolesMapper {
    int insert(SysUserRoles record);

    int insertSelective(SysUserRoles record);
    /**
     * 根据user id查询用户管理的权限信息
     * @param sysUserId
     * @return
     */
    List<SysUserRoles> selectByUserId(Long sysUserId);
    
    /**
     * 通过角色id解除用户角色的绑定
     * @param roleId
     * @return
     */
    int deleteUserRolesById(Long roleId);
    
    /**
     * 通过用户id解除角色的绑定
     * @param roleId
     * @return
     */
    int deleteByUserRoleId(Long roleId);
    
}