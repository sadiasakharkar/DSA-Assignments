package Assignment08;

import java.util.*;

class Node {
    int data, weight;
    Node next;

    Node(int d, int w) {
        data = d;
        weight = w;
    }
}

class CustomLinkedList {
    Node head;

    void addLast(int data, int weight) {
        Node newNode = new Node(data, weight);
        if (head == null) {
            head = newNode;
            return;
        }
        Node t = head;
        while (t.next != null)
            t = t.next;
        t.next = newNode;
    }
}

class GraphDFS {
    int v;
    CustomLinkedList adj[];

    GraphDFS(int v) {
        this.v = v;
        adj = new CustomLinkedList[v + 1];
        for (int i = 1; i <= v; i++)
            adj[i] = new CustomLinkedList();
    }

    void addEdge(int a, int b, int w) {
        adj[a].addLast(b, w);
        adj[b].addLast(a, w);
    }

    void dfs(int s, int e, boolean[] vis, ArrayList<Integer> path) {
        path.add(s);
        if (s == e)
            return;
        vis[s] = true;

        Node t = adj[s].head;
        while (t != null) {
            if (!vis[t.data]) {
                dfs(t.data, e, vis, path);
                if (path.get(path.size() - 1) == e)
                    return;
            }
            t = t.next;
        }
        path.remove(path.size() - 1);
    }

    void findPath(int start, int end) {
        boolean vis[] = new boolean[v + 1];
        ArrayList<Integer> path = new ArrayList<>();
        dfs(start, end, vis, path);

        if (!path.isEmpty() && path.get(path.size() - 1) == end)
            System.out.println("Path Found: " + path);
        else
            System.out.println("No path exists!");
    }

    // BFS if required (not used in this code)
    /*
     * void BFS(int start) {
     * boolean visited[] = new boolean[v + 1];
     * Queue<Integer> q = new LinkedList<>();
     * 
     * q.add(start);
     * visited[start] = true;
     * 
     * while (!q.isEmpty()) {
     * int cur = q.poll();
     * System.out.print(cur + " ");
     * 
     * Node t = adj[cur].head;
     * while (t != null) {
     * if (!visited[t.data]) {
     * visited[t.data] = true;
     * q.add(t.data);
     * }
     * t = t.next;
     * }
     * }
     * }
     */
}

public class DFSPathOnly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();

        GraphDFS g = new GraphDFS(v);

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(a, b, w);
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        g.findPath(start, end);
    }
}
