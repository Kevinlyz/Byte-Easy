package com.mbyte.easy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);
    
    /**
     	* 根据查询条件检索角色信息
     * @param role
     * @return
     */
    List<SysRole> selectByRole(SysRole role);
    
    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
               * 通过用户id查询角色信息
     * @param id
     * @return
     */
    List<SysRole> selectRolesByUserID(Long id);
    
    /**
	 * 根据角色名查找唯一角色
	 * @param userName
	 * @return
	 */
    SysRole selectByRolename(String name);

    /**
     * 分页查询数据
     * @param page
     * @param name
     * @return
     */
    IPage<SysRole> selectByRoleForPage(Page page, @Param("name") String name);
}