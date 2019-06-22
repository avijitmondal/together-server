package com.avijit.together.gateway;

import com.avijit.together.gateway.filter.ErrorFilter;
import com.avijit.together.gateway.filter.PostFilter;
import com.avijit.together.gateway.filter.PreFilter;
import com.avijit.together.gateway.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class TogetherGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TogetherGatewayApplication.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

}