package com.mbyte.easy.mapper;

import com.mbyte.easy.entity.SysUser;

import java.util.List;

public interface SysUserMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(Long id);
	
	/**
	 * 根据用户名查找唯一用户
	 * @param userName
	 * @return
	 */
	SysUser selectByUsername(String userName);

	/**
	 *  根据条件查询用户信息
	 * 
	 * @param user
	 * @return
	 */
	List<SysUser> selectByUser(SysUser user);
	
	

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);
}