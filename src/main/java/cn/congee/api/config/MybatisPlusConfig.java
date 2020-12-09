package cn.congee.api.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:29
 */
@Slf4j
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        log.info("=====================================Mybatis Plus=======================================");
        return new PaginationInterceptor();
    }

}
