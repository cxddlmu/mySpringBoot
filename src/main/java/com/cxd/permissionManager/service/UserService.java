package com.cxd.permissionManager.service;

import com.cxd.permissionManager.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cxd
 * @since 2018-01-13
 */
public interface UserService extends IService<User> {
    User selectUserByUserNo(String userNo);
}
