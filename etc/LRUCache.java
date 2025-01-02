package LRUcaching;

import java.util.HashMap;

public class LRUCache {

    public static class Node {
        int k;
        int v;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.k = key;
            this.v = value;
            this.next = this.prev = null;
        }
    }

    int capacity;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    void put(int key, int value) {
        if (map.containsKey(key)) {
            // * Remove associated node and key from both linked list and hashmap
            removeNode(map.get(key));

        }
        if (map.size() == capacity) {
            // * caching is full
            // * remove last node > lru is kept at last
            removeNode(dummytail.prev);
        }
        Node newNode = new Node(key, value);
        insert(newNode);

    }

    int get(int key) {
        Node node = map.get(key);
        if (node != null) {

            removeNode(node);
            insert(node);

            return node.v;
        }
        return -1;
    }

    Node dummyhead = new Node(0, 0);
    Node dummytail = new Node(0, 0);

    void insert(Node node) {
        map.put(node.k, node);
        if (dummyhead.next == null) {
            // first time inserting
            node.prev = dummyhead;
            node.next = dummytail;
            dummyhead.next = node;
            dummytail.prev = node;

        } else {
            node.next = dummyhead.next;
            dummyhead.next.prev = node;
            node.prev = dummyhead;
            dummyhead.next = node;

        }

    }

    void removeNode(Node node) {
        map.remove(node.k);

        Node left = node.prev;
        Node right = node.next;

        left.next = right;
        right.prev = left;

        node.next = null;
        node.prev = null;

    }

}

/*
 * Implement LFU caching
 */
