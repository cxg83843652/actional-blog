package cn.actional.blog.common.cache.aspect;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;
import cn.actional.blog.common.cache.annotation.QueryCache;
import cn.actional.blog.common.cache.annotation.UpdateCache;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * 缓存的切面
 */
@Aspect
@Service
public class CacheAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheAspect.class);


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 拦截带QueryCache注解的方法
     */
    @Pointcut("@annotation(cn.actional.blog.common.cache.annotation.QueryCache)")
    public void queryCachePointcut() {}

    /**
     * 拦截带UpdateCache注解的方法
     */
    @Pointcut("@annotation(cn.actional.blog.common.cache.annotation.UpdateCache)")
    public void updateCachePointcut() {}



    /**
     * 拦截器具体实现
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("queryCachePointcut()")
    public Object queryInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();

        CacheNameSpaceEnum cacheNameSpace = method.getAnnotation(QueryCache.class).nameSpace();
        String key = cacheNameSpace.name();



        if (StringUtils.isBlank(key)) {
            throw new Exception();
        }

        LOGGER.info("获取到缓存key值 >>>> " + key);
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();

        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            //缓存中有数据，从缓存获取
            Object object = operations.get(key);
            LOGGER.info("从缓存中获取到数据 >>>> " + object.toString());
            return object;
        }

        //没有数据时，调用原始方法查询数据库
        Object object = pjp.proceed();

        operations.set(key,object,30, TimeUnit.MINUTES);
        LOGGER.info("mysql取到数据并存入缓存 >>>> " + object.toString());

        return object;
    }

    @Around("updateCachePointcut()")
    public void updateInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        CacheNameSpaceEnum[] nameSpace = method.getAnnotation(UpdateCache.class).nameSpace();
        for (int i = 0; i < nameSpace.length; i++) {
            String key = nameSpace[i].name();
            if (StringUtils.isBlank(key)) {
                throw new Exception();
            }
            Boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                Boolean delete = redisTemplate.delete(key);
            }
            pjp.proceed();
        }

    }


}
