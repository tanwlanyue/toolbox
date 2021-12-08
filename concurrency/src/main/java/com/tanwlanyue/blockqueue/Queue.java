package com.tanwlanyue.blockqueue;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhanglei211 on 2021/10/4.
 */
public class Queue {
    private ReentrantLock lock;
    private Condition notEmpty;
    LinkedList<Object> list;
    int capacity;
    volatile int size=0;
    public Queue(int capacity) {
        lock= new ReentrantLock();
        notEmpty = lock.newCondition();
        this.capacity=capacity;
        this.list = new LinkedList<>();
    }

    public void offer(Object obj){
        lock.lock();
        try {
            while (size==capacity){
                notEmpty.await();
            }
            list.offer(obj);
            notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void take(Object obj){
        lock.lock();
        try {
            while (size==0){
                notEmpty.await();
            }
            Object poll = list.poll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
