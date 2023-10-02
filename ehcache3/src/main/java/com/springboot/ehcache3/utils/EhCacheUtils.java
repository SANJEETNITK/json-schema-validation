package com.springboot.ehcache3.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class EhCacheUtils {

    private static final Logger LOG = LoggerFactory.getLogger(EhCacheUtils.class);

    @Autowired
    private CacheManager cacheManager;

    public Object getSchema(String cacheName, String cacheKey) {
        Cache schemaCache = cacheManager.getCache(cacheName);
        if (schemaCache == null) {
            LOG.info("No cache found with name = {}", cacheName);
            return null;
        }
        Cache.ValueWrapper cacheValueWrapper = schemaCache.get(cacheKey);
        if (cacheValueWrapper == null) {
            LOG.info("No cache found for key = {}", cacheKey);
            return null;
        }
        return cacheValueWrapper.get();
    }

    public void evictSingleCacheValue(String cacheName, String cacheKey) {
        try {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache == null) {
                LOG.info("No cache found with name = {}", cacheName);
                return;
            }
            cache.evict(cacheKey);
            LOG.info("Cache value cleared for {}", cacheKey);
        } catch (Exception e){
            LOG.error("Exception while evicting single cache value for key {}. Exception = {}", cacheKey, e.getStackTrace());
        }
    }

}
