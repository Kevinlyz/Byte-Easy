package com.mbyte.easy.lxt.service.impl;

import com.mbyte.easy.lxt.entity.Test;
import com.mbyte.easy.lxt.mapper.TestMapper;
import com.mbyte.easy.lxt.service.ITestService;
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
