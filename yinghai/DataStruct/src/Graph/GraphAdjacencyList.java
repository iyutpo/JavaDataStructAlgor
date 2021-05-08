package Graph;

/*
Graph.java 中我们用邻接矩阵的方式实现了一个Graph，但相比于邻接链表（Adjacency List），邻接矩阵需要非常大的存储空间。因此这里我们用邻接链表来实现一个graph：
*           C------B-----E                  [0, 1, 1, 0, 0]
*           |    / |                        [1, 0, 1, 1, 1]
*           |  /   |             =====>     [1, 1, 0, 0, 0]
*            A     D                        [0, 1, 0, 0, 0]
*                                           [0, 1, 0, 0, 0]
 */

import java.util.Iterator;
import java.util.LinkedList;

public class GraphAdjacencyList {
    private int V;          // 节点的个数
    private LinkedList<Integer> adj[];

    // Constructor
    @SuppressWarnings("unchecked") GraphAdjacencyList(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList();
    }
    // 定义一个添加edge的 function：
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }
    // DFS的一个helper function
    private void DFShelper(int v, boolean visited[]) {
        visited[v] = true;
        System.out.println(v + " ");
        //
        Iterator<Integer> i = adj[v].listIterator();     // Convert Linked List to Iterator
        while (i.hasNext()) {           // loop thru the Iterator(i.e. linked list adj)
            int n = i.next();           // if adj has "next" pointer
            if (!visited[n])            // and if visited[n] is not visited
                DFShelper(n, visited);  // then do recursion
        }
    }
    public void DFS(int v) {
        boolean visited[] = new boolean[V];
        DFShelper(v, visited);
    }

    public static void main(String[] args) {
        GraphAdjacencyList g = new GraphAdjacencyList(5);
        // 添加边（addEdge默认是有向的，所以要添加两次实现无向图）
        g.addEdge(0, 1);      // 0-1
        g.addEdge(1, 0);      // 1-0
        g.addEdge(0, 2);      // 0-2
        g.addEdge(2, 0);      // 2-0
        g.addEdge(1, 2);      // 1-2
        g.addEdge(2, 1);      // 2-1
        g.addEdge(1, 3);      // 1-3
        g.addEdge(3, 1);      // 3-1
        g.addEdge(1, 4);      // B-E
        g.addEdge(4, 1);      // B-E

        System.out.println("Following is Depth First Traversal "
                + "(starting from vertex 2)");
        g.DFS(2);
    }
}

























