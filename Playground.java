import caching.LRUcaching;
import etcs.MinMax;
import etcs.MinMax.Pair;

public class Playground {
    public static void main(String[] args) {

        LRUcaching l = new LRUcaching(3);

        l.put(1, 10);
        l.put(2, 20);
        l.put(3, 30);

        l.printList();
        System.out.println("-----------------");

        l.put(5,50);

        l.printList();

    }
}