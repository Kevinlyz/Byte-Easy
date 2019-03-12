package com.mbyte.easy.security.controller;

import com.mbyte.easy.entity.SysResource;
import com.mbyte.easy.entity.SysRole;
import com.mbyte.easy.entity.SysRoleResources;
import com.mbyte.easy.mapper.SysResourceMapper;
import com.mbyte.easy.mapper.SysRoleMapper;
import com.mbyte.easy.mapper.SysRoleResourcesMapper;
import com.mbyte.easy.mapper.SysUserRolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制类
 * 
 * @author 王震
 *
 */
@Controller
@Transactional
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysUserRolesMapper userRolesMapper;
	@Autowired
	private SysResourceMapper resourceMapper;
	@Autowired
	private SysRoleResourcesMapper roleResourceMapper;

	@ModelAttribute("resourceList")
	public List<SysResource> resourceList() {
		List<SysResource> resourceList = resourceMapper.selectByResource(null);
		return resourceList;
	}

	/**
	 * 进入角色管理界面
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('/role')")
	@RequestMapping(value = { "", "/" })
	public String index(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		SysRole role = new SysRole(); 
		if (name != null && !"".equals(name.trim())) {
			role.setName(name.trim());
			model.addAttribute("name", name.trim());
		}
//		PageHelper.startPage(pageNo, pageSize);
//		List<SysRole> list = roleMapper.selectByRole(role);
//		//一个一个将角色资源列表加入
//		for(int i = 0; i < list.size(); i++) {
//			SysRole r = list.get(i);
//			List<SysResource> resources = resourceMapper.selectResourceByRoleId(r.getId());
//			r.setResources(resources);
//			list.set(i, r);
//		}
//		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
//		if (pageNo > pageInfo.getPages()) {
//			pageNo = pageInfo.getPages();
//			PageHelper.startPage(pageNo, pageSize);
//			list = roleMapper.selectByRole(role);
//			for(int i = 0; i < list.size(); i++) {
//				SysRole r = list.get(i);
//				List<SysResource> resources = resourceMapper.selectResourceByRoleId(r.getId());
//				r.setResources(resources);
//				list.set(i, r);
//			}
//			pageInfo = new PageInfo<SysRole>(list);
//		}
//		model.addAttribute("pageInfo", pageInfo);
		return "admin-role";
	}

	/**
	 * 添加角色
	 * 
	 * @param model
	 * @param resource
	 * @return
	 */
	@PreAuthorize("hasAuthority('/role/add-role')")
	@RequestMapping(value = "/add-role")
	public String addRoleBefore(Model model, @ModelAttribute(value = "role") SysRole role) {
		return "admin-role-add";
	}

	@ResponseBody
	@RequestMapping(value = "/add-role", params = "save=true")
	public String addRole(Model model, @ModelAttribute(value = "role") SysRole role,
			@RequestParam(required = false) String roleResources) {
		SysRoleResources sysRoleResource = new SysRoleResources();
		if (role != null && role.getName() != null && !"".equals(role.getName())) {
			roleMapper.insert(role);
			role = roleMapper.selectByRolename(role.getName());
			if (!"".equals(roleResources) && roleResources != null) {
				// 权限资源字段处理
				String[] resourceIds = roleResources.split(",");
				for (String ids : resourceIds) {
					Long id = Long.valueOf(ids);
					sysRoleResource.setResourcesId(id);
					sysRoleResource.setSysRoleId(role.getId());
					roleResourceMapper.insert(sysRoleResource);
				}
			}
			return "1";
		}
		return "0";
	}

	/**
	 * 编辑角色权限
	 * 
	 * @param model
	 * @param resource
	 * @return
	 */
	@PreAuthorize("hasAuthority('/role/editor-role')")
	@RequestMapping(value = "/editor-role/{id}")
	public String editorRole(Model model, @ModelAttribute(value = "role") SysRole role, @PathVariable("id") Long id) {
		role = roleMapper.selectByPrimaryKey(id);
		model.addAttribute("role", role);
		// 获取所有的资源
		List<SysResource> resourceList = resourceMapper.selectByResource(null);
		model.addAttribute("resourceList", resourceList);
		// 获取选中资源
		List<SysResource> resources = resourceMapper.selectResourceByRoleId(role.getId());
		model.addAttribute("resources", resources);
		return "admin-role-editor";
	}

	@ResponseBody
	@RequestMapping(value = "/editor-role", params = "save=true")
	public String editRole(Model model, @ModelAttribute(value = "role") SysRole role,
			@RequestParam(required = false) String roleResources) {
		if (role != null) {
			SysRoleResources sysUserRoles = new SysRoleResources();
			roleMapper.updateByPrimaryKey(role);
			if (!"".equals(roleResources) && roleResources != null) {
				roleResourceMapper.deleteByRoleId(role.getId());
				// 权限资源字段处理
				String[] roleIds = roleResources.split(",");
				for (String ids : roleIds) {
					Long id = Long.valueOf(ids);
					sysUserRoles.setResourcesId(id);
					sysUserRoles.setSysRoleId(role.getId());
					roleResourceMapper.insert(sysUserRoles);
				}
			}
			return "1";
		}
		return "0";
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAuthority('/role/delete')")
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json", consumes = "application/json")
	public Integer delete(@RequestBody Long[] ids) {
		if (ids.length != 0 && ids != null) {
			for (long id : ids) {
				// 删除角色时候，删除资源和用户对应的角色
				roleResourceMapper.deleteByRoleId(id);
				userRolesMapper.deleteUserRolesById(id);
				roleMapper.deleteByPrimaryKey(id);
			}
			return 1;
		}
		return 0;
	}

}
