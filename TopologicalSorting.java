import queues.LinearQueue;

public class TopologicalSorting {
    int toposort(int adjmatrix[][], int v) {

        int indegree[] = new int[v];
        LinearQueue q = new LinearQueue(v);

        // * calc indegree
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (adjmatrix[i][j] != 0) {
                    indegree[j]++;
                }
            }
        }

        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                q.enqueue(i);
            }
        }

        int count = 0;

        while (!q.isEmpty()) {
            count++;
            int x = q.dequeue();
            System.out.println(x);

            for (int j = 0; j < v; j++) {
                if (adjmatrix[x][j] != 0) {
                    indegree[j]--;
                    if (indegree[j] == 0) {
                        q.enqueue(j);
                    }

                }
            }
        }

        if (count != v) {
            System.out.println("Cycle detected");
            return 1;
        }

        return -1;
    }
}