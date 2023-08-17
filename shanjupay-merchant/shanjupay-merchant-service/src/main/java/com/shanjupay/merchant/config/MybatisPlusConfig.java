package com.shanjupay.merchant.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: shanjupay
 * @ClassName MybatisPlusConfig
 * @description:
 * @author: wen
 * @create: 2023-08-15 19:55
 * @Version 1.0
 **/
@Configuration
@MapperScan("com.shanjupay.merchant.mapper")
public class MybatisPlusConfig {

    /** @Author wen
     * @Description //TODO 
     * @Date 19:57 2023/8/15
     * @Param 
     * @return 
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    
    
    /** @Author wen
     * @Description //TODO 
     * @Date 20:03 2023/8/15
     * @Param 
     * @return 
     **/
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }
    
}