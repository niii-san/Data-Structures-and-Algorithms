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
    public void addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println("prev: " + current.prev + " data: " + current.data + " next: " + current.next);
            current = current.next;
        }
    }

    // * task other function

    public void insertBefore(Node node, int data) {
        Node newNode = new Node(data);

        newNode.next=node;
        newNode.prev=node.prev;
        node.prev.next=newNode;
        node.prev=newNode;


    }

}
