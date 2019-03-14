package com.mbyte.easy.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.sys.entity.TPerson;
import com.mbyte.easy.sys.service.ITPersonService;
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
 * @author 
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/sys/tPerson")
public class TPersonController extends BaseController  {

    private String prefix = "sys/tPerson/";

    @Autowired
    private ITPersonService tPersonService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param tPerson
     * @return
     */
    @RequestMapping
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, TPerson tPerson) {

        Page<TPerson> page = new Page<TPerson>(pageNo, pageSize);
        QueryWrapper<TPerson> queryWrapper = new QueryWrapper<TPerson>();
//        queryWrapper.lambda()
//                .eq(Test::getName, "1");
        IPage<TPerson> pageInfo = tPersonService.page(page, queryWrapper);

        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tPerson-list";

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
     * @param tPerson
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TPerson tPerson){
        return toAjax(tPersonService.save(tPerson));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tPerson",tPersonService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param tPerson
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TPerson tPerson){
        return toAjax(tPersonService.updateById(tPerson));
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tPersonService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tPersonService.removeByIds(ids));
    }

}

