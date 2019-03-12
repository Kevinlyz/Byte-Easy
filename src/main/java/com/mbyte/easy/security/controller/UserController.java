package com.mbyte.easy.security.controller;

import com.mbyte.easy.entity.SysRole;
import com.mbyte.easy.entity.SysUser;
import com.mbyte.easy.entity.SysUserRoles;
import com.mbyte.easy.mapper.SysRoleMapper;
import com.mbyte.easy.mapper.SysUserMapper;
import com.mbyte.easy.mapper.SysUserRolesMapper;
import com.mbyte.easy.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 管理员控制类
 * 
 * @author 杨凯旋
 *
 */
@Controller
@Transactional
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysUserRolesMapper userRolesMapper;

	@ModelAttribute("roleList")
	public List<SysRole> resourceList() {
		List<SysRole> roleList = roleMapper.selectByRole(null);
		return roleList;
	}

	/**
	 * 进入管理员管理界面
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user')")
	@RequestMapping(value = { "", "/" })
	public String index(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		SysUser user = new SysUser();
		if (name != null && !"".equals(name.trim())) {
			user.setUsername(name);
			model.addAttribute("name", name.trim());
		}
//		PageHelper.startPage(pageNo, pageSize);
//		List<SysUser> list = userMapper.selectByUser(user);
//		for(int i = 0; i < list.size(); i++) {
//			SysUser u = list.get(i);
//			List<SysRole> roles = roleMapper.selectRolesByUserID(u.getId());
//			u.setRoles(roles);
//			list.set(i, u);
//		}
//		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
//		if (pageNo > pageInfo.getPages()) {
//			pageNo = pageInfo.getPages();
//			PageHelper.startPage(pageNo, pageSize);
//			list =  userMapper.selectByUser(user);
//			for(int i = 0; i < list.size(); i++) {
//				SysUser u = list.get(i);
//				List<SysRole> roles = roleMapper.selectRolesByUserID(u.getId());
//				u.setRoles(roles);
//				list.set(i, u);
//			}
//			pageInfo = new PageInfo<SysUser>(list);
//		}
//
//		model.addAttribute("pageInfo", pageInfo);
		return "admin-list";
	}

	/**
	 * 添加管理员
	 * 
	 * @param model
	 * @param resource
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user/add-user')")
	@RequestMapping(value = "/add-user")
	public String addRoleBefore(Model model, @ModelAttribute(value = "user") SysUser user) {
		return "admin-add";
	}

	@ResponseBody
	@RequestMapping(value = "/add-user", params = "save=true")
	public String addRole(Model model, @ModelAttribute(value = "user") SysUser user,
			@RequestParam(required = false) String userRoles) {
		SysUserRoles sysUserRoles = new SysUserRoles();
		SysUser dbUser = userMapper.selectByUsername(user.getUsername());
		// 用户名已存在
		if (dbUser != null) {
			return "2";
		}
		if (user != null && user.getUsername() != null && !"".equals(user.getUsername())) {
			user.setPassword(Utility.QuickPassword(user.getPassword()));
			user.setCreatetime(new Date());
			user.setUpdatetime(new Date());
			userMapper.insert(user);
			user = userMapper.selectByUsername(user.getUsername());
			if (!"".equals(userRoles) && userRoles != null) {
				// 角色字段处理
				String[] roleIds = userRoles.split(",");
				for (String ids : roleIds) {
					Long id = Long.valueOf(ids);
					sysUserRoles.setRolesId(id);
					sysUserRoles.setSysUserId(user.getId());
					userRolesMapper.insert(sysUserRoles);
				}
			}
			return "1";
		}
		return "0";
	}

	/**
	 * 禁用/启用用户
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @Description:
	 */
	@PreAuthorize("hasAuthority('/user/available-user')")
	@ResponseBody
	@RequestMapping(value = "/available-user/{id}")
	public String availableUser(Model model, @PathVariable("id") Long id) {
		SysUser user = userMapper.selectByPrimaryKey(id);
		if (user.getAvailable() != null) {
			user.setAvailable(!user.getAvailable());
			userMapper.updateByPrimaryKey(user);
			return "1";
		} else if (user.getAvailable() == null) {
			user.setAvailable(true);
			userMapper.updateByPrimaryKey(user);
			return "1";
		}
		return "0";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user/delete-user')")
	@ResponseBody
	@RequestMapping(value = "/delete-user/{id}")
	public Integer delet(Model model, @PathVariable("id") Long id) {
		try {
			userRolesMapper.deleteByUserRoleId(id);
			userMapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 * @Description:
	 */
	@PreAuthorize("hasAuthority('/user/delete-user')")
	@ResponseBody
	@RequestMapping(value = "/deleteAll-user", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				userRolesMapper.deleteByUserRoleId(id);
				userMapper.deleteByPrimaryKey(id);
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param model
	 * @param role
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user/edit-user')")
	@RequestMapping(value = "/edit-user/{id}")
	public String editUserBefore(Model model, @ModelAttribute(value = "user") SysUser user,
			@PathVariable("id") Long id) {
		// 根据ID查询用户
		user = userMapper.selectByPrimaryKey(id);
		// 查询对应用户的角色
		List<SysRole> userroles = roleMapper.selectRolesByUserID(id);
		user.setRoles(userroles);
		model.addAttribute("userroles", userroles);
		model.addAttribute("user", user);
		return "admin-editor";
	}

	@ResponseBody
	@RequestMapping(value = "/edit-user", params = "save=true")
	public String editRole(Model model, @ModelAttribute(value = "user") SysUser user,
			@RequestParam(required = false) String userRoles) {
		if (user != null) {
			SysUserRoles sysUserRoles = new SysUserRoles();
			SysUser dbUser = userMapper.selectByPrimaryKey(user.getId());
			user.setPassword(dbUser.getPassword());
			user.setCreatetime(dbUser.getCreatetime());
			user.setUpdatetime(new Date());
			userMapper.updateByPrimaryKey(user);
			if (!"".equals(userRoles) && userRoles != null) {
				userRolesMapper.deleteByUserRoleId(user.getId());
				// 角色字段处理
				String[] roleIds = userRoles.split(",");
				for (String ids : roleIds) {
					Long id = Long.valueOf(ids);
					sysUserRoles.setRolesId(id);
					sysUserRoles.setSysUserId(user.getId());
					userRolesMapper.insert(sysUserRoles);
				}
				//throw new RuntimeException("测试了一个手动异常，判断事物是否回滚。");
			}
			return "1";
		}
		return "0";
	}

	/**
	 * 修改管理员密码
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @Description:
	 */
	@PreAuthorize("hasAuthority('/user/modify-password')")
	@RequestMapping(value = "/modify-password/{id}")
	public String modifyPasswordBefore(Model model, @ModelAttribute(value = "user") SysUser user,
			@PathVariable("id") Long id) {
		user = userMapper.selectByPrimaryKey(user.getId());
		model.addAttribute("user", user);
		return "admin-modify-password";
	}

	@ResponseBody
	@RequestMapping(value = "/modify-password", params = "save=true")
	public String modifyPassword(Model model, @ModelAttribute(value = "user") SysUser user, String adminPassword,
			HttpServletRequest req) {
		SysUser dbUser = userMapper.selectByPrimaryKey(user.getId());

		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);

		if (!Utility.checkPassword(adminPassword, loginUser.getPassword())) {
			return "2";// 管理员密码不正确
		}
		if (dbUser != null) {
			dbUser.setPassword(Utility.QuickPassword(user.getPassword()));
			userMapper.updateByPrimaryKey(dbUser);
			
			return "1";
		}
		return "0";
	}
}
