package cn.actional.blog.common.cache.annotation;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateCache {
    CacheNameSpaceEnum[] nameSpace();
}
