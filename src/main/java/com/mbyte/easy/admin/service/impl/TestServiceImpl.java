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
 * @author 王震
 * @since 2019-03-11
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
