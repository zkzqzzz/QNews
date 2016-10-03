package com.demo.zk.qnews.dagger;

/**
 * ClassName:com.demo.zk.qnews.dagger
 * Author: zk<p>.
 * time: 2016/10/3 11:18.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class dagger {

    /**
     * @Inject 和 @Provide 两种依赖生成方式区别

    a. @Inject 用于注入可实例化的类，@Provides 可用于注入所有类
    b. @Inject 可用于修饰属性和构造函数，可用于任何非 Module 类，@Provides 只可用于用于修饰非构造函数，并且该函数必须在某个Module内部
    c. @Inject 修饰的函数只能是构造函数，@Provides 修饰的函数必须以 provide 开头

     */
    /**    @Inject:
     *     1.构造方法注入：在类的构造方法前面注释@Inject
           2.成员变量注入：在类的成员变量（非私有）前面注释@Inject
           3.函数方法注入：在函数前面注释@Inject
     */

    /**
     *      @Provide:
           1,接口(Interface)是没有构造函数的，当然就不能对构造函数进行注解
           2,第三方库提供的类，我们无法修改源码，因此也不能注解它们的构造函数
           3,有些类需要提供统一的生成函数(一般会同时私有化构造函数)或需要动态选择初始化的配置，而不是使用一个单一的构造函数
            对于以上三种情况，可以使用 @Provides 注解来标记自定义的生成函数，从而被 Dagger 调用
     */

    /**
     * @Qualifier的使用(限定符)

     (1).创建一个 @Qualifier 注解，用于区分两类程序员：
        @Qualifier
        @Documented
        @Retention(RUNTIME)
        public @interface Level {
        String value() default "";
        }

     (2). 为这两类程序员分别设置 @Provides 函数，并使用 @Qualifier 注解对他们做出不同的标记：

        @Provides @Level("low") Coder provideLowLevelCoder() {
        Coder coder = new Coder();
        coder.setName("战五渣");
        coder.setPower(5);
        return coder;
        }

        @Provides @Level("high") Coder provideHighLevelCoder() {
        Coder coder = new Coder();
         coder.setName("大神");
        coder.setPower(1000);
        return coder;
        }

        (3). 在声明 @Inject 对象的时候，加上对应的 @Qualifier 注解。

        @Inject @Level("low") Coder lowLevelCoder;
        @Inject @Level("high") Coder highLevelCoder;
     *
     */

}
