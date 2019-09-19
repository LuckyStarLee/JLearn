package com.luckylee.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock test.
 *
 * @author Lucky Lee
 * @since 1.0
 */
@Slf4j
public class ReentrantLockWithCondition {

    private Stack<String> stack = new Stack<>();
    private int CAPACITY = 5;

    private ReentrantLock lock = new ReentrantLock();
    private Condition stackEmptyCondition = lock.newCondition();
    private Condition stackFullCondition = lock.newCondition();

    private void pushToStack(String item) throws InterruptedException {
        try {
            lock.lock();
            log.info("add  current thread name is {}", Thread.currentThread().getName());
            log.info("add thread get current thread time {} ", System.currentTimeMillis());
            while (stack.size() == CAPACITY) {
                log.info("full now");
                stackFullCondition.await();
            }
            stack.push(item);
            log.info("add element");
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private String popFromStack() throws InterruptedException {
        try {
            lock.lock();
            log.info("get current thread name is {}", Thread.currentThread().getName());
            log.info("pop thread get current thread time {}", System.currentTimeMillis());
            while (stack.size() == 0) {
                log.info("empty now, waiting");
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        ReentrantLockWithCondition reentrantLockWithCondition = new ReentrantLockWithCondition();

        PopStack popStack = new PopStack(reentrantLockWithCondition, "pop");
        popStack.start();

        TimeUnit.SECONDS.sleep(20);

        PutStack putStack = new PutStack(reentrantLockWithCondition, "put");
        putStack.start();

        popStack.join();
        putStack.join();
    }


    private static class PopStack extends Thread {
        private ReentrantLockWithCondition reentrantLockWithCondition;

        PopStack(ReentrantLockWithCondition reentrantLockWithCondition, String name) {
            super(name);
            this.reentrantLockWithCondition = reentrantLockWithCondition;
        }

        @Override
        public void run() {
            try {
                reentrantLockWithCondition.popFromStack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class PutStack extends Thread {
        private ReentrantLockWithCondition reentrantLockWithCondition;

        PutStack(ReentrantLockWithCondition reentrantLockWithCondition, String name) {
            super(name);
            this.reentrantLockWithCondition = reentrantLockWithCondition;
        }

        @Override
        public void run() {
            try {
                reentrantLockWithCondition.pushToStack("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
