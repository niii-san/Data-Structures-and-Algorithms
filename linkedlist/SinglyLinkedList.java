package linkedlist;

public class SinglyLinkedList {

    Node head = null;
    Node tail = null;

    public static class Node {
        public int data;
        public Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // * PASSED
    public Node addNode(int data) {

        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        return newNode;
    }

    // * PASSED
    public void addHeadFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;

        }
    }

    // * PASSED
    public Node addBefore(Node node, int data) {

        Node newNode = new Node(data);

        if (node == head) {

            newNode.next = head;
            head = newNode;
            return newNode;

        } else {

            Node current = head;
            while (current.next != node) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;

            return newNode;
        }
    }

    // * PASSED
    public Node addAfter(Node node, int data) {

        Node newNode = new Node(data);
        newNode.next = node.next;
        node.next = newNode;

        return newNode;

    }

    // * PASSED
    public void removeAfter(Node node) {

        Node temp = node.next;
        node.next = node.next.next;
        temp.next = null;

    }

    // * PASSED
    public void removeBefore(Node node) {

        Node current = head;

        if (node == head) {
            System.out.println("theres no any node before head");
        } else {
            while (current.next.next != node) {
                if (current.next.next == null) {
                    System.out.println("Node not found or too few nodes only");
                    break;
                }
                current = current.next;
            }

            Node temp = current.next;
            current.next = current.next.next;
            temp.next = null;

        }

    }

    // * PASSED
    public void removeNodeByPosition(int position) {
        // for head
        if (position == 1) {
            Node temp = head;
            head = head.next;
            temp.next = null;
        } else {
            Node current = head;
            for (int i = 2; i < position; i++) {
                if (current == null) {
                    System.out.println("position out of bound");
                    break;
                }
                current = current.next;
            }
            Node temp = current.next;
            current.next = temp.next;
            temp.next = null;
        }
    }

    // * PASSED
    public void printList() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;

        }
    }

}