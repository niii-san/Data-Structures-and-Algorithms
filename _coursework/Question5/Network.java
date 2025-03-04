package _coursework.Question5;

/*
 * Question 5
Optimizing a Network with Multiple Objectives
Problem:
Suppose you are hired as software developer for certain organization and you are tasked with creating a
GUI application that helps network administrators design a network topology that is both cost-effective
and efficient for data transmission. The application needs to visually represent servers and clients as
nodes in a graph, with potential network connections between them, each having associated costs and
bandwidths. The goal is to enable the user to find a network topology that minimizes both the total cost
and the latency of data transmission.
Approach:
1. Visual Representation of the Network:
o Design the GUI to allow users to create and visualize a network graph where each node
represents a server or client, and each edge represents a potential network connection. The
edges should display associated costs and bandwidths.
2. Interactive Optimization:
o Implement tools within the GUI that enable users to apply algorithms or heuristics to
optimize the network. The application should provide options to find the best combination
of connections that minimizes the total cost while ensuring all nodes are connected.
3. Dynamic Path Calculation:
o Include a feature where the user can calculate the shortest path between any pair of nodes
within the selected network topology. The GUI should display these paths, taking into
account the bandwidths as weights.
4. Real-time Evaluation:
o Provide real-time analysis within the GUI that displays the total cost and latency of the
current network topology. If the user is not satisfied with the results, they should be able
to adjust the topology and explore alternative solutions interactively.
Example:
 Input: The user inputs a graph in the application, representing servers, clients, potential
connections, their costs, and bandwidths.
 Output: The application displays the optimal network topology that balances cost and latency,
and shows the shortest paths between servers and clients on the GUI.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Network {
    public static void main(String[] args) {
        // These properties may help avoid blurry rendering on Wayland systems.
        // Force a fixed UI scale and use GTK2 look and feel instead of GTK3.
        System.setProperty("sun.java2d.uiScale", "1.0");
        System.setProperty("jdk.gtk.version", "2");
        // Optionally, you can disable XRender if it causes issues:
        System.setProperty("sun.java2d.xrender", "false");

        SwingUtilities.invokeLater(() -> {
            new NetworkFrame();
        });
    }
}

// Represents a node in the network (server or client)
class Node {
    static int counter = 0;
    int id;
    int x, y;
    String label; // e.g., "Node 0", "Node 1", etc.

    public Node(int x, int y) {
        this.id = counter++;
        this.x = x;
        this.y = y;
        this.label = "Node " + id;
    }
}

// Represents an edge between two nodes with cost and bandwidth values.
class Edge {
    Node from;
    Node to;
    double cost;
    double bandwidth; // assume > 0

    public Edge(Node from, Node to, double cost, double bandwidth) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.bandwidth = bandwidth;
    }
}

// Holds the network graph data.
class Graph {
    List<Node> nodes;
    List<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void clear() {
        nodes.clear();
        edges.clear();
        Node.counter = 0;
    }

    // Computes the minimum spanning tree (MST) using Kruskal’s algorithm based on
    // cost.
    public List<Edge> getMinimumSpanningTree() {
        List<Edge> result = new ArrayList<>();
        if (nodes.isEmpty())
            return result;

        // Sort edges by cost.
        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(Comparator.comparingDouble(e -> e.cost));

        // Use disjoint set union (DSU) for cycle detection.
        DisjointSet ds = new DisjointSet(nodes.size());
        for (Edge edge : sortedEdges) {
            int u = edge.from.id;
            int v = edge.to.id;
            if (ds.find(u) != ds.find(v)) {
                ds.union(u, v);
                result.add(edge);
            }
        }
        return result;
    }

    // Finds the shortest path between two nodes using Dijkstra’s algorithm.
    // Here, we use weight = 1.0 / bandwidth to simulate latency (higher bandwidth
    // means lower latency).
    public List<Edge> getShortestPath(Node start, Node end) {
        // Build an undirected adjacency list.
        Map<Node, List<Edge>> adj = new HashMap<>();
        for (Node n : nodes) {
            adj.put(n, new ArrayList<>());
        }
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            // Add the reverse edge for an undirected graph.
            adj.get(e.to).add(new Edge(e.to, e.from, e.cost, e.bandwidth));
        }

        Map<Node, Double> dist = new HashMap<>();
        Map<Node, Edge> prev = new HashMap<>();
        for (Node n : nodes) {
            dist.put(n, Double.POSITIVE_INFINITY);
        }
        dist.put(start, 0.0);

        PriorityQueue<NodeDist> pq = new PriorityQueue<>(Comparator.comparingDouble(nd -> nd.distance));
        pq.add(new NodeDist(start, 0.0));

        while (!pq.isEmpty()) {
            NodeDist current = pq.poll();
            Node u = current.node;
            if (u == end)
                break;
            if (current.distance > dist.get(u))
                continue;
            for (Edge e : adj.get(u)) {
                double weight = 1.0 / e.bandwidth;
                double alt = dist.get(u) + weight;
                if (alt < dist.get(e.to)) {
                    dist.put(e.to, alt);
                    prev.put(e.to, e);
                    pq.add(new NodeDist(e.to, alt));
                }
            }
        }

        // Reconstruct the path.
        List<Edge> path = new ArrayList<>();
        if (!prev.containsKey(end)) {
            return path; // no path found.
        }
        Node current = end;
        while (current != start) {
            Edge e = prev.get(current);
            path.add(e);
            current = e.from;
        }
        Collections.reverse(path);
        return path;
    }

    // Sums the cost of a list of edges.
    public double getTotalCost(List<Edge> edgeList) {
        double total = 0;
        for (Edge e : edgeList) {
            total += e.cost;
        }
        return total;
    }

    // Sums the latency (using 1/bandwidth) of a list of edges.
    public double getTotalLatency(List<Edge> edgeList) {
        double total = 0;
        for (Edge e : edgeList) {
            total += 1.0 / e.bandwidth;
        }
        return total;
    }
}

// Helper class for Dijkstra algorithm.
class NodeDist {
    Node node;
    double distance;

    public NodeDist(Node node, double distance) {
        this.node = node;
        this.distance = distance;
    }
}

// Disjoint set union (DSU) for use in Kruskal’s MST algorithm.
class DisjointSet {
    int[] parent;

    public DisjointSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}

// Interaction modes for the drawing panel.
enum Mode {
    NONE, ADD_NODE, ADD_EDGE, SELECT_PATH
}

// The panel that draws the network graph.
class NetworkPanel extends JPanel {
    Graph graph;
    Mode currentMode = Mode.NONE;

    // Used for edge creation or path selection.
    Node firstSelected = null;
    // Highlighted edges from optimization and path calculation.
    List<Edge> mstEdges = new ArrayList<>();
    List<Edge> shortestPathEdges = new ArrayList<>();

    public NetworkPanel(Graph graph) {
        this.graph = graph;
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    // Set the current mode.
    public void setMode(Mode mode) {
        currentMode = mode;
        firstSelected = null;
        if (mode != Mode.SELECT_PATH) {
            shortestPathEdges.clear();
        }
        repaint();
    }

    // Handle mouse clicks based on mode.
    private void handleMouseClick(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        switch (currentMode) {
            case ADD_NODE:
                // Add a new node at the clicked position.
                Node newNode = new Node(x, y);
                graph.nodes.add(newNode);
                repaint();
                break;
            case ADD_EDGE:
                // Select two nodes to create an edge.
                Node clickedNode = getNodeAtPosition(x, y);
                if (clickedNode != null) {
                    if (firstSelected == null) {
                        firstSelected = clickedNode;
                    } else if (firstSelected != clickedNode) {
                        // Prompt for edge parameters.
                        String costStr = JOptionPane.showInputDialog(this, "Enter cost for edge:");
                        String bwStr = JOptionPane.showInputDialog(this, "Enter bandwidth for edge:");
                        try {
                            double cost = Double.parseDouble(costStr);
                            double bandwidth = Double.parseDouble(bwStr);
                            Edge newEdge = new Edge(firstSelected, clickedNode, cost, bandwidth);
                            graph.edges.add(newEdge);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input. Edge not created.");
                        }
                        firstSelected = null;
                        repaint();
                    }
                }
                break;
            case SELECT_PATH:
                // Select two nodes to compute the shortest path.
                Node node = getNodeAtPosition(x, y);
                if (node != null) {
                    if (firstSelected == null) {
                        firstSelected = node;
                    } else if (firstSelected != node) {
                        shortestPathEdges = graph.getShortestPath(firstSelected, node);
                        if (shortestPathEdges.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No path found between selected nodes.");
                        }
                        firstSelected = null;
                        repaint();
                    }
                }
                break;
            default:
                break;
        }
    }

    // Returns a node if the (x,y) falls within its drawn circle.
    private Node getNodeAtPosition(int x, int y) {
        int radius = 10;
        for (Node node : graph.nodes) {
            int dx = node.x - x;
            int dy = node.y - y;
            if (dx * dx + dy * dy <= radius * radius) {
                return node;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw all edges.
        for (Edge edge : graph.edges) {
            if (mstEdges.contains(edge)) {
                g.setColor(Color.GREEN);
                ((Graphics2D) g).setStroke(new BasicStroke(3));
            } else if (shortestPathEdges.contains(edge)) {
                g.setColor(Color.MAGENTA);
                ((Graphics2D) g).setStroke(new BasicStroke(3));
            } else {
                g.setColor(Color.GRAY);
                ((Graphics2D) g).setStroke(new BasicStroke(1));
            }
            g.drawLine(edge.from.x, edge.from.y, edge.to.x, edge.to.y);
            // Label the edge with cost and bandwidth at its midpoint.
            int midX = (edge.from.x + edge.to.x) / 2;
            int midY = (edge.from.y + edge.to.y) / 2;
            g.setColor(Color.BLACK);
            g.drawString("C:" + edge.cost + " B:" + edge.bandwidth, midX, midY);
        }

        // Draw nodes.
        for (Node node : graph.nodes) {
            g.setColor(Color.BLUE);
            g.fillOval(node.x - 10, node.y - 10, 20, 20);
            g.setColor(Color.WHITE);
            g.drawString(node.label, node.x - 15, node.y + 4);
        }
    }
}

// The main frame that contains the network panel and control buttons.
class NetworkFrame extends JFrame {
    Graph graph;
    NetworkPanel networkPanel;
    JLabel statusLabel;

    public NetworkFrame() {
        setTitle("Network Optimizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        graph = new Graph();
        networkPanel = new NetworkPanel(graph);
        add(networkPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton addNodeBtn = new JButton("Add Node");
        JButton addEdgeBtn = new JButton("Add Edge");
        JButton optimizeBtn = new JButton("Optimize");
        JButton shortestPathBtn = new JButton("Shortest Path");
        JButton resetBtn = new JButton("Reset");

        controlPanel.add(addNodeBtn);
        controlPanel.add(addEdgeBtn);
        controlPanel.add(optimizeBtn);
        controlPanel.add(shortestPathBtn);
        controlPanel.add(resetBtn);

        statusLabel = new JLabel("Status: ");
        controlPanel.add(statusLabel);
        add(controlPanel, BorderLayout.SOUTH);

        // Button actions.
        addNodeBtn.addActionListener(e -> {
            networkPanel.setMode(Mode.ADD_NODE);
            statusLabel.setText("Status: Click on panel to add nodes.");
        });

        addEdgeBtn.addActionListener(e -> {
            networkPanel.setMode(Mode.ADD_EDGE);
            statusLabel.setText("Status: Click two nodes to create an edge.");
        });

        optimizeBtn.addActionListener(e -> {
            List<Edge> mst = graph.getMinimumSpanningTree();
            networkPanel.mstEdges = mst;
            double totalCost = graph.getTotalCost(mst);
            statusLabel.setText("Status: MST computed. Total cost: " + totalCost);
            networkPanel.repaint();
        });

        shortestPathBtn.addActionListener(e -> {
            networkPanel.setMode(Mode.SELECT_PATH);
            statusLabel.setText("Status: Click two nodes to compute shortest path.");
        });

        resetBtn.addActionListener(e -> {
            graph.clear();
            networkPanel.mstEdges.clear();
            networkPanel.shortestPathEdges.clear();
            networkPanel.setMode(Mode.NONE);
            statusLabel.setText("Status: Graph reset.");
            networkPanel.repaint();
        });

        setVisible(true);
    }
}
