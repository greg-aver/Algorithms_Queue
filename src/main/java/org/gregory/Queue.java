package org.gregory;

import java.util.EmptyStackException;
import java.util.Stack;

public class Queue<T>
{
    private Stack<T> stackMain;
    private Stack<T> stackBuffer;

    public Queue()
    {
        stackBuffer = new Stack<T>();
        stackMain = new Stack<T>();
    }

    public void enqueue(T item)
    {
        stackMain.push(item);
    }

    public T dequeue()
    {
        while (!stackMain.empty()) {
            stackBuffer.push(stackMain.pop());
        }

        T head = null;

        try {
            head = (T) stackBuffer.pop();
        } catch (EmptyStackException e) {
            head = null;
        }

        while ((!stackBuffer.empty())) {
            stackMain.push(stackBuffer.pop());
        }

        return head;
    }

    public int size()
    {
        int size = 0;
        while (!stackMain.empty()) {
            stackBuffer.push(stackMain.pop());
            size++;
        }
        while ((!stackBuffer.empty())) {
            stackMain.push(stackBuffer.pop());
        }

        return size;
    }

    public void spin(int moving) {
        for (int i = 1; i <= moving; i++) {
            this.enqueue(this.dequeue());
        }
    }
}
