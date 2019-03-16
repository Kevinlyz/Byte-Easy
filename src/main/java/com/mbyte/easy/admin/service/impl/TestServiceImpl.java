package com.mbyte.easy.admin.service.impl;

import com.mbyte.easy.admin.entity.Test;
import com.mbyte.easy.admin.mapper.TestMapper;
import com.mbyte.easy.admin.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2019-03-13
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
