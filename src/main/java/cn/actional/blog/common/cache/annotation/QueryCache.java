package cn.actional.blog.common.cache.annotation;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;

import java.lang.annotation.*;

/**
 *      标识查询数据库的方法，有该注解表示先查询缓存  nameSpace 用于key值
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryCache {
    CacheNameSpaceEnum nameSpace();
}
