package cn.congee.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;

/**
 * @Author: yang
 * @Date: 2020-12-10 4:28
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"cn.congee.api*.mapper"})
public class SpringbootJwtMybatisplusApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootJwtMybatisplusApplication.class);
    }

    @Value("${server.port}")
    private String SERVER_PORT;

    private static String SPRING_EL_ASPECT;

    @PostConstruct
    private void init(){
        SPRING_EL_ASPECT = SERVER_PORT;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(SpringbootJwtMybatisplusApplication.class, args);
        long end = System.currentTimeMillis();
        log.info("服务启动耗时: " + (end - start) + "ms,服务端口: " + SPRING_EL_ASPECT);
    }

}
