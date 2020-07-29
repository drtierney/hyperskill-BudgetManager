package budget;

import java.util.Comparator;

public class PriceSorter implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return Double.compare(o2.getPrice(), o1.getPrice());
    }
}
