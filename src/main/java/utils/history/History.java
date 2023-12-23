package utils.history;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;

public class History<T> {
    private final ConcurrentLinkedDeque<T> deque;

    public History() {
        this.deque = new ConcurrentLinkedDeque<>();
    }

    public void add(T entity) {
        this.deque.add(entity);
    }

    public void addAll(List<T> entities) {
        this.deque.addAll(entities);
    }

    public void clear(Consumer<T> function) {
        T entity;
        while ((entity = this.poll()) != null) {
            function.accept(entity);
        }
    }

    private T poll() {
        return this.deque.pollFirst();
    }

    public List<T> getAsList() {
        return new ArrayList<>(deque);
    }

    public void remove(T entity) {
        this.deque.remove(entity);
    }


}
