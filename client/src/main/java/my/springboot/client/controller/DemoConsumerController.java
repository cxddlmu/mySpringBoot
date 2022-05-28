package my.springboot.client.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import my.springboot.client.redis.RedisLockUtil;
import my.springboot.client.redis.RedisUtils;
import my.springboot.server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by cuixiaodong on 2018/7/15.
 */
@RestController
public class DemoConsumerController {

    @Reference
    private DemoService demoService;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisLockUtil redisLockUtil;

    /**
     * @param name
     * @return
     */
    @RequestMapping("/sayHello")
    public Object sayHello(@RequestParam String name) {
        String a = null;
        Object a1 = redisUtils.get("helpTopic:id:0");
        if (a1 != null) {
            System.out.println(a1);

           return a1;
        } else {

            a = demoService.sayHello(name);
            System.out.println(a);
        }


        return a;
    }
    @RequestMapping("/login")
    public void login(@RequestParam String name,HttpSession httpSession){
        httpSession.setAttribute("name1",name);
    }


}
