package log;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MyStruct {
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

    public Iterable<LogEntry> getIter() {
        Iterable<LogEntry> iterable = queue;
        return iterable;
    }
}
