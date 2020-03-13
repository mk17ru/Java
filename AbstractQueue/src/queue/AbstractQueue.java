package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {

    private int size = 0;

    @Override
    public Queue filter(Predicate<Object> predicate) {
        assert predicate != null;

        Function<Object, Object> function = o -> {
            if (!predicate.test(o)) {
                return null;
            } else {
                return o;
            }
        };
        return makeQueue(function);
    }

    protected abstract void enqueueImpl(Object element);

    public void enqueue(Object element) {
        assert element != null;

        enqueueImpl(element);
        size++;
    }

    protected abstract void dequeueImpl();

    public Object dequeue() {
        assert size > 0;

        Object element = elementImpl();
        dequeueImpl();
        size--;
        return element;
    }

    protected abstract Object elementImpl();

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();

    protected abstract void setIndex();

    protected abstract Object getElement();

    protected abstract Queue createEmptyQueue();


    public Queue map(Function<Object, Object> function) {
        assert function != null;

        return makeQueue(function);
    }

    private Queue makeQueue(Function<Object, Object> curFunction) {
        setIndex();
        Queue queue = createEmptyQueue();
        int i = 0;
        while (i < size) {
            Object element = curFunction.apply(getElement());
            if (element != null) {
                queue.enqueue(element);
            }
            i++;
        }
        return queue;
    }

}
