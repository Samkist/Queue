package me.Samkist.Queue;

import java.util.*;
import java.util.function.Consumer;

public class Queue<T> implements Iterable<T> {

    private Stack<T> queueStack = new Stack<>();
    private Stack<T> offloadStack = new Stack<>();

    @Override
    public Iterator<T> iterator() {
        return createArrayList().iterator();
    }

    private ArrayList<T> createArrayList() {
        T[] queueArray = (T[]) queueStack.toArray();
        ArrayList<T> queueList = new ArrayList<>();
        for(int i = queueArray.length; i > 0; i--) {
            queueList.add(queueArray[i-1]);
        }
        return queueList;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        createArrayList().forEach(s -> action.accept(s));
    }

    @Override
    public Spliterator<T> spliterator() {
        return createArrayList().spliterator();
    }

    public void enqueue(T element) {
        while(!queueStack.isEmpty()) {
            offloadStack.push(queueStack.pop());
        }

        queueStack.push(element);

        while(!offloadStack.isEmpty()) {
            queueStack.push(offloadStack.pop());
        }
    }

    public T dequeue() {
        return queueStack.pop();
    }

    public T first() {
        return queueStack.peek();
    }

    public boolean isEmpty() {
        return queueStack.isEmpty();
    }

    public int size() {
        return queueStack.size();
    }

    public String toString() {
        return queueStack.toString();
    }

}
