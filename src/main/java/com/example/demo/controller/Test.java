package com.example.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/9/26.
 */
@RequestMapping(value = "/test")
@RestController
@DefaultProperties(defaultFallback="DefaultHystrix")
public class Test {
    @Autowired
    private RestTemplate restTemplate;
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    }
//    )
    @HystrixCommand
    @GetMapping("/test")
    public String test(@RequestParam int num){
       String res = "";
       res=restTemplate.postForObject("http://client/welcome/testHystrix",num,String.class);
       return res;
    }
    @HystrixCommand
    @GetMapping("/defaultest")
    public String defaultest(){
        String res = "";
        throw new RuntimeException("运行异常");
        //return res;
    }
    public String testHystrix(int num){
        return "休息休息一下";
    }
    public String DefaultHystrix(){
        return "默认方法:休息休息一下";
    }
}
