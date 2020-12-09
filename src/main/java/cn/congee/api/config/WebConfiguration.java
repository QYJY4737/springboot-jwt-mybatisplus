package cn.congee.api.config;

import cn.congee.api.interceptor.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:30
 */
@Slf4j
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 配置跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600)
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedOrigins("*");
        log.info("=====================================解决跨域问题成功=======================================");
    }

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        // 排除拦截
        excludePath.add("/userKmmy/**");  //登录
        excludePath.add("/webjars/**");
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源
        log.info("=====================================通过登录拦截器=======================================");
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
