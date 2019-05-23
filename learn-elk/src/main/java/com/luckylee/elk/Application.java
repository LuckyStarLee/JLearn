package com.luckylee.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @SysLog
    @RequestMapping(value = "/getDataList")
    public String getInputDataList(@RequestBody String vo) {
        return vo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
