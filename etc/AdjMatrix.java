package etc;

import java.util.Arrays;

import linkedlist.SinglyLinkedList;
import queues.CircularQueue;
import stacks.IntStack;

public class AdjMatrix {

    // * mst

    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    // * mst end

    Edge[] edges;
    int edgeindx = -1; // * mst

    int[][] matrix;
    int vertices;

    public AdjMatrix(int vertices) {
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
        edges = new Edge[vertices * (vertices - 1) / 2];
    }

    void addEdges(int u, int v, int w) {
        matrix[u][v] = w;
        matrix[v][u] = w;
        edges[++edgeindx] = new Edge(u, v, w); // * mst
    }

    void krushkalsAlgorithm() {
        int parent[] = new int[vertices];
        int size[] = new int[vertices];
        int mst[][] = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = -1;
        }
        Arrays.sort(edges);
        int edgeTaken = 0;
        int edgeCounter = -1;
        while (edgeTaken < vertices) {
            Edge e = edges[++edgeCounter];
            int uabsroot = find(e.u, parent);
            int vbasroot = find(e.v, parent);
            if (uabsroot == vbasroot) {
                // cycle continue
                continue;
            }
            mst[e.u][e.v] = e.w;
            mst[e.v][e.u] = e.w;
            union(uabsroot, vbasroot, parent, size);
            edgeTaken++;
        }

    }

    void union(int uabsroot, int vbasroot, int parent[], int size[]) {
        if (size[uabsroot] > size[vbasroot]) {
            parent[vbasroot] = uabsroot;
        } else if (size[uabsroot] < size[vbasroot]) {
            parent[uabsroot] = vbasroot;

        }
    }

    int find(int u, int parent[]) {
        if (parent[u] == -1) {
            return u;
        }
        return find(parent[u], parent);
    }

    void printGraph() {
        // * row */
        for (int i = 0; i < vertices; i++) {
            // *column
            System.out.print(i + " is connected to ");
            for (int j = 0; j < vertices; j++) {
                if (matrix[i][j] != 0) {
                    // * i, j are connected
                    System.out.print(j + ", ");
                }

            }
            System.out.println();
        }
    }

    SinglyLinkedList getAjdNodes(int i) {
        SinglyLinkedList adjNodes = new SinglyLinkedList();
        for (int j = 0; j < vertices; j++) {
            if (matrix[i][j] != 0) {
                adjNodes.addNode(j);
            }
        }
        return adjNodes;
    }

    void printMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {

            }
        }
    }

    void BFS(int source) {
        CircularQueue q = new CircularQueue(vertices);
        boolean visited[] = new boolean[vertices];

        q.enqueue(source);
        visited[source] = true;

        while (!q.isEmpty()) {
            int x = q.dequeue();
            System.out.println(x);
            // * call getAdjNodes and iterate through list
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

    int dijakstra(int source, int destination) {
        int dist[] = new int[vertices];
        int prevpath[] = new int[vertices];
        boolean visited[] = new boolean[vertices];

        // * populating distance and prev path
        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prevpath[i] = -1;
        }

        dist[source] = 0;

        for (int i = 0; i < vertices; i++) {
            // * find minimum vertex
            int min_vertex = findMinVertex(dist, visited);
            visited[min_vertex] = true;

            for (int j = 0; j < vertices; j++) {
                if (matrix[min_vertex][j] != 0) {
                    if (!visited[j] && dist[min_vertex] + matrix[min_vertex][j] < dist[j]) {
                        dist[j] = dist[min_vertex] + matrix[min_vertex][j];
                        prevpath[j] = min_vertex;
                    }
                }
            }

        }
        // print path
        int x = destination;
        IntStack stk = new IntStack(vertices);
        stk.push(x);
        while (prevpath[x] != -1) {
            x = prevpath[x];
            stk.push(x);
        }
        while (!stk.isEmpty()) {
            System.out.print(stk.pop());
        }
        return dist[destination];
    }

    int findMinVertex(int[] dist, boolean[] visited) {
        int min = -1;
        for (int i = 0; i < vertices; i++) {
            if (min == -1 && !visited[i] || dist[i] < dist[min] && !visited[i]) {
                min = 1;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        AdjMatrix adj = new AdjMatrix(6);
        adj.addEdges(0, 1, 10);
        adj.addEdges(0, 5, 100);
        adj.addEdges(0, 2, 5);
        adj.addEdges(1, 2, 2);
        adj.addEdges(1, 3, 5);
        adj.addEdges(2, 3, 10);
        adj.addEdges(2, 4, 20);
        adj.addEdges(3, 5, 2);
        adj.addEdges(4, 5, 5);

        int result = adj.dijakstra(0, 5);
        System.out.println(result);

    }

}