package com.demo.zk.qnews.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * ClassName:com.demo.zk.qnews.dagger.scope
 * Author: zk<p>.
 * time: 2016/10/3 15:07.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
