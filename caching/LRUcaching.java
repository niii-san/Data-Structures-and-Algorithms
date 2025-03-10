package caching;

import java.util.HashMap;

public class LRUcaching {

    static class Node {
        Node prev;
        Node next;
        int k;
        int v;

        Node(int key, int value) {
            this.k = key;
            this.v = value;
            this.prev = this.next = null;
        }
    }

    int capacity;
    public HashMap<Integer, Node> map;

    Node dummyHead = new Node(0, 0);
    Node dummyTail = new Node(0, 0);

    public LRUcaching(int capacity) {
        this.capacity = capacity;
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        map = new HashMap<>();
    }

    public void put(int key, int value) {

        Node newNode = new Node(key, value);

        if (map.containsKey(key)) {
            delete(key);
            insert(newNode);
        } else {
            if (map.size() == capacity) {
                Node lru = dummyTail.prev;
                lru.prev.next = lru.next;
                lru.next.prev = lru.prev;
                lru.next = lru.prev = null;
                insert(newNode);
            }else{
                insert(newNode);
            }
        }

    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            delete(node.k);
            insert(node);
            return node.v;
        } else {
            return -1;
        }

    }

    public void insert(Node node) {
        map.put(node.k, node);

        node.prev = dummyHead;
        node.next = dummyHead.next;
        dummyHead.next = node;
        node.next.prev = node;

    }

    public void delete(int key) {
        Node node = map.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = node.next = null;
        map.remove(key);
    }

    public void printList() {
        Node current = dummyHead.next;
        while (current != dummyTail) {
            System.out.println("key: " + current.k + " value: " + current.v);
            current=current.next;
        }

    }

}
