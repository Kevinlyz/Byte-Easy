package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${cfg.superController};
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
    * 前端控制器
    * </p>
*
* @author ${author}
* @since 2019-03-11
*/
@Controller
<#--@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if><#if controllerMappingHyphenStyle>/${controllerMappingHyphen}<#else>/${table.entityPath}</#if>")-->
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/${entity ?uncap_first}")
public class ${table.controllerName} extends ${superControllerClass}  {

private String prefix = "<#if package.ModuleName??>${package.ModuleName}</#if>/${entity?uncap_first}/";

@Autowired
private ${table.serviceName} ${entity?uncap_first}Service;

/**
* 查询列表
*
* @param model
* @param pageNo
* @param pageSize
* @param ${entity?uncap_first}
* @return
*/
@RequestMapping
public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ${entity} ${entity?uncap_first}) {

Page<${entity}> page = new Page<${entity}>(pageNo, pageSize);
QueryWrapper<${entity}> queryWrapper = new QueryWrapper<${entity}>();
//        queryWrapper.lambda()
//                .eq(Test::getName, "1");
<#list table.fields as field >
    <#if (field.name != "id" && field.name != "create_time" && field.name != "update_time") >
        <#if (field.type == "datetime") >
            if(DateUtils.
        </#if>
        if(!StringUtils.isBlank(${entity?uncap_first}.get${field.name?cap_first}())) queryWrapper = queryWrapper.like("${field.name}",${entity?uncap_first}.get${field.name?cap_first}());
    </#if>
</#list>

IPage<${entity}> pageInfo = ${entity?uncap_first}Service.page(page, queryWrapper);

model.addAttribute("pageInfo", new PageInfo(pageInfo));
return prefix+"${entity?uncap_first}-list";

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
* @param ${entity?uncap_first}
* @return
*/
@PostMapping("add")
@ResponseBody
public AjaxResult add(${entity} ${entity?uncap_first}){
return toAjax(${entity?uncap_first}Service.save(${entity?uncap_first}));
}
/**
* 添加跳转页面
* @return
*/
@GetMapping("editBefore/{id}")
public String editBefore(Model model,@PathVariable("id")Long id){
model.addAttribute("${entity?uncap_first}",${entity?uncap_first}Service.getById(id));
return prefix+"edit";
}
/**
* 添加
* @param ${entity?uncap_first}
* @return
*/
@PostMapping("edit")
@ResponseBody
public AjaxResult edit(${entity} ${entity?uncap_first}){
return toAjax(${entity?uncap_first}Service.updateById(${entity?uncap_first}));
}
/**
* 删除
* @param id
* @return
*/
@GetMapping("delete/{id}")
@ResponseBody
public AjaxResult delete(@PathVariable("id") Long id){
return toAjax(${entity?uncap_first}Service.removeById(id));
}
/**
* 批量删除
* @param ids
* @return
*/
@PostMapping("deleteAll")
@ResponseBody
public AjaxResult deleteAll(@RequestBody List
<Long> ids){
    return toAjax(${entity?uncap_first}Service.removeByIds(ids));
    }

    }

