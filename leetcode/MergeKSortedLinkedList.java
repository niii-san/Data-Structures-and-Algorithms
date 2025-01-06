package leetcode;

import linkedlist.SinglyLinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// class Solution {
// public ListNode mergeKLists(ListNode[] lists) {

// }
// }

public class MergeKSortedLinkedList {

    public static SinglyLinkedList.Node mergeK(SinglyLinkedList.Node[] lists) {

        int k = lists.length;

        SinglyLinkedList.Node temp = null;

        int i = 0;

        while(i<k){
            SinglyLinkedList.Node result =null;
            if(i+1==k){
                temp = mergeList(temp,lists[i]);
                i++;
            }else{
                result = mergeList(lists[i], lists[i+1]);
                temp = mergeList(temp, result);
                i=i+2;
              
            }
        }

        

        return temp;
    }

    public static SinglyLinkedList.Node mergeList(SinglyLinkedList.Node list1, SinglyLinkedList.Node list2) {

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

    public static void main(String[] args) {
        SinglyLinkedList l1 = new SinglyLinkedList();
        l1.addNode(5);
        l1.addNode(6);
        l1.addNode(8);
        l1.addNode(10);

        SinglyLinkedList l2 = new SinglyLinkedList();
        l2.addNode(2);
        l2.addNode(8);
        l2.addNode(10);
        l2.addNode(22);

        SinglyLinkedList l3 = new SinglyLinkedList();
        l3.addNode(1);
        l3.addNode(9);
        l3.addNode(14);
        l3.addNode(20);

        SinglyLinkedList l4 = new SinglyLinkedList();
        l4.addNode(3);
        l4.addNode(6);
        l4.addNode(10);
        l4.addNode(30);

        SinglyLinkedList l5 = new SinglyLinkedList();
        l5.addNode(1);
        l5.addNode(2);
        l5.addNode(3);

        SinglyLinkedList.Node[] lists = { l1.head, l2.head, l3.head, l4.head,l5.head };
        SinglyLinkedList.Node result = mergeK(lists);

        while (result != null) {
            System.out.print(result.data + ", ");
            result = result.next;
        }


        // SinglyLinkedList.Node tst= mergeList(l1.head, l2.head);
        // while (tst!=null) {
        //     System.out.print(tst.data+", ");
        //     tst=tst.next;
        // }

    }
}
