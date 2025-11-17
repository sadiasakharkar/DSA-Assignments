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

class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

class GraphMST {
    int v;
    CustomLinkedList adj[];

    GraphMST(int v) {
        this.v = v;
        adj = new CustomLinkedList[v + 1];
        for (int i = 1; i <= v; i++)
            adj[i] = new CustomLinkedList();
    }

    void addEdge(int a, int b, int w) {
        adj[a].addLast(b, w);
        adj[b].addLast(a, w);
    }

    int find(int parent[], int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent, parent[x]);
    }

    void maxBandwidth() {
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 1; i <= v; i++) {
            Node t = adj[i].head;
            while (t != null) {
                if (i < t.data)
                    edges.add(new Edge(i, t.data, t.weight));
                t = t.next;
            }
        }

        edges.sort((a, b) -> b.weight - a.weight);

        int parent[] = new int[v + 1];
        for (int i = 1; i <= v; i++)
            parent[i] = i;

        int sum = 0, count = 0;

        for (Edge e : edges) {
            int pa = find(parent, e.src);
            int pb = find(parent, e.dest);

            if (pa != pb) {
                parent[pb] = pa;
                sum += e.weight;
                count++;
            }
            if (count == v - 1)
                break;
        }

        System.out.println("Max Bandwidth Sum: " + sum);
    }
}

public class MaxBandwidthOnly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();

        GraphMST g = new GraphMST(v);

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(a, b, w);
        }

        g.maxBandwidth();
    }
}
