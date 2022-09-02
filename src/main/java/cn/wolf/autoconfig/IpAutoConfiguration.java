package cn.wolf.autoconfig;

import cn.wolf.inceptor.SpringMvcConfig;
import cn.wolf.properties.IpProperties;
import cn.wolf.service.IpCountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Import({IpProperties.class, SpringMvcConfig.class})
public class IpAutoConfiguration {

    @Bean
    public IpCountService ipCountService(){
        return new IpCountService();
    }
}
