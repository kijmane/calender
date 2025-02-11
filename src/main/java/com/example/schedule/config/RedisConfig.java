package com.example.schedule.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // RedisTemplate를 빈으로 등록하여 Redis와의 연결 설정
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // Redis 연결 설정
        template.setConnectionFactory(redisConnectionFactory);
        // Redis 키와 값에 대한 직렬화 방식 설정 (문자열 형식으로 직렬화)
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        // 설정된 RedisTemplate 반환
        return template;
    }
}