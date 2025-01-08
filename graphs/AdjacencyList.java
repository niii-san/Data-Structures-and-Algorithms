package graphs;

import java.util.ArrayList;

import linkedlist.SinglyLinkedList;
import linkedlist.SinglyLinkedList.Node;
import queues.CircularQueue;
import stacks.IntStack;

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

        System.out.print("\nResult: ");
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

    public void depthfirstsearch(int source) {
        boolean visited[] = new boolean[vertices];
        System.out.print("\nResult: ");
        dfs(source, visited);
    }

    private void dfs(int node, boolean visited[]) {
        System.out.print(node + ", ");
        Node current = list[node].head;
        visited[node] = true;
        while (current != null) {
            if (!visited[current.data]) {
                dfs(current.data, visited);
            }
            current = current.next;
        }

    }

    public void nrDFS(int source) {
        IntStack stk = new IntStack(vertices);
        boolean visited[] = new boolean[vertices];
        stk.push(source);
        visited[source] = true;

        System.out.print("\nNr DFS: ");
        while (!stk.isEmpty()) {
            // * get top of the stack and explore its neighbour nodes
            int x = stk.pop();
            System.out.print(x + ", ");
            Node current = list[x].head;

            while (current != null) {
                if (!visited[current.data]) {
                    stk.push(current.data);
                    visited[current.data] = true;
                }
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {

        AdjacencyList a1 = new AdjacencyList(8);
        a1.addEdges(0, 1);
        a1.addEdges(0, 2);
        a1.addEdges(0, 3);
        a1.addEdges(1, 2);
        a1.addEdges(1, 5);
        a1.addEdges(1, 6);
        a1.addEdges(3, 4);
        a1.addEdges(3, 7);

        // a1.BFS(0);
        // a1.printLists();
        // a1.depthfirstsearch(4);
        a1.nrDFS(0);

        // ArrayList<Integer> result = a1.getAdjacentNodesOf(4);
        // System.out.println(result);

    }

}
