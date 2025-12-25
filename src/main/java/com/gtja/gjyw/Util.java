package com.gtja.gjyw;


/**
 * Author: zhongzhu.wang
 * Date:2025/12/8 09:17
 */
public class Util {

    static <T> boolean equals(Box<T> box1, Box<T> box2){
        return box1.getData().equals(box2.getData());
    }

    public static void main(String[] args) {
        Util.<Integer>equals(new Box<>(1),new Box<>(3));
    }
}
