package graphs;

import linkedlist.SinglyLinkedList;
import queues.CircularQueue;
import queues.LinearQueue;
import stacks.IntStack;

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

    public void BFS(int source) {
        CircularQueue q = new CircularQueue(vertices);
        boolean visited[] = new boolean[vertices];

        q.enqueue(source);
        visited[source] = true;

        System.out.print("Result: ");
        while (!q.isEmpty()) {
            int x = q.dequeue();
            System.out.print(x + ", ");
            for (int j = 0; j < vertices; j++) {
                if (matrix[x][j] != 0) {
                    if (!visited[j]) {
                        q.enqueue(j);
                        visited[j] = true;
                    }
                }
            }

        }
    }

    public void depthfirstsearch(int source) {
        boolean visited[] = new boolean[vertices];
        System.out.print("Result: ");
        dfs(source, visited);

    }

    private void dfs(int node, boolean visited[]) {
        System.out.print(node + ", ");
        visited[node] = true;
        for (int j = 0; j < vertices; j++) {
            if (matrix[node][j] != 0) {
                if (!visited[j]) {
                    dfs(j, visited);
                }
            }
        }
    }

    public void nrDFS(int source) {
        IntStack stk = new IntStack(vertices);
        boolean visited[] = new boolean[vertices];

        stk.push(source);
        visited[source] = true;

        System.out.print("\nNon recursize DFS result: ");
        while (!stk.isEmpty()) {
            int current = stk.pop();
            System.out.print(current + ", ");
            for (int j = 0; j < vertices; j++) {
                if (matrix[current][j] != 0 && !visited[j]) {
                    stk.push(j);
                    visited[j] = true;
                }
            }
        }

    }

    public static void main(String[] aggs) {

        AdjacencyMatrix a1 = new AdjacencyMatrix(8);
        a1.addEdges(0, 1);
        a1.addEdges(0, 2);
        a1.addEdges(0, 3);
        a1.addEdges(1, 2);
        a1.addEdges(1, 5);
        a1.addEdges(1, 6);
        a1.addEdges(3, 4);
        a1.addEdges(3, 7);

        // a1.BFS(4);
        a1.nrDFS(0);

        // SinglyLinkedList.Node r1 = a1.getAdjacentNodesOf(1);
        // while (r1 != null) {
        // System.out.print(r1.data + ", ");
        // r1 = r1.next;
        // }

    }
}
