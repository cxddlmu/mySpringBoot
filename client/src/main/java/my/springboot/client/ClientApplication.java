package my.springboot.client;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=30)
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
