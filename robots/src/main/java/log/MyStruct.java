package log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyStruct {
    private int structureSize;
    private List<LogEntry> list;

    public MyStruct(int structureSize) {
        this.structureSize = structureSize;
        list = new ArrayList<>(structureSize);
        list = Collections.synchronizedList(list);
    }

    public void add(LogEntry element) {
        if (list.size() == structureSize) {
            list.remove(0);
        }
        list.add(element);
    }


    public List<LogEntry> getSlice(int startIndex, int endIndex) {
        return list.subList(startIndex, endIndex);
    }

    public int getStructureSize() {
        return structureSize;
    }

    public void delete(int index) {

    }

    public int size() {
        return this.list.size();
    }

    public Iterable<LogEntry> getIter() {
        Iterable<LogEntry> iterable = list;
        return iterable;
    }
}
