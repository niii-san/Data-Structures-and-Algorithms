package _coursework.Question3;

import java.util.*;

/*
 * Question 3 (A)
You have a network of n devices. Each device can have its own communication module installed at a
cost of modules [i - 1]. Alternatively, devices can communicate with each other using direct connections.

The cost of connecting two devices is given by the array connections where each connections[j] =
[device1j, device2j, costj] represents the cost to connect devices device1j and device2j. Connections are
bidirectional, and there could be multiple valid connections between the same two devices with different
costs.

Goal:
Determine the minimum total cost to connect all devices in the network.

Input:
n: The number of devices.
modules: An array of costs to install communication modules on each device.
connections: An array of connections, where each connection is represented as a triplet [device1j,
device2j, costj].
Output:
The minimum total cost to connect all devices.

Example:
Input: n = 3, modules = [1, 2, 2], connections = [[1, 2, 1], [2, 3, 1]] Output: 3
Explanation:
The best strategy is to install a communication module on the first device with cost 1 and connect the
other devices to it with cost 2, resulting in a total cost of 3
 */

 public class A_MinimumNetworkCost {
    public static int minCostToConnectDevices(int n, int[] modules, int[][] connections) {
        List<Edge> edges = new ArrayList<>();

        // Step 1: Add direct connections as edges
        for (int[] conn : connections) {
            edges.add(new Edge(conn[0], conn[1], conn[2]));
        }

        // Step 2: Add virtual edges from node 0 to every device with module cost
        for (int i = 0; i < n; i++) {
            edges.add(new Edge(0, i + 1, modules[i]));
        }

        // Step 3: Sort edges by cost (Kruskal's Algorithm)
        edges.sort(Comparator.comparingInt(e -> e.cost));

        DSU dsu = new DSU(n + 1); // DSU for nodes [0, n]
        int totalCost = 0, edgesUsed = 0;

        // Step 4: Process edges in sorted order
        for (Edge edge : edges) {
            if (dsu.union(edge.u, edge.v)) { // If adding this edge connects new components
                totalCost += edge.cost;
                edgesUsed++;
                if (edgesUsed == n)
                    break; // MST is complete when we connect n edges
            }
        }

        return totalCost; // Return the minimum total cost
    }

    public static void main(String[] args) {

        // TESTS
        // test 1
        int n1 = 3;
        int[] modules1 = { 1, 2, 2 };
        int[][] connections1 = { { 1, 2, 1 }, { 2, 3, 1 } };

        int result1 = minCostToConnectDevices(n1, modules1, connections1);
        System.out.println("Minimum total cost: " + result1); // Output: 3

        // test 2
        int n2 = 4;
        int[] modules2 = { 1, 2, 3, 4 };
        int[][] connections2 = { { 1, 2, 1 }, { 2, 3, 2 }, { 3, 4, 3 } };

        int result2 = minCostToConnectDevices(n2, modules2, connections2);
        System.out.println("Minimum total cost: " + result2); // Output: 7

        // test 3
        int n3 = 5;
        int[] modules3 = { 1, 2, 3, 4, 5 };
        int[][] connections3 = { { 1, 2, 1 }, { 2, 3, 2 }, { 3, 4, 3 }, { 4, 5, 4 } };

        int result3 = minCostToConnectDevices(n3, modules3, connections3);
        System.out.println("Minimum total cost: " + result3); // Output: 11


    }
}
// Class to represent an edge in the graph
class Edge {
    int u, v, cost;

    // Constructor to initialize an edge
    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

// Disjoint Set Union (DSU) / Union-Find Data Structure
class DSU {
    int[] parent, rank;

    public DSU(int n) {
        parent = new int[n]; // Array to store parent of each node
        rank = new int[n]; // Rank array to optimize union operation

        // Initialize each node as its own parent (initially disjoint sets)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1; // Rank initialized to 1 for all nodes
        }
    }

    // Find function with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union function to merge two sets
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return false; // Already in the same set

        // Union by rank to keep the tree balanced
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}


