package Digestra;

import java.lang.*;
import java.util.Scanner;

/**
 * Created by sadegh pc on 5/28/2018.
 */
public class DijkstraAlgorithm {
    //getting the length of the proximity matrix
    public int getLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please enter the number of nodes that you want : ");
        int length = scanner.nextInt();
        return length;

    }

    //set the length as a fixed variable
    final int length = getLength();

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src) {
        int[] dist = new int[length]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean[] sptSet = new Boolean[length];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < length; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < length - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < length; v++) {
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // print the constructed distance array
        printSolution(dist);
    }

    int minDistance(int[] dist, Boolean[] sptSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < length; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }

        return minIndex;
    }

    // A utility function to print the constructed distance array
    void printSolution(int[] dist) {
        System.out.println("vertex - distance from source");
        for (int i = 0; i < length; i++)
            System.out.println("   " + i + " -------> " + dist[i]);

    }

    // Driver method
    public static void main(String[] args) {
        System.out.println("*****************************************");
        System.out.println("\tM.S.Hadipour's Dijkstra Algorithm ");
        System.out.println("*****************************************");
        Scanner scanner = new Scanner(System.in);
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        int length = algorithm.length;
        int[][] v = new int[length][length];
        //Initialization of proximity matrix
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j > i) {
                    System.out.print("please enter weight of node [" + i + "][" + j + "] : ");
                    v[i][j] = scanner.nextInt();
                    v[j][i] = v[i][j];
                }
            }
        }
        //showing the proximity matrix
        System.out.println("you entered the following proximity matrix :");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }
        //checking proximity matrix
        System.out.print("do you want to change the relationships? (y|n) : ");
        String accept1 = scanner.next();
        char accept2 = accept1.charAt(0);
        while (accept2 == 'y') {
            int i;
            int j;
            System.out.print("enter the row that you want to change : ");
            i = scanner.nextInt();
            System.out.print("enter the column that you want to change : ");
            j = scanner.nextInt();
            if (i == j) {
                System.out.println("\nthis relationship is not available...\n");
                System.out.println("\nplease check the following matrix:\n");
                for (i = 0; i < length; i++) {
                    for (j = 0; j < length; j++) {
                        System.out.print(v[i][j] + "\t");
                    }
                    System.out.println();
                }
                System.out.print("do you want to change the relationships? (y|n) > ");
                accept1 = scanner.next();
                accept2 = accept1.charAt(0);
            } else {
                System.out.println("enter weight of node [" + i + "][" + j + "] : ");
                v[i][j] = scanner.nextInt();
                v[j][i] = v[i][j];
                System.out.println("\nplease check the following matrix:\n");
                for (i = 0; i < length; i++) {
                    for (j = 0; j < length; j++) {
                        System.out.print(v[i][j] + "\t");
                    }
                    System.out.println();
                }
                System.out.println("do you want to change the relationships? (y|n) > ");
                accept1 = scanner.next();
                accept2 = accept1.charAt(0);
            }
        }
        //getting the starter nodes
        System.out.print("please enter the Starter node (it must be a number) : ");
        int startRow = scanner.nextInt();
        System.out.println("\nTHE SYSTEM IS CALCULATING...\n");
        algorithm.dijkstra(v, startRow);
    }
}
