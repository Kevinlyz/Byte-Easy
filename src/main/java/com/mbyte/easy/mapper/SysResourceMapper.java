package com.mbyte.easy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.entity.SysResource;
import com.mbyte.easy.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Long id);
    
    /**
              * 根据用户名查询资源列表
     * @param username
     * @return
     */
    List<SysResource> selectByUsername(String username);
    
    /**
              *  根据角色id查询资源信息
     * @param roleId
     * @return
     */
    List<SysResource> selectResourceByRoleId(Long roleId);
    
    /**
              * 根据查询条件检索相应的资源数据
     * @param resource
     * @return
     */
    List<SysResource> selectByResource(SysResource resource);
    
    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

    /**
     * 分页查询数据
     * @param page
     * @param name
     * @return
     */
    IPage<SysResource> selectBySysResourceForPage(Page page,  @Param("resource") SysResource resource);
}