package graphs;

import linkedlist.SinglyLinkedList;

public class AdjacencyMatrix {

    int vertices;
    int matrix[][];

    public AdjacencyMatrix(int vertices) {
        this.vertices = vertices;
        this.matrix = new int[vertices][vertices];
    }

    public void addEdges(int u, int v) {
        matrix[u][v] = 1;
        matrix[v][u] = 1;
    }

    public SinglyLinkedList.Node getAdjacentNodesOf(int v) {
        SinglyLinkedList nodes = new SinglyLinkedList();
        for (int j = 0; j < vertices; j++) {
            if (matrix[v][j] != 0) {
                nodes.addNode(j);
            }
        }
        return nodes.head;
    }

    public void printAdjacentNodesOf(int v) {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Adjacent nodes of: " + i + " = ");
            for (int j = 0; j < vertices; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(j + ", ");
                }
            }
            System.out.println();
        }
    }

    public void printMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] aggs) {

        AdjacencyMatrix a1 = new AdjacencyMatrix(5);
        a1.addEdges(0, 1);
        a1.addEdges(0, 2);
        a1.addEdges(2, 1);
        a1.addEdges(1, 3);
        a1.addEdges(1, 4);
        a1.addEdges(4, 3);

        // SinglyLinkedList.Node r1 = a1.getAdjacentNodesOf(1);
        // while (r1 != null) {
        //     System.out.print(r1.data + ", ");
        //     r1 = r1.next;
        // }

    }
}
