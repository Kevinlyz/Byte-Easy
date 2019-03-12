package com.mbyte.easy.sys.service.impl;

import com.mbyte.easy.sys.entity.User;
import com.mbyte.easy.sys.mapper.UserMapper;
import com.mbyte.easy.sys.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
