package com.mbyte.easy.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Test;
import com.mbyte.easy.admin.service.ITestService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private ITestService testService;

    @RequestMapping
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Test test) {

        Page<Test> page = new Page<Test>(pageNo, pageSize);
        QueryWrapper<Test> diseaseQueryWrapperw = new QueryWrapper<Test>(test);
        IPage<Test> pageInfo = testService.page(page, diseaseQueryWrapperw);

        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return "admin/test/test-list";

    }
}

