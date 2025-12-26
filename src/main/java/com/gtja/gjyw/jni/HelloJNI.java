package com.gtja.gjyw.jni;


/**
 * Author: zhongzhu.wang
 * Date:2025/12/26 16:40
 */
public class HelloJNI {

    static {
        // 加载本地库 libhello.so / libhello.dylib
        System.loadLibrary("hello");
    }

    // 声明 native 方法
    public static native void sayHello();

    public static void main(String[] args) {
        sayHello();
    }
}
