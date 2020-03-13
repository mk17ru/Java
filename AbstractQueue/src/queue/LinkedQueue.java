package queue;

public class LinkedQueue extends AbstractQueue {

    private Node head;
    private Node tail;
    private Node curElement;

    protected void enqueueImpl(Object element) {
        Node cur = new Node(element);
        if (size() == 0) {
            head = cur;
        } else {
            tail.next = cur;
        }
        tail = cur;
    }

    protected void dequeueImpl() {
        head = head.next;
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void clearImpl() {
        head = null;
        tail = null;
    }

    protected LinkedQueue createEmptyQueue() {
        return new LinkedQueue();
    }

    protected void setIndex() {
        curElement = head;
    }

    protected Object getElement() {
        Object result = curElement.value;
        curElement = curElement.next;
        return result;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value) {
            assert value != null;

            this.value = value;
            this.next = null;
        }
    }


}
