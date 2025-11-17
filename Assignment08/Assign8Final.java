package Assignment08;

// import Assignment07.Node;
// import Assignment03.Node;

// Name: Sadia Sakharkar

// Roll No: UCE2025002
// Batch: A4
// Assignment 08

// Problem Statement: You are given a weighted, undirected graph G with n stations (1 ≤ n ≤ 10^5)
// and m communication channels (n−1 ≤ m ≤ 2×10^5).
// Each channel has an integer bandwidth (1 ≤ bandwidth ≤ 10^9).
// Task 1: Find the path from one station to another station
// Task 2: Select n−1 channels so that all stations are connected and the
// sum of the bandwidths is maximized. Print this sum

import java.util.*;

class Node {
    int data;
    int weight;
    Node next;

    Node(int data, int weight) {
        this.data = data;
        this.weight = weight;
        this.next = null;
    }
}

class CustomLinkedList {
    Node head;

    public void addLast(int data, int weight) {
        Node newNode = new Node(data, weight);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
    }
}

// Class to store edges (used in Kruskal’s algorithm)
class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

// Graph class handles all graph operations
class GraphList {
    int v; // number of vertices
    CustomLinkedList adjList[];

    GraphList(int v) {
        this.v = v;
        adjList = new CustomLinkedList[v + 1]; // 1-based indexing
        for (int i = 1; i <= v; i++) {
            adjList[i] = new CustomLinkedList();
        }
    }

    // Function to create graph safely with validation
    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        if (e <= 0) {
            System.out.println("Graph must have at least one edge!");
            return;
        }

        for (int i = 0; i < e; i++) {
            System.out.print("\nEnter edge " + (i + 1) + " (src dest weight): ");
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();

            // Validation for vertex range
            if (v1 < 1 || v1 > v || v2 < 1 || v2 > v) {
                System.out.println(" Invalid vertex! Skipping edge...");
                i--; // repeat this edge input
                continue;
            }

            // Avoid self-loops
            if (v1 == v2) {
                System.out.println("Self loop not allowed! Try again");
                i--;
                continue;
            }

            adjList[v1].addLast(v2, w);
            adjList[v2].addLast(v1, w);
        }

        System.out.println("\n✅ Graph created");
    }

    // Display adjacency list (for understanding)
    public void displayGraph() {
        System.out.println("\nAdjacency List:");
        for (int i = 1; i <= v; i++) {
            System.out.print(i + " → ");
            Node temp = adjList[i].head;
            while (temp != null) {
                System.out.print("(" + temp.data + ", w=" + temp.weight + ") → ");
                temp = temp.next;
            }
            System.out.println("null");
        }
    }

    // ✅ DFS Path Find
    void dfs(int s, int e, boolean[] vis, ArrayList<Integer> path) {
        path.add(s);
        if (s == e)
            return;
        vis[s] = true;

        Node temp = adjList[s].head;
        while (temp != null) {
            if (!vis[temp.data]) {
                dfs(temp.data, e, vis, path);
                if (path.get(path.size() - 1) == e)
                    return;
            }
            temp = temp.next;
        }
        path.remove(path.size() - 1);
    }

    void findPathDFS(int start, int end) {
        boolean vis[] = new boolean[v + 1];
        ArrayList<Integer> path = new ArrayList<>();
        dfs(start, end, vis, path);

        if (!path.isEmpty() && path.get(path.size() - 1) == end)
            System.out.println("✅ Path via DFS: " + path);
        else
            System.out.println("❌ No path found");
    }

    // ✅ BFS Traversal
    public void BFS(int start) {
        boolean visited[] = new boolean[v + 1];
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visited[start] = true;

        System.out.print("\nBFS Traversal: ");
        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");

            Node temp = adjList[cur].head;
            while (temp != null) {
                if (!visited[temp.data]) {
                    visited[temp.data] = true;
                    q.add(temp.data);
                }
                temp = temp.next;
            }
        }
        System.out.println();
    }

    // ✅ Maximum Spanning Tree (Kruskal)
    public void findMaxBandwidth() {
        ArrayList<Edge> edges = new ArrayList<>();

        // Collect all edges from adjacency list
        for (int i = 1; i <= v; i++) {
            Node temp = adjList[i].head;
            while (temp != null) {
                if (i < temp.data)
                    edges.add(new Edge(i, temp.data, temp.weight));
                temp = temp.next;
            }
        }

        if (edges.isEmpty()) {
            System.out.println("\nNo edges to calculate bandwidth!");
            return;
        }

        // Sort edges in descending order (for maximum spanning tree)
        edges.sort((a, b) -> b.weight - a.weight);

        int parent[] = new int[v + 1];
        for (int i = 1; i <= v; i++)
            parent[i] = i;

        int cost = 0, count = 0;
        for (Edge e : edges) {
            int a = find(parent, e.src);
            int b = find(parent, e.dest);
            if (a != b) {
                cost += e.weight;
                parent[b] = a;
                count++;
            }
            if (count == v - 1)
                break;
        }

        System.out.println("\n🌐 Max Bandwidth Sum: " + cost);
    }

    int find(int[] parent, int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent, parent[x]);
    }
}

// Main class
public class Assign8Final {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        if (v <= 0) {
            System.out.println("Graph must have at least one vertex!");
            return;
        }

        GraphList g = new GraphList(v);
        g.createGraph();
        g.displayGraph();

        System.out.print("\nEnter start for DFS+BFS: ");
        int start = sc.nextInt();
        System.out.print("Enter end for DFS path: ");
        int end = sc.nextInt();

        g.findPathDFS(start, end);
        g.BFS(start);
        g.findMaxBandwidth();

        System.out.println("\n✅ Execution completed");
    }
}

// OUTPUT:
// Enter number of vertices: 4
// Enter number of edges: 5

// Enter edge 1 (src dest weight): 1 2 10
// Enter edge 2 (src dest weight): 1 3 6
// Enter edge 3 (src dest weight): 2 3 5
// Enter edge 4 (src dest weight): 2 4 15
// Enter edge 5 (src dest weight): 3 4 4

// ✅ Graph created successfully!

// Adjacency List:
// 1 → (2, w=10) → (3, w=6) → null
// 2 → (1, w=10) → (3, w=5) → (4, w=15) → null
// 3 → (1, w=6) → (2, w=5) → (4, w=4) → null
// 4 → (2, w=15) → (3, w=4) → null

// Enter start vertex: 1
// Enter end vertex: 4

// --- DFS Path ---
// Path found ✅ [1, 2, 4]

// --- BFS Traversal ---
// BFS from 1: 1 -> 2 -> 3 -> 4

// --- Maximum Bandwidth ---
// Edges selected (Max-Spanning-Tree):
// 2 - 4 | w = 15
// 1 - 2 | w = 10
// 1 - 3 | w = 6

// 🌐 Max Bandwidth Sum: 31

// Program executed successfully ✅
