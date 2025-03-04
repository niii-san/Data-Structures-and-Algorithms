package _coursework.Question4;

/*
 * Question 4 (B)
You have a map of a city represented by a graph with n nodes (representing locations) and edges where
edges[i] = [ai, bi] indicates a road between locations ai and bi. Each location has a value of either 0 or 1,
indicating whether there is a package to be delivered. You can start at any location and perform the
following actions:
Collect packages from all locations within a distance of 2 from your current location.
Move to an adjacent location.
Your goal is to collect all packages and return to your starting location.
Goal:
Determine the minimum number of roads you need to traverse to collect all packages.
Input:
packages: An array of package values for each location.
roads: A 2D array representing the connections between locations.
Output:
The minimum number of roads to traverse.
Note that if you pass a roads several times, you need to count it into the answer several times.
Input: packages = [1, 0, 0, 0, 0, 1], roads = [[0, 1], [1, 2], [2, 3], [3, 4], [4, 5]]
Output:2
Explanation: Start at location 2, collect the packages at location 0, move to location 3, collect the
packages at location 5 then move back to location 2.
Input: packages = [0,0,0,1,1,0,0,1], roads = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
Output: 2
Explanation: Start at location 0, collect the package at location 4 and 3, move to location 2, collect the
package at location 7, then move back to location 0.
 */

import java.util.*;

// Main class
public class B_PackageCollector {

    // Method to compute the minimum number of roads to traverse
    // in order to cover (collect) all packages and return to the starting node.
    public static int minRoadsToCollectAllPackages(int[] packages, int[][] roads) {
        int n = packages.length;

        // Build the graph as an adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // Add bidirectional roads
        for (int[] road : roads) {
            int u = road[0], v = road[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        // Map each node that has a package (value==1) to a unique bit index.
        // This will help us use a bitmask to keep track of collected packages.
        Map<Integer, Integer> packageMap = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (packages[i] == 1) {
                packageMap.put(i, idx++);
            }
        }
        int P = idx; // number of package nodes
        // If there are no packages, no road needs to be traversed.
        if (P == 0)
            return 0;

        // targetMask represents all packages collected.
        int targetMask = (1 << P) - 1;

        // Precompute "coverage" for each node.
        // For each node u, coverage[u] is a bitmask that marks
        // every package node that is within a distance of 2 from u.
        int[] coverage = new int[n];
        for (int u = 0; u < n; u++) {
            int cov = 0;
            // BFS from node u up to depth 2
            Queue<int[]> queue = new LinkedList<>();
            boolean[] vis = new boolean[n];
            queue.offer(new int[] { u, 0 }); // {node, distance}
            vis[u] = true;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curNode = cur[0];
                int dist = cur[1];
                // If the current node has a package, add it to the coverage mask.
                if (packages[curNode] == 1 && packageMap.containsKey(curNode)) {
                    cov |= (1 << packageMap.get(curNode));
                }
                // Only proceed if we haven't reached distance 2.
                if (dist < 2) {
                    for (int nei : graph[curNode]) {
                        if (!vis[nei]) {
                            vis[nei] = true;
                            queue.offer(new int[] { nei, dist + 1 });
                        }
                    }
                }
            }
            coverage[u] = cov;
        }

        int ans = Integer.MAX_VALUE;

        // Try every node as a potential starting location.
        // Our route must start and end at the same node.
        for (int start = 0; start < n; start++) {
            int costForThisStart = bfsForStart(start, graph, coverage, targetMask, n);
            ans = Math.min(ans, costForThisStart);
        }

        return ans;
    }

    // State class for BFS: current node, current mask of collected packages, and
    // cost so far.
    static class State {
        int node, mask, cost;

        public State(int node, int mask, int cost) {
            this.node = node;
            this.mask = mask;
            this.cost = cost;
        }
    }

    // Perform BFS starting from a given node 'start'
    // Returns the minimal cost (number of roads traversed) to return to 'start'
    // while having collected all packages (i.e. mask == targetMask).
    private static int bfsForStart(int start, List<Integer>[] graph, int[] coverage, int targetMask, int n) {
        // visited[node][mask] marks whether we've visited a state
        boolean[][] visited = new boolean[n][1 << (Integer.bitCount(targetMask))];
        Queue<State> queue = new LinkedList<>();
        // The initial state: at start, with the coverage of the start node.
        int initialMask = coverage[start];
        State init = new State(start, initialMask, 0);
        queue.offer(init);
        visited[start][initialMask] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            // If we are back at the starting node and have collected all packages, return
            // the cost.
            if (cur.node == start && cur.mask == targetMask && cur.cost > 0) {
                return cur.cost;
            }
            // Explore adjacent nodes.
            for (int nei : graph[cur.node]) {
                int newMask = cur.mask | coverage[nei];
                // If this state hasn't been visited, add it.
                if (!visited[nei][newMask]) {
                    visited[nei][newMask] = true;
                    queue.offer(new State(nei, newMask, cur.cost + 1));
                }
            }
        }
        // If no valid route is found for this start, return a large number.
        return Integer.MAX_VALUE;
    }

    // Main method for testing our solution with provided examples.
    public static void main(String[] args) {
        // Example 1:
        int[] packages1 = { 1, 0, 0, 0, 0, 1 };
        int[][] roads1 = {
                { 0, 1 },
                { 1, 2 },
                { 2, 3 },
                { 3, 4 },
                { 4, 5 }
        };
        System.out.println("Output for Example 1: " + minRoadsToCollectAllPackages(packages1, roads1));
        // output: 2

        // Example 2:
        int[] packages2 = { 0, 0, 0, 1, 1, 0, 0, 1 };
        int[][] roads2 = {
                { 0, 1 },
                { 0, 2 },
                { 1, 3 },
                { 1, 4 },
                { 2, 5 },
                { 5, 6 },
                { 5, 7 }
        };
        System.out.println("Output for Example 2: " + minRoadsToCollectAllPackages(packages2, roads2));
        // output: 2

        // Example 3:
        int[] packages3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
        int[][] roads3 = {
                { 0, 1 },
                { 1, 2 },
                { 2, 3 },
                { 3, 4 },
                { 4, 5 },
                { 5, 6 },
                { 6, 7 }
        };
        System.out.println("Output for Example 3: " + minRoadsToCollectAllPackages(packages3, roads3));
        // output: 6
    }
}
