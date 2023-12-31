package org.practice.graph;

import java.util.*;
import java.io.*;

public class IsGraphBipartite {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        // write your code here
        int[][] visited = new int[vtces][2];
        for (int i = 0; i < vtces; i++) {
            if (visited[i][0] > 0) continue;
            boolean isBip = isBipartite(graph, visited, i);
            if (isBip) {
                System.out.println("True");
                return;
            }
        }

        System.out.println("False");
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph, int[][] visited, int src) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0});

        while (queue.size() > 0) {
            int[] node = queue.remove();

            if (visited[node[0]][0] > 0) {
                int lastLevel = visited[node[0]][1];
                if (lastLevel % 2 != node[1] % 2) return false;
            } else {
                visited[node[0]][0] = node[0];
                visited[node[0]][1] = node[1];
            }

            for (var edge : graph[node[0]]) {
                if (visited[edge.nbr][0] > 0) continue;

                queue.add(new int[]{edge.nbr, node[1] + 1});
            }
        }

        return true;
    }
}
