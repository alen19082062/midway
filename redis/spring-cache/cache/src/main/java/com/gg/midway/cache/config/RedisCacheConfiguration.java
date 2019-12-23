package com.gg.midway.cache.config;

//@Configuration
//@ConditionalOnClass(RedisConnectionFactory.class)
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//@ConditionalOnBean(RedisConnectionFactory.class)
//@ConditionalOnMissingBean(CacheManager.class)
//@Conditional(CacheCondition.class)
//class RedisCacheConfiguration {
//    @Bean
//    public RedisCacheManager cacheManager(
//            RedisConnectionFactory redisConnectionFactory,
//            ResourceLoader resourceLoader) {
//        RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(determineConfiguration(resourceLoader.getClassLoader()));
//        List<String> cacheNames = this.cacheProperties.getCacheNames();
//
//        if (!cacheNames.isEmpty()) {
//            build.initialCacheNames(new LinkedHashSet<>(cacheNames));
//        }
//        return this.customizerInvoker.customize(builder.build());
//    }
//}
