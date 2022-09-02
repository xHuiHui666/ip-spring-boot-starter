package cn.wolf.inceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(getIpCountInterceptor()).addPathPatterns("/**");// 拦截所有请求
    }

    @Bean
    public IpCountInterceptor getIpCountInterceptor(){
        return new IpCountInterceptor();
    }
}
