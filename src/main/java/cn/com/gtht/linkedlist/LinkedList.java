package cn.com.gtht.linkedlist;


import java.util.Objects;

/**
 * Author: zhongzhu.wang
 * Date:2025/12/30 10:28
 */
public class LinkedList<T> {

    static class Node<T>{
        T data;
        Node<T> next;
    }

    Node<T> head;

    public LinkedList(Node<T> head) {
        this.head = head;
    }


    public void insert(T data){
        Node<T> node = new Node<T>();
        node.data = data;

        Node<T> tmp = head;

        if(Objects.isNull(tmp)){
            head = node;
            return;
        }

        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = node;
    }
}
