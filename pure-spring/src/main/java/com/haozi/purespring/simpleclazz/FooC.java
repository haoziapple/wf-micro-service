package com.haozi.purespring.simpleclazz;

public class FooC {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void foo() {
        System.out.println("FooC.foo() invoked!");
    }
}
