package cn.wolf.inceptor;

import cn.wolf.service.IpCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpCountInterceptor implements HandlerInterceptor {

    @Autowired
    private IpCountService ipCountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 调用web层方法之前执行该操作
        ipCountService.count();
        return true;
    }

}
