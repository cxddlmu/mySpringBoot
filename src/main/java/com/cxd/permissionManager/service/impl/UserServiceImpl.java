package com.cxd.permissionManager.service.impl;

import com.cxd.permissionManager.entity.User;
import com.cxd.permissionManager.mapper.UserMapper;
import com.cxd.permissionManager.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxd
 * @since 2018-01-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public User selectUserByUserNo(String userNo) {
        return baseMapper.selectUserByUserNo(userNo);
    }
}
