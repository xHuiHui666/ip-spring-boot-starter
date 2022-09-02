package cn.wolf.service;

import cn.wolf.properties.IpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableScheduling
public class IpCountService {

    private Map<String,Integer> ipCountMap  = new HashMap<>();

    @Autowired
    // 当前的request对象的注入工作由使用当前starter的工程提供自动装配
    private HttpServletRequest request;

    public void count(){
        // 每次调用当前操作,就记录当前访问的IP,然后累加访问次数
        // 1. 获取当前操作的IP地址
        String ip = request.getRemoteAddr();
        // 2. 根据IP地址从Map中取值,并递增
        Integer count = ipCountMap.get(ip);
        if (count == null){

            // 第一次访问的时候次数就为1
            ipCountMap.put(ip,1);

        }else {
            // 后面访问的时候次数加1
            ipCountMap.put(ip,count + 1);
        }
    }

    @Autowired
    private IpProperties ipProperties;


    @Scheduled(cron = "0/#{ipProperties.cycle} * * * * ?")
    public void print(){

        if (ipProperties.getModel().toUpperCase().equals(IpProperties.LogModel.DETAIL.getValue())){
            // 详细模式
            System.out.println("      IP访问监控");
            System.out.println("+-----ip-address-----+--num--+");
            for (Map.Entry<String, Integer> entry : ipCountMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(String.format("|%18s   |%5d  |",key,value));
            }
            System.out.println("+--------------------+-------+");

        }else if (ipProperties.getModel().toUpperCase().equals(IpProperties.LogModel.SIMPLE.getValue())){
            // 极简模式
            System.out.println("      IP访问监控");
            System.out.println("+-----ip-address-----+");
            for (String key : ipCountMap.keySet()) {
                System.out.println(String.format("|%18s  |",key));
            }
            System.out.println("+--------------------+");
        }

        if (ipProperties.getCycleReset()){
         ipCountMap.clear();
        }
    }

}
