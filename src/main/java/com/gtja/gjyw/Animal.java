package com.gtja.gjyw;
/**
 * Author: zhongzhu.wang
 * Date:2025/12/3 13:27
 */
public class Animal {


    static int y = 1;

    static {
        x = 1;
    }
    private int a = 0;
    Foo foo = new Foo();
    {
        a= 1;b =2;
    }
    static int x = 0;

    public Animal() {
        System.out.println("aaa");
    }

    private int b = 1;

    public static void main(String[] args) {
        System.out.println(x);
        Animal a = new Animal();
    }
}
