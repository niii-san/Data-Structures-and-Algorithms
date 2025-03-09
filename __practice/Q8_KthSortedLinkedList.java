package __practice;

import linkedlist.SinglyLinkedList;
import linkedlist.SinglyLinkedList.Node;

public class Q8_KthSortedLinkedList {

    static Node mergeKthSortedLinkedList(Node[] heads, int start, int end) {
        if (start == end)
            return heads[start];

        int mid = (start + end) / 2;
        // left side
        Node left = mergeKthSortedLinkedList(heads, start, mid);
        Node right = mergeKthSortedLinkedList(heads, mid + 1, end);

        return merge(left, right);
    }

    static Node merge(Node left, Node right) {
        if (left == null)
            return right;
        if (right == null)
            return left;

        Node dummyHead = new Node(0);
        Node current = dummyHead;

        while (left != null && right != null) {
            if (left.data < right.data) {
                current.next = left;
                left=left.next;
            } else {
                current.next = right;
                right=right.next;
            }
            current = current.next;
        }

        if (left != null) {
            current.next = left;
        }
        if (right != null) {
            current.next = right;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        SinglyLinkedList l1 = new SinglyLinkedList();
        l1.addNode(1);
        l1.addNode(3);
        l1.addNode(6);
        l1.addNode(10);

        SinglyLinkedList l2 = new SinglyLinkedList();
        l2.addNode(2);
        l2.addNode(11);

        SinglyLinkedList l3 = new SinglyLinkedList();
        l3.addNode(1);
        l3.addNode(4);
        l3.addNode(7);
        l3.addNode(9);
        l3.addNode(11);

        SinglyLinkedList l4 = new SinglyLinkedList();
        l4.addNode(0);
        l4.addNode(2);
        l4.addNode(4);

        Node[] linkedLists = { l1.head, l2.head, l3.head, l4.head };
        Node result = mergeKthSortedLinkedList(linkedLists, 0, linkedLists.length - 1);
        while (result != null) {
            System.out.print(result.data + ", ");
            result = result.next;
        }

    }
}
