package leetcode;

import linkedlist.SinglyLinkedList;

public class MergeTwoSortedLinkedList {

  public static SinglyLinkedList.Node merge(SinglyLinkedList.Node list1, SinglyLinkedList.Node list2) {

        SinglyLinkedList.Node first = list1;
        SinglyLinkedList.Node second = list2;

        if (first== null)
            return second;
        if (second== null)
            return first;

        SinglyLinkedList.Node head = null;
        SinglyLinkedList.Node tail = null;

        while (first!= null && second != null) {
            if (head == null) {
                if (first.data < second.data) {
                    head = first;
                    tail= first;
                    first= first.next;
                } else {
                    head = second;
                    tail= second;
                    second= second.next;
                }
            } else {
                if (first.data < second.data) {
                    tail.next =first;
                    first= first.next;
                    tail=tail.next;
                } else {
                    tail.next = second;
                    second= second.next;
                    tail=tail.next;
                }
            }
        }

        if (first== null && second!= null) {
            tail.next = second;
        }
        if (first!= null && second== null) {
            tail.next = first;
        }
        return head;
    }

}
