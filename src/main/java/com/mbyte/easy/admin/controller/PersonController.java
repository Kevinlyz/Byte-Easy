package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Person;
import com.mbyte.easy.admin.service.IPersonService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* <p>
* 前端控制器
* </p>
* @author 黄润宣
* @since 2019-03-11
*/
@Controller
@RequestMapping("/admin/person")
public class PersonController extends BaseController  {

    private String prefix = "admin/person/";

    @Autowired
    private IPersonService personService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param person
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Person person) {
        Page<Person> page = new Page<Person>(pageNo, pageSize);
        QueryWrapper<Person> queryWrapper = new QueryWrapper<Person>();

        if(person.getName() != null  && !"".equals(person.getName() + "")) {
            queryWrapper = queryWrapper.like("name",person.getName());
         }


        if(person.getCode() != null  && !"".equals(person.getCode() + "")) {
            queryWrapper = queryWrapper.like("code",person.getCode());
         }

        IPage<Person> pageInfo = personService.page(page, queryWrapper);
        model.addAttribute("searchInfo", person);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"person-list";
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
    * @param person
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Person person){
        return toAjax(personService.save(person));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("person",personService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param person
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Person person){
        return toAjax(personService.updateById(person));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(personService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(personService.removeByIds(ids));
    }

}

