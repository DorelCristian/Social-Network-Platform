package org.example.domain;

import java.util.*;

/*class Graph {
    private int V;
    private List<List<Integer>> adj;

    public Graph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    public int findConnectedComponentWithLongestPath() {
        int maxPathLength = 0;
        List<Integer> longestPath = new ArrayList<>();
        boolean[] visited = new boolean[V];

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                List<Integer> currentPath = new ArrayList<>();
                int currentMaxPathLength = 0;

                DFS(v, visited, currentPath, 0);

                // Find the longest path within the connected component
                for (int vertex : currentPath) {
                    Arrays.fill(visited, false);
                    int length = DFS(vertex, visited, new ArrayList<>(), 0);
                    if (length > currentMaxPathLength) {
                        currentMaxPathLength = length;
                        longestPath = new ArrayList<>(currentPath);
                    }
                }

                if (currentMaxPathLength > maxPathLength) {
                    maxPathLength = currentMaxPathLength;
                }
            }
        }

        System.out.println("Longest Path Length: " + maxPathLength);
        System.out.println("Longest Path: " + longestPath);
        return maxPathLength;
    }

    private int DFS(int v, boolean[] visited, List<Integer> path, int length) {
        visited[v] = true;
        path.add(v);
        length++;

        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                length = DFS(neighbor, visited, path, length);
            }
        }

        return length;
    }
}
*/
import java.util.*;

import java.util.*;

class Graph {
    private int V;
    private List<List<Integer>> adj;

    public Graph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    public List<Integer> findComponentWithLongestPath() {
        List<Integer> longestPath = new ArrayList<>();
        int maxLength = 0;
        boolean[] visited = new boolean[V];

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                List<Integer> currentPath = new ArrayList<>();
                int currentLength = DFS(v, visited, currentPath);

                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    longestPath = new ArrayList<>(currentPath);
                }
            }
        }

        return longestPath;
    }

    private int DFS(int v, boolean[] visited, List<Integer> path) {
        visited[v] = true;
        path.add(v);
        int length = 1;

        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                length += DFS(neighbor, visited, path);
            }
        }

        return length;
    }

}
