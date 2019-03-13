package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Fu;
import com.mbyte.easy.admin.service.IFuService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/admin/fu")
public class FuController extends BaseController {

    private String prefix = "admin/fu/";

    @Autowired
    private IFuService fuService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param fu
     * @return
     */
    @RequestMapping
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
            , String login_timeSpace
            , Fu fu
    ) {

        Page<Fu> page = new Page<Fu>(pageNo, pageSize);
        QueryWrapper<Fu> queryWrapper = new QueryWrapper<Fu>();
//        queryWrapper.lambda()
//                .eq(Test::getName, "1");
        if (!StringUtils.isBlank(fu.getName())) queryWrapper = queryWrapper.like("name", fu.getName());
        if (!StringUtils.isBlank(fu.getPassword())) queryWrapper = queryWrapper.like("password", fu.getPassword());
        if (!StringUtils.isBlank(login_timeSpace))
            queryWrapper = queryWrapper.between("login_time", LocalDateTime.parse(login_timeSpace.split(" - ")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse(login_timeSpace.split(" - ")[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        IPage<Fu> pageInfo = fuService.page(page, queryWrapper);

        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix + "fu-list";

    }


    /**
     * 添加跳转页面
     *
     * @return
     */
    @GetMapping("addBefore")
    public String addBefore() {
        return prefix + "add";
    }

    /**
     * 添加
     *
     * @param fu
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Fu fu) {
        return toAjax(fuService.save(fu));
    }

    /**
     * 添加跳转页面
     *
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model, @PathVariable("id") Long id) {
        model.addAttribute("fu", fuService.getById(id));
        return prefix + "edit";
    }

    /**
     * 添加
     *
     * @param fu
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Fu fu) {
        return toAjax(fuService.updateById(fu));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id) {
        return toAjax(fuService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List
            <Long> ids) {
        return toAjax(fuService.removeByIds(ids));
    }

}

