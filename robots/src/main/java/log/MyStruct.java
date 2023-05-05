package log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyStruct implements Iterable<LogEntry> {
    private int structureSize;
    private Queue<LogEntry> queue;

    public MyStruct(int structureSize) {
        this.structureSize = structureSize;
        queue = new ConcurrentLinkedQueue<>();
    }

    public void add(LogEntry element) {
        if (queue.size() == structureSize) {
            queue.poll();
        }
        queue.add(element);
    }

    public List<LogEntry> getSlice(int startIndex, int endIndex) {
        List<LogEntry> list = new ArrayList<>(queue);
        return list.subList(startIndex, endIndex);
    }

    public int getStructureSize() {
        return structureSize;
    }

    public void delete(int index) {

    }

    public int size() {
        return this.queue.size();
    }

    @Override
    public Iterator<LogEntry> iterator() {
        return new MyStructIterator(queue.iterator());
    }

    private class MyStructIterator implements Iterator<LogEntry> {
        private Iterator<LogEntry> queueIterator;

        public MyStructIterator(Iterator<LogEntry> queueIterator) {
            this.queueIterator = queueIterator;
        }

        @Override
        public boolean hasNext() {
            synchronized (queue) {
                return queueIterator.hasNext();
            }
        }

        @Override
        public LogEntry next() {
            synchronized (queue) {
                return queueIterator.next();
            }
        }
    }
}