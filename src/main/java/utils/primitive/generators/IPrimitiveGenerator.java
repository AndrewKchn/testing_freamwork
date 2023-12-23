package utils.primitive.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface IPrimitiveGenerator<T extends Comparable<T>, S> {

    T generate();

    T generate(S min, S max);

    default List<T> generateList(int size) {
        List<T> tlist = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tlist.add(generate());
        }
        return tlist;
    }

    default List<T> generateList(int size, S min, S max) {
        List<T> tlist = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tlist.add(generate(min, max));
        }
        return tlist;
    }

    default List<T> generateUniqueList(int size, S min, S max) {
        List<T> tlist = new ArrayList<>();
        while (tlist.size()<size){
            T randomValue = generate(min,max);
            if (!tlist.contains(randomValue)){
                tlist.add(randomValue);
            }
        }
        Collections.sort(tlist);
        return tlist;
    }
}
