package com.example.demo.config;

import com.example.demo.infraestructuras.helpers.CacheConstantes;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class RedisConfig {

    @Value(value = "${cache.redis.address}")
    private String serverAddreess;

    @Value(value = "${cache.redis.password}")
    private String serverPassWord;

    @Bean
    public RedissonClient redissonClient(){
        var config=new Config();
        config.useSingleServer()
                .setAddress(serverAddreess)
                .setPassword(serverPassWord);
        return Redisson.create(config);

    }

    @Bean
    @Autowired
    public CacheManager cacheManager(RedissonClient redissonClient) {
        var configs = Map.of(
                CacheConstantes.FLY_CACHE_NAME, new CacheConfig(),
                CacheConstantes.HOTEL_CACHE_NAME, new CacheConfig()
        );
        return new RedissonSpringCacheManager(redissonClient, configs);
    }


    @CacheEvict(cacheNames = {
            CacheConstantes.FLY_CACHE_NAME,
            CacheConstantes.HOTEL_CACHE_NAME

    },allEntries = true)
    @Scheduled(cron = CacheConstantes.SCHEDULED_RESET_CACHE)// media noche se limpie
    @Async
    public void deleteCache(){
        log.info("Limpiando cache");
    }

}
