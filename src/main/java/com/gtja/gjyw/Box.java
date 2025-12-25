package com.gtja.gjyw;


import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhongzhu.wang
 * Date:2025/12/8 08:59
 */
@SuppressWarnings("unchecked")
public class Box<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Box(T data) {
        this.data = data;
    }

    public Box() {
    }

    public static void main(String[] args) {
        Box<Integer> bi;
        bi = createBox();

        Map m = new HashMap();
        m.put(1, 3);
        m.put(null, 4);

        System.out.println(m.get(null));
    }

    static Box createBox(){
        return new Box();
    }
}
