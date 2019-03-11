package com.mbyte.easy.mapper;

import java.util.List;

import com.mbyte.easy.entity.SysRoleResources;

public interface SysRoleResourcesMapper {
    int insert(SysRoleResources record);

    int insertSelective(SysRoleResources record);
    
    /**
     * 根据角色id删除绑定的资源信息
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);
    
   /**
           * 根据角色信息查询资源信息
    * @param sysRoleId 角色id
    * @return
    */
    List<SysRoleResources> selectByRoleId(Long sysRoleId);
    
}