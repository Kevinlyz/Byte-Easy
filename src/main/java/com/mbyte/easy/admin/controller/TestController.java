package com.mbyte.easy.admin.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Test;
import com.mbyte.easy.admin.service.ITestService;
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
 * @author 王震
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/admin/test")
public class TestController extends BaseController {

    private String prefix = "admin/test/";

    @Autowired
    private ITestService testService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param test
     * @return
     */
    @RequestMapping
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Test test) {

        Page<Test> page = new Page<Test>(pageNo, pageSize);
        QueryWrapper<Test> queryWrapper = new QueryWrapper<Test>();
//        queryWrapper.lambda()
//                .eq(Test::getName, "1");
        IPage<Test> pageInfo = testService.page(page, queryWrapper);

        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"test-list";

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
    public AjaxResult add(Test test){
        return toAjax(testService.save(test));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("test",testService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param test
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Test test){
        return toAjax(testService.updateById(test));
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(testService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(testService.removeByIds(ids));
    }

}

