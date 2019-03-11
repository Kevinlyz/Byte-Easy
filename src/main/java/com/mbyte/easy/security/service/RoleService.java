package com.mbyte.easy.security.service;
/**
 *     角色服务抽象类
 * @author wangzhen
 *
 */

import java.util.List;

import com.mbyte.easy.entity.SysRole;

public interface RoleService {
	
	/**
	 * 根据用户名查询用户具有的权限
	 * @param username
	 * @return
	 */
	List<SysRole> selectByUsername(String username);
	

}
