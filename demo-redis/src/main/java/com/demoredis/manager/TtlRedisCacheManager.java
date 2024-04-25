package com.demoredis.manager;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import java.time.Duration;

public class TtlRedisCacheManager extends RedisCacheManager {
    //d：天；h：小时；m：分钟；s：秒
    private static final String d = "d";
    private static final String h = "h";
    private static final String m = "m";
    private static final String s = "s";

    public TtlRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        String[] cells = StringUtils.delimitedListToStringArray(name, "=");
        name = cells[0].trim();
        if (cells.length > 1) {
            cacheConfig = entryTtl(cacheConfig, cells[1].trim().toLowerCase());
        }
        return super.createRedisCache(name, cacheConfig);
    }

    /**
     *
     * 根据传参设置缓存失效时间，并兼容单位（默认单位是h）
     * @param cacheConfig redis缓存配置
     * @param ttlStr 带单位的缓存有效期值 d：天；h：小时；m：分钟；s：秒（eg: 1d代表一天；2h代表2个小时；）
     * @return {@link RedisCacheConfiguration}
     */
    private RedisCacheConfiguration entryTtl(RedisCacheConfiguration cacheConfig, String ttlStr) {

        ttlStr = ttlStr.toLowerCase();

        if (ttlStr.endsWith(d)) {
            return cacheConfig.entryTtl(Duration.ofDays(Long.parseLong(ttlStr.replace(d, ""))));
        } else if (ttlStr.endsWith(h)) {
            return cacheConfig.entryTtl(Duration.ofHours(Long.parseLong(ttlStr.replace(h, ""))));
        } else if (ttlStr.endsWith(m)) {
            return cacheConfig.entryTtl(Duration.ofMinutes(Long.parseLong(ttlStr.replace(m, ""))));
        } else if (ttlStr.endsWith(s)) {
            return cacheConfig.entryTtl(Duration.ofSeconds(Long.parseLong(ttlStr.replace(s, ""))));
        } else {
            return cacheConfig.entryTtl(Duration.ofHours(Long.parseLong(ttlStr.replaceAll("\\D", ""))));
        }
    }
}
