package com.mbyte.easy.test.service.impl;

import com.mbyte.easy.test.entity.Test;
import com.mbyte.easy.test.mapper.TestMapper;
import com.mbyte.easy.test.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黄润宣
 * @since 2019-03-12
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
