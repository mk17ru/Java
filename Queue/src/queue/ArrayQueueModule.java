package queue;

public class ArrayQueueModule {
    // INV: n >= 0
    // Q = {e_first, e_2, e_3, ..., e_n-1, e_last} not null
    // First in - Last out
    private static int head = 0, tail = 0;
    private static Object[] elements = new Object[2];

    // Pre: e not null
    // Post: Q' = {e_1, e_2, ..., e_n, e} && |Q| > 0
    public static void enqueue(Object e) {
        assert e != null;
        elements[tail] = e;
        tail = inc(tail);
        if (size() == elements.length) {
            increaseCapacity();
        }
    }

    // Pre: |Q| > 0
    // Post: R = e_1 && Q' = {e_2, ..., e_n} && |Q'| = |Q| - 1
    public static Object dequeue() {
        assert size() > 0;
        Object result = elements[head];
        elements[head] = null;
        head = inc(head);
        return result;
    }

    // Pre: |Q| > 0
    // Post: R = e_1
    public static Object element() {
        assert size() > 0;
        return elements[head];
    }

    // Pre: true
    // Post: R = |Q|
    public static int size() {
        if (head == tail) {
            return elements[head] == null ? 0 : elements.length;
        }
        return head > tail ? elements.length - (head - tail) : tail - head;
    }

    // Pre: true
    // Post: R = (|Q| == 0)
    public static boolean isEmpty() {
        return size() == 0;
    }

    // Pre: true
    // Post: |Q| == 0
    public static void clear() {
        head = 0;
        tail = 0;
        elements = new Object[2];
    }

    // Pre: e not null
    // Post: Q' = {e, e_1, e_2, ..., e_n-1, e_n} && |Q| > 0
    public static void push(Object e) {
        assert e != null;
        head = dec(head);
        elements[head] = e;
        if (size() == elements.length) {
            increaseCapacity();
        }
    }

    // Pre: |Q| > 0
    // Post: R = e_n && Q' = {e_1, e_2, ..., e_n-1} && |Q'| = |Q| - 1
    public static Object remove() {
        assert size() > 0;
        tail = dec(tail);
        Object result = elements[tail];
        elements[tail] = null;
        return result;
    }

    // Pre: |Q| > 0
    // Post: R = e_n
    public static Object peek() {
        assert size() > 0;
        return elements[dec(tail)];
    }

    private static void increaseCapacity() {
        Object[] increased = new Object[elements.length * 2];
        System.arraycopy(elements, head, increased, 0, elements.length - head);
        System.arraycopy(elements, 0, increased, elements.length - head, tail);
        elements = increased;
        head = 0;
        tail = elements.length / 2;
    }

    private static int inc(int a) {
        return (a + 1) % elements.length;
    }

    private static int dec(int a) {
        return (elements.length + a - 1) % elements.length;
    }
}
