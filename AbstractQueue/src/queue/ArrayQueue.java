package queue;

//
// Let be a = sequence of queue elements
// n = a.length
//
//INV: n >= 0 && for i = 0..n - 1: a[i] != null
// First in - Last out

public class ArrayQueue extends AbstractQueue {

    private Object[] elements = new Object[4];
    private int head;
    private int curElement;
    protected void enqueueImpl(Object element) {

        ensureCapacity(size());
        elements[getTail()] = element;
    }

    protected void dequeueImpl() {

        elements[head] = null;
        head = (head + 1) % elements.length;
    }

    protected Object elementImpl() {
        return elements[head];
    }



    protected void clearImpl() {
        elements = new Object[4];
        head = 0;
    }


    private void ensureCapacity(int cap) {
        if (cap == elements.length) {
            Object[] newArray = new Object[elements.length * 2 + 1];
            System.arraycopy(elements, head, newArray, 0, elements.length - head);
            System.arraycopy(elements, 0, newArray, elements.length - head, getTail());
            elements = newArray;
            head = 0;
        }
    }

    protected void setIndex() {
        curElement = 0;
    }

    protected Object getElement() {

        Object element = elements[(head + curElement) % elements.length];
        curElement++;
        return element;
    }

    protected Queue createEmptyQueue() {
        return new ArrayQueue();
    }

    protected int getTail() {
        return (head + size()) % elements.length;
    }
}