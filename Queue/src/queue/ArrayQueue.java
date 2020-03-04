package queue;

public class ArrayQueue {
    // INV: n >= 0
    // Q = {e_first, e_2, e_3, ..., e_n-1, e_last} not null
    //
    // First in - Last out
    private int head = 0, tail = 0;
    private Object[] elements = new Object[2];

    // Pre: e not null
    // Post: Q' = {e_1, e_2, ..., e_n, e} && |Q| > 0
    public void enqueue(/*ArrayQueue this, */Object e) {
        assert e != null;
        elements[tail] = e;
        tail = inc(tail);
        if (size() == elements.length) {
            increaseCapacity();
        }
    }

    // Pre: |Q| > 0
    // Post: R = e_1 && Q' = {e_2, ..., e_n} && |Q'| = |Q| - 1
    public Object dequeue(/*ArrayQueue this*/) {
        assert size() > 0;
        Object result = elements[head];
        elements[head] = null;
        head = inc(head);
        return result;
    }

    // Pre: |Q| > 0
    // Post: R = e_1
    public Object element(/*ArrayQueue this*/) {
        assert size() > 0;
        return elements[head];
    }

    // Pre: true
    // Post: R = |Q|
    public int size(/*ArrayQueue this*/) {
        if (head == tail) {
            return elements[head] == null ? 0 : elements.length;
        }
        return head > tail ? elements.length - (head - tail) : tail - head;
    }

    // Pre: true
    // Post: R = (|Q| == 0)
    public boolean isEmpty(/*ArrayQueue this*/) {
        return size() == 0;
    }

    // Pre: true
    // Post: |Q| == 0
    public void clear(/*ArrayQueue this*/) {
        head = 0;
        tail = 0;
        elements = new Object[2];
    }

    // Pre: e not null
    // Post: Q' = {e, e_1, e_2, ..., e_n-1, e_n} && |Q| > 0
    public void push(/*ArrayQueue this, */Object e) {
        assert e != null;
        head = dec(head);
        elements[head] = e;
        if (size() == elements.length) {
            increaseCapacity();
        }
    }

    // Pre: |Q| > 0
    // Post: R = e_n && Q' = {e_1, e_2, ..., e_n-1} && |Q'| = |Q| - 1
    public Object remove(/*ArrayQueueADT this*/) {
        assert size() > 0;
        tail = dec(tail);
        Object result = elements[tail];
        elements[tail] = null;
        return result;
    }

    // Pre: |Q| > 0
    // Post: R = e_n
    public Object peek(/*ArrayQueue this*/) {
        assert size() > 0;
        return elements[dec(tail)];
    }

    private void increaseCapacity(/*ArrayQueue this*/) {
        Object[] increased = new Object[elements.length * 2];
        System.arraycopy(elements, head, increased, 0, elements.length - head);
        System.arraycopy(elements, 0, increased, elements.length - head, tail);
        elements = increased;
        head = 0;
        tail = elements.length / 2;
    }

    private int inc(/*ArrayQueue this, */int a) {
        return (a + 1) % elements.length;
    }

    private int dec(/*ArrayQueue this, */int a) {
        return (elements.length + a - 1) % elements.length;
    }
}
