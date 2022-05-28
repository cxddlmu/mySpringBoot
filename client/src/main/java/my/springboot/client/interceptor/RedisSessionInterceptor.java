package my.springboot.client.interceptor;

import my.springboot.client.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截登录失效的请求
public class RedisSessionInterceptor implements HandlerInterceptor
{
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
        HttpSession session = request.getSession();
        System.out.println("spring:session:sessions:"+session.getId());
        System.out.println(redisUtils.hasKey("spring:session:sessions:expires:"+session.getId()));
        System.out.println(redisUtils.hget("spring:session:sessions:"+session.getId(),"sessionAttr:name1"));
        String name = (String) redisUtils.hget("spring:session:sessions:" + session.getId(), "sessionAttr:name1");
        System.out.println(redisUtils.getExpire("spring:session:sessions:expires:"+session.getId()));
        if (name != null && redisUtils.hasKey("spring:session:sessions:expires:"+session.getId()))
        {
            return true;
        }else{

            response401(response);
            return false;
        }


    }

    private void response401(HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.setStatus(401);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }
}
