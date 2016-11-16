package demo.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Application configuration
 * @author rushikesh
 *
 */
@Configuration
public class AppConfiguration {

    @Bean
    public JedisPool jedisPool(@Value("${spring.redis.host}") String host
            ,@Value("${spring.redis.port}") int port
            ,@Value("${spring.redis.timeout}") int timeout
            ,@Value("${spring.redis.maxTotal}") int maxTotal
            ,@Value("${spring.redis.minIdle}") int minIdle
            ,@Value("${spring.redis.blockWhenExhausted}") boolean exhaustBlock) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setNumTestsPerEvictionRun(0);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setBlockWhenExhausted(exhaustBlock);
        return new JedisPool(poolConfig, host, port, timeout);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
