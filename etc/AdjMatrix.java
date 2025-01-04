package etc;

import linkedlist.SinglyLinkedList;

public class AdjMatrix {

    int[][] matrix;
    int vertices;

    public AdjMatrix(int vertices){
        this.vertices=vertices;
        matrix = new int[vertices][vertices];
    }
    
    void addEdges(int u,int v){
        matrix[u][v]=1;
        matrix[v][u]=1;
    }

    void printGraph(){
            // * row */
        for(int i=0;i<vertices;i++){
            // *column 
            System.out.print(i+" is connected to ");
            for(int j=0;j<vertices;j++){
                if(matrix[i][j]!=0){
                    // * i, j are connected
                    System.out.print(j+", ");
                }

            }
            System.out.println();
        }
    }

    SinglyLinkedList getAjdNodes(int i){
        SinglyLinkedList adjNodes = new SinglyLinkedList();
        for(int j=0;j<vertices;j++){
            if (matrix[i][j]!=0) {
                adjNodes.addNode(j);
            }
        }
        return adjNodes;
    }

    void printMatrix(){
        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){


            }
        }
    }

    public static void main(String[] args){
        AdjMatrix adj = new AdjMatrix(5);
        adj.addEdges(0, 1);
        adj.addEdges(0, 2);
        adj.addEdges(1, 3);
        adj.addEdges(1, 4);
        adj.addEdges(2, 3);
        adj.addEdges(3, 4);
        adj.printGraph();

    }

    
}