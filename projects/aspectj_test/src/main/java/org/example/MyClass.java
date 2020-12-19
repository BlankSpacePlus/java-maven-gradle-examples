package org.example;

public class MyClass {

    public void foo(int number,String name){
        System.out.println("inside foo(int,String)\t" + number + '\t' + name);
    }

    public static void main(String[] args) {
        MyClass myObject = new MyClass();
        myObject.foo(1, "Sam");
    }

}
