package com.luckylee.test;

/**
 * @program: JLearn
 * @author: lining
 * @create: 2018-05-05 18:05
 **/
public class Stack<E> {
    private Note<E> head;
    private Note<E> curr;

    class Note<T extends E> {
        T data;
        Note<T> pre;

        public Note(T data) {
            this.data = data;
        }
    }

    public void push(E data) {
        if (head == null) {
            head = new Note(data);
            curr = head;
        } else {
            Note note = new Note(data);
            note.pre = curr;
            curr = note;
        }
    }

    public E pop() {
        if (curr == null) {
            return null;
        } else {
            Note note = curr;
            curr = curr.pre;
            return (E) note.data;
        }
    }

    public int size() {
        int i = 0;
        if (curr != null) {
            i++;
            Note s = curr.pre;
            while (s != null) {
                i++;
                s = s.pre;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        for (int i = 1; i <= 10000; i++) {
            stack.push(i);
        }
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.size());
    }
}
