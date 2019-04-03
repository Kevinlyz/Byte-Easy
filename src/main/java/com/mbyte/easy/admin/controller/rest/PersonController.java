package com.mbyte.easy.admin.controller.rest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

/**
* <p>
* 前端控制器
* </p>
* @author 
* @since 2019-03-11
*/
@RestController("restPersonController")
@RequestMapping("/rest/person")
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
    public AjaxResult index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Person person) {
        LambdaQueryWrapper<Person> queryWrapper = new QueryWrapper<Person>().lambda();
        if(StringUtils.isNoneBlank(person.getName())){
            queryWrapper.eq(Person::getName,person.getName());
        }
        return success(personService.list(queryWrapper));
    }


    /**
    * 添加
    * @param person
    * @return
    */
    @RequestMapping("add")
    public AjaxResult add(Person person){
        return toAjax(personService.save(person));
    }

    /**
    * 添加
    * @param person
    * @return
    */
    @RequestMapping("edit")
    public AjaxResult edit(Person person){
        return toAjax(personService.updateById(person));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @RequestMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(personService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @RequestMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(personService.removeByIds(ids));
    }

}

