import linkedlist.SinglyLinkedList;
import linkedlist.SinglyLinkedList.Node;

public class Playground {
    public static void main(String[] args) {

        SinglyLinkedList l1 = new SinglyLinkedList();

        Node n1 = l1.addNode(1);
        Node n2 = l1.addNode(22);
        Node n3 = l1.addNode(333);
        Node n4 = l1.addNode(4444);
        Node n5 = l1.addNode(55555);

        System.out.println("\nbefore");
        l1.printList();


        l1.addAfter(n5,9999999);
        

        System.out.println("\nafter");
        l1.printList();

    }
}