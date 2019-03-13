package com.mbyte.easy.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.sys.entity.User;
import com.mbyte.easy.sys.service.IUserService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 黄润宣
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController  {

    private String prefix = "/sys/user/";

    @Autowired
    private IUserService userService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param user
     * @return
     */
    @RequestMapping
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, User user) {

        Page<User> page = new Page<User>(pageNo, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.lambda()
//                .eq(Test::getName, "1");
        IPage<User> pageInfo = userService.page(page, queryWrapper);

        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"user-list";

    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("addBefore")
    public String addBefore(){
        return prefix+"add";
    }
    /**
     * 添加
     * @param test
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(User user){
        return toAjax(userService.save(user));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("user",userService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param test
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(User user){
        return toAjax(userService.updateById(user));
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(userService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(userService.removeByIds(ids));
    }

}

