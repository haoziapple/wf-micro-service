package com.haozi.purespring.beanuser;

import com.haozi.purespring.simpleclazz.FooA;
import com.haozi.purespring.simpleclazz.FooB;
import com.haozi.purespring.simpleclazz.FooC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 动态创建bean的使用者
 *
 * @author user
 */
@Component
public class BeanUser {
    private FooA fooA;

    private FooB fooB;

    private FooC fooC;

    @Autowired
    public void setFooA(FooA fooA) {
        System.out.println("动态创建成功:" + fooA.getA() + " " + fooA.getB());
        this.fooA = fooA;
    }

    @Autowired
    public void setFooB(FooB fooB) {
        System.out.println("动态创建成功:" + fooB.getName());
        fooB.foo();
        this.fooB = fooB;
    }

    @Autowired
    public void setFooC(FooC fooC) {
        System.out.println("动态创建成功:" + fooC.getName());
        fooC.foo();
        this.fooC = fooC;
    }
}
