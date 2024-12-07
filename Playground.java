import linkedlist.DoublyLinkedList;
import linkedlist.DoublyLinkedList.Node;

public class Playground {
    public static void main(String[] args) {

        DoublyLinkedList d1 = new DoublyLinkedList();

        Node n1 = d1.addNode(1);
        Node n2 = d1.addNode(2);
        Node n3 = d1.addNode(3);

        
        
        d1.removeAt(3);

        d1.printList();
    }
}