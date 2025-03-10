package __practice;

import java.util.HashMap;

public class Q11_LRUcaching {

    int capacity;
    public HashMap<Integer, Node> map;
    Node dummyHead;
    Node dummyTail;

    Q11_LRUcaching(int capacity) {
        this.capacity = capacity;
        Node dh = this.dummyHead = new Node(-1, -1);
        Node dt = this.dummyTail = new Node(-1, -1);
        dh.next = dt;
        dt.prev = dh;

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
                delete(lru.key);
                insert(newNode);
            } else {
                insert(newNode);
            }
        }
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;

        } else {
            delete(node.key);
            insert(node);
            return node.value;
        }
    }

    public void insert(Node node) {
        map.put(node.key, node);

        node.next = dummyHead.next;
        node.prev = dummyHead;
        dummyHead.next = node;
        node.next.prev = node;
    }

    public void delete(int key) {
        Node node = map.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;

        map.remove(key);
    }

    public void printList() {
        Node current = dummyHead.next;

        while (current.next != null) {
            System.out.println("{key: " + current.key + ", " + "value: " + current.value + "}, ");
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Q11_LRUcaching l1 = new Q11_LRUcaching(3);
        l1.put(1, 5);
        l1.put(2, 6);
        l1.put(3, 7);
        l1.printList();

    }
}

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = this.next = null;
    }
}