package com.demo.zk.qnews.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * ClassName:com.demo.zk.qnews.dagger.scope
 * Author: zk<p>.
 * time: 2016/10/3 11:09.
 * Function:  创建一个 @Qualifier 注解，用于区分两类程序员：
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value() default "Application";
}
