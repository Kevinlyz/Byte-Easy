package com.mbyte.easy;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyApplicationTests {

    @Autowired
    private ITestService iTestService;

    @Test
    public void contextLoads() {
        com.mbyte.easy.admin.entity.Test test = new com.mbyte.easy.admin.entity.Test();
        Page<com.mbyte.easy.admin.entity.Test> page = new Page<com.mbyte.easy.admin.entity.Test>(1, 20);
//        QueryWrapper<com.mbyte.easy.admin.entity.Test> diseaseQueryWrapperw = new QueryWrapper<com.mbyte.easy.admin.entity.Test>(test);
        IPage<com.mbyte.easy.admin.entity.Test> pageInfo = iTestService.page(page, null);
        System.out.println(JSON.toJSONString(pageInfo));

    }



}
