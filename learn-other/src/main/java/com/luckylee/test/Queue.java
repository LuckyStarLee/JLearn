package com.luckylee.test;


/**
 * @program: JLearn
 * @author: lining
 * @create: 2018-05-06 21:52
 **/
public class Queue<E> {
    private Note<E> head;
    private Note<E> curr;

    class Note<T extends E> {
        T date;
        Note<T> next;

        public Note(T date) {
            this.date = date;
        }
    }

    public void add(E data) {
        if (head == null) {
            head = new Note(data);
            curr = head;
        } else {
            Note note = new Note(data);
            curr.next = note;
            curr = note;
        }
    }

    public E pop() throws  Exception {
        if (head == null) {
            throw new Exception("队列为空");
        } else {
            Note note = head;
            head = note.next;
            return (E) note.date;
        }
    }

    public static void main(String[] args) throws Exception{
        Queue<Integer> q = new Queue<>();

       /* for (int i = 0; i < 6; i++) {
            q.add(i);
        }*/

        for (int i = 0; i <6 ; i++) {
            System.out.println(q.pop());
        }
    }
}
