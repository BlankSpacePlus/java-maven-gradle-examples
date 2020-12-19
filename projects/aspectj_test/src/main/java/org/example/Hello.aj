package org.example;

/**
 * aspectj经过编译后产生的还是.class文件，这是一种低侵入式的设计
 */
public aspect Hello {

    pointcut callPointCut() : call(void org.example.MyClass.foo(int,String));

    before() : callPointCut() {
        System.out.println("Hello AspectJ!");
        System.out.println("in the advice attached to the call pointcut");
    }

}
