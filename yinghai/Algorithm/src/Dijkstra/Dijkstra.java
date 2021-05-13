package Dijkstra;

/*
* Dijkstra's Algorithm: 是一种用于求解单源最短路问题的算法。
* 由于涉及到绘图讲解，所以仍然推荐先阅读DijkstraAlgorithm.md 文件
*
* */

import java.util.Arrays;

// 首先创建一个Graph类：
class Graph {
    private char[] vertex;          // 顶点数组
    private int[][] matrix;         // 邻接矩阵
    private VisistedVertex visited; // 已经被访问过的顶点的集合

    // Constructor
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    // 打印图：
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    // Dijkstra's Algorithm
    public void dijkstra(int index) {
        /**
         * @param index --> 出发顶点的索引
         */
        visited = new VisistedVertex(vertex.length, index);
        update(index);          // 更新index顶点到周围顶点的距离和前驱顶点
    }

    // 更新index下标顶点 到周围顶点的距离和周围顶点的前驱节点
    private void update(int index) {
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {        // 遍历邻接矩阵
            len = visited.getDis(index) + matrix[index][j];     // len是 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离之和
            if(!visited.in(j) && len < visited.getDis(j)) {      // 如果顶点j 未被访问，且len < 出发顶点到j顶点的距离，就需要更新
                visited.updatePre(j, index);    // 更新j顶点的前驱节点为index顶点
                visited.updateDis(j, len);      // 更新出发顶点到j顶点的距离
            }
        }
    }
}

// 创建一个VisitedVertex类来表示 当前节点已被访问过
class VisistedVertex {
    public int[] already_arr;   // 记录各个顶点是否被访问过， 1表示访问过，0表示未访问
    public int[] pre_visited;   // 每个下表所对应的值为前一个顶点的下标，会动态更新
    public int[] dis;           // 记录出发顶点到其他所有顶点的距离，如G为出发顶点，就会记录G到其他顶点的距离，并动态更新，求得最短距离会存入dis

    // Constructor:
    public VisistedVertex(int length, int index) {
        /**
         * @param length --> 顶点个数
         * @param index --> 出发顶点所对应的下标，如G点的下标就是6， A就是0
         */
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        // 初始化 dis 数组，初始值都是65535。但对角线上都是0
        Arrays.fill(dis, 65535);
        this.dis[index] = 0;        // 对角线为0
    }

    public boolean in(int index) {
        /**
         * 判断index顶点是否被访问过
         * @param index
         * @return 如果访问过就返回true，否则false
         */
        return already_arr[index] == 1;
    }

    public void updateDis(int index, int len) {
        /**
         * 更新出发顶点到index顶点的距离
         * @param --> index
         * @param --> len
         */
        dis[index] = len;
    }

    public void updatePre(int pre, int index) {
        /**
         * 更新 pre顶点的前驱节点为index顶点
         * @param --> pre
         * @param index
         */
        pre_visited[pre] = index;
    }

    public int getDis(int index) {
        /**
         * 返回出发顶点到 index顶点的距离
         * @param --> index
         */
        return dis[index];
    }
}

public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵：
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;            // 表示不相连
        matrix[0] = new int[] {N, 5, 7, N, N, N, 2};
        matrix[1] = new int[] {5, N, N, 9, N, N, 3};
        matrix[2] = new int[] {7, N, N, N, 8, N, N};
        matrix[3] = new int[] {N, 9, N, N, N, 4, N};
        matrix[4] = new int[] {N, N, 8, N, N, 5, 4};
        matrix[5] = new int[] {N, N, N, 4, N, N, 6};
        matrix[6] = new int[] {2, 3, N, N, N, 6, N};
        // 创建一个Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
    }
}
