package com.demo.zk.qnews.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * ClassName:com.demo.zk.qnews.dagger.scope
 * Author: zk<p>.
 * time: 2016/10/3 11:11.
 * Function: @PerApp是一个自定义的范围注解，
 * 作用是允许对象被记录在正确的组件中，当然这些对象的生命周期应该遵循app的生命周期
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
