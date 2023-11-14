package org.practice.graph;

import java.util.*;
import java.io.*;

public class MinimumCostToConnectAllCities {
    static class Edge implements Comparable<Edge> {
        int v;
        int wt;

        Edge(int nbr, int wt) {
            this.v = nbr;
            this.wt = wt;
        }

        @Override
        public int compareTo(Edge o) {
            return this.wt - o.wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph.get(v1).add(new Edge(v2, wt));
            graph.get(v2).add(new Edge(v1, wt));
        }

        boolean[] visited = new boolean[vtces];
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(0, 0));
        int ans = 0;

        while (queue.size() > 0) {
            Edge rem = queue.remove();

            if (visited[rem.v]) continue;

            ans += rem.wt;
            visited[rem.v] = true;

            for (Edge e : graph.get(rem.v)) {
                if(visited[e.v]) continue;

                queue.add(e);
            }
        }

        System.out.println(ans);
    }
}
