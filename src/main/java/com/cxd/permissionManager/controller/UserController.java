package com.cxd.permissionManager.controller;


import com.cxd.permissionManager.entity.User;
import com.cxd.permissionManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cxd
 * @since 2018-01-13
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{userNo}")
    public User getUserByUserNo(@PathVariable String userNo) {
        User user = new User();
        user.setUserNo(userNo);
//        return userService.selectOne(new EntityWrapper<User>(user));
        return userService.selectUserByUserNo(userNo);

    }
}

