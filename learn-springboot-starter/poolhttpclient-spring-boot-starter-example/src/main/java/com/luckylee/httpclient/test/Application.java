package com.luckylee.httpclient.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication
@RestController
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Resource
    private CloseableHttpClient httpClient;

    @GetMapping("/checkHttpClient")
    public String check() {
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        String r = "";
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            r = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }
        log.info(r);
        return r;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
