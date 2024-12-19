package linkedlist;

public class DoublyLinkedList {

    public static class Node {
        Node prev;
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    Node head = null;
    Node tail = null;

    // * passed
    public Node addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        return newNode;
    }

    // * passed
    public void removeNode() {
        if (tail == head) {
            tail = head = null;

        } else {
            tail = tail.prev;
            tail.next.prev = null;
            tail.next.next = null;
            tail.next = null;
        }

    }

    // * passed
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println("data: " + current.data);
            // System.out.println("prev: " + current.prev + " data: " + current.data + "
            // next: " + current.next);
            current = current.next;
        }
    }

    // * passed
    public Node insertBefore(Node node, int data) {
        Node newNode = new Node(data);

        if (node != head) {
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
        } else {
            newNode.next = node;
            node.prev = newNode;
            head = newNode;
        }

        return newNode;
    }

    // * passed
    public void removeBefore(Node node) {
        if (node.prev != null) {
            if (node.prev.prev == null) {
                Node temp = head;
                head = head.next;
                head.prev = null;
                temp.next = null;
            } else {
                Node toRemove = node.prev;
                toRemove.prev.next = node;
                node.prev = toRemove.prev;
                toRemove.prev = toRemove.next = null;
            }
        } else {
            System.out.println("Nothing to remove");
        }
    }

    // * passed
    public void insertAfter(Node node, int data) {
        Node newNode = new Node(data);
        if (node == tail) {
            node.next = newNode;
            newNode.prev = node;
            tail = newNode;
        } else {
            newNode.next = node.next;
            newNode.next.prev = newNode;
            node.next = newNode;
            newNode.prev = node;
        }
    }

    // * passed
    public void removeAfter(Node node) {
        if (node.next == null) {
            System.out.println("no node after provided to remove");
        } else {
            if (node.next == tail) {
                node.next.prev = null;
                node.next = null;
                tail = node;
            } else {
                Node toRemove = node.next;
                node.next = node.next.next;
                node.next.prev = node;
                toRemove.next = toRemove.prev = null;
            }

        }
    }

    // * passed
    public void removeAt(int position) {

        Node left = head;
        Node temp;
        for (int i = 1; i < position - 1; i++) {
            left = left.next;
        }

        if (left.next == tail) {
            tail.prev = null;
            tail = left;
            tail.next = null;

        } else {
            temp = left.next;
            left.next = left.next.next;
            left.next.prev = left;
            temp.next = temp.prev = null;
        }

    }

}
