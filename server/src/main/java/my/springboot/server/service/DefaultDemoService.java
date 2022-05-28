package my.springboot.server.service;

import com.alibaba.dubbo.config.annotation.Service;
import my.springboot.server.entity.HelpTopic;
import my.springboot.server.redis.RedisUtils;
import my.springboot.server.repository.HelpTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Created by cuixiaodong on 2018/7/15.
 */
@Service
@org.springframework.stereotype.Service
public class DefaultDemoService implements DemoService {

    @Autowired
    private HelpTopicRepository helpTopicRepository;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @param name
     * @return
     */
    public String sayHello(String name) {
        Optional<HelpTopic> helpTopic = helpTopicRepository.findById(0);
        if(helpTopic.isPresent()){
            System.out.println(redisUtils.get("helpTopic:id:0"));
//            redisTemplate.boundValueOps("helpTopic:0").set(helpTopic.get().getDescription());
            redisUtils.set("helpTopic:id:0",helpTopic.get(),1000L);
        }
        System.out.println(helpTopicRepository.findAll().toArray().toString());
        return "Hello, " + name + " (from Spring Boot)";
    }



}