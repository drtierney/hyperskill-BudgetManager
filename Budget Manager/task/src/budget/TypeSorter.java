package budget;

import java.util.Comparator;

public class TypeSorter implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o2.getCategory().compareTo(o1.getCategory());
    }
}
