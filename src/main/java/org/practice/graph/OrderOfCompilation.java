package org.practice.graph;

import java.io.*;
import java.util.*;

public class OrderOfCompilation {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
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
            graph[v1].add(new Edge(v1, v2));
        }

        // write your code here
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vtces];

        for (int i = 0; i < vtces; i++) {
            if (visited[i]) continue;

            topologicalSort(graph, i, visited, stack);
        }

        while (stack.size() > 0) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    public static void topologicalSort(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> stack) {
        if (visited[src]) return;

        visited[src] = true;
        for (var edge : graph[src]) {
            if (visited[edge.nbr]) continue;

            topologicalSort(graph, edge.nbr, visited, stack);
        }

        stack.push(src);
    }
}
