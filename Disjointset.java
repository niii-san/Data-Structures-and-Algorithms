public class Disjointset {

    int size[];
    int parent[];
    int vertices;

    public Disjointset(int vertices) {
        this.vertices = vertices;
        size = new int[vertices];
        parent = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = -1;
        }
    }
    // TODO: make graph

    void findCycle(int u, int v) {
        int uabsroot = find(u);
        int vbasroot = find(v);
        if (uabsroot == vbasroot) {
            System.out.println("Cycle detected");
        } else {
            // add edge to graph
            union(uabsroot, uabsroot);
        }
    }

    void union(int uabsroot, int vbasroot) {
        if (size[uabsroot] > size[vbasroot]) {
            parent[vbasroot] = uabsroot;
        } else if (size[uabsroot] < size[vbasroot]) {
            parent[uabsroot] = vbasroot;

        }
    }

    int find(int u) {
        if (parent[u] == -1) {
            return u;
        }
        return find(parent[u]);
    }


    public static void main(String[] args) {

        Disjointset d1 = new Disjointset(5);
        d1.findCycle(0, 1);
        d1.findCycle(1 ,2);
        d1.findCycle(2, 3);
        d1.findCycle(0, 0););
        
    }

}
