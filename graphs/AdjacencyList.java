package graphs;

import java.util.ArrayList;

import linkedlist.SinglyLinkedList;
import linkedlist.SinglyLinkedList.Node;
import queues.CircularQueue;

public class AdjacencyList {
    int vertices;
    SinglyLinkedList[] list;

    public AdjacencyList(int vertices) {
        this.vertices = vertices;
        this.list = new SinglyLinkedList[vertices];
        // * Populating list with linkedlist
        for (int i = 0; i < vertices; i++) {
            list[i] = new SinglyLinkedList();
        }
    }

    public void addEdges(int u, int v) {
        list[u].addNode(v);
        list[v].addNode(u);
    }

    public void printLists() {
        for (int i = 0; i < vertices; i++) {
            System.out.print(i);
            SinglyLinkedList.Node current = list[i].head;
            while (current != null) {
                System.out.print("--> " + current.data);
                current = current.next;
            }
            System.out.println();
        }
    }

    public ArrayList<Integer> getAdjacentNodesOf(int v) {
        ArrayList<Integer> adjNodes = new ArrayList<>();
        SinglyLinkedList.Node current = list[v].head;
        while (current != null) {
            adjNodes.add(current.data);
            current = current.next;
        }
        return adjNodes;
    }

    public void BFS(int source) {
        CircularQueue q = new CircularQueue(vertices);
        boolean visited[] = new boolean[vertices];

        q.enqueue(source);
        visited[source] = true;

        System.out.print("Result: ");
        while (!q.isEmpty()) {
            int x = q.dequeue();
            System.out.print(x + ", ");
            Node current = list[x].head;
            while (current != null) {
                if (!visited[current.data]) {
                    q.enqueue(current.data);
                    visited[current.data] = true;
                }
                current = current.next;
            }
        }

    }

    public static void main(String[] args) {

        AdjacencyList a1 = new AdjacencyList(5);
        a1.addEdges(0, 1);
        a1.addEdges(0, 2);
        a1.addEdges(0, 4);
        a1.addEdges(1, 3);
        a1.addEdges(1, 4);
        a1.addEdges(2, 4);
        a1.addEdges(2, 3);
        a1.addEdges(3, 4);

        a1.BFS(0);

        // ArrayList<Integer> result = a1.getAdjacentNodesOf(4);
        // System.out.println(result);

    }

}
