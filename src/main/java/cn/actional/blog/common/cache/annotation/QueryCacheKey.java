package cn.actional.blog.common.cache.annotation;


import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface QueryCacheKey {

}
