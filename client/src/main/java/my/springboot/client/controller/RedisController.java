package my.springboot.client.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import my.springboot.client.redis.RedisLockUtil;
import my.springboot.client.redis.RedisUtils;
import my.springboot.server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.Set;

@RestController
public class RedisController {
    @Reference
    private DemoService demoService;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisLockUtil redisLockUtil;

    @RequestMapping("/incr")
    public double incr(String name){
        return redisUtils.hincr("sum","start",1);
    }

//    @RequestMapping("/setScore")
//    public void setScore(Double score,String name){
//        redisUtils.addZset("rank",name,score);
//    }
    @RequestMapping("/setScore")
    public void setScore(){
        String keyModel = "o2o:cp:sortTest2";
        for (int i = 0; i < 10 ; i++) {
            int score = new Random().nextInt(50);
            redisUtils.addZset(keyModel,"H"+i,score);
        }
    }
    @RequestMapping("/getScores")
    public Double getScores(String name){
        return redisUtils.score("rank",name);
    }
    @RequestMapping("/rangeByScore")
    public Set<Object> rangeByScore(){
        return redisUtils.rangeByScore("rank",0,600);
    }
    @RequestMapping("/rangeByScoreWithScores")
    public Set<Object> rangeByScoreWithScores(){
        return redisUtils.rangeByScore("rank",0,600);
    }
    @RequestMapping("/lock")
    public Boolean lock(){
        return redisLockUtil.tryLock("lockKey");
    }
}
