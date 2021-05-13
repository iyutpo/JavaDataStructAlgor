package Floyd;

/*
* Floyd Algorithm： 与Dijkstra's Algorithm一样，也是用于计算给定加权图中顶点之间最短路径的算法。
* 而与Dijkstra's Algorithm不同的是，Dijkstra's Algorithm计算的是某个特定顶点到其他各个顶点之间的最短距离（即单源最短路径）
* Floyd Algorithm则是以图中所有顶点作为出发点，依次计算每个顶点到其余各个顶点之间的最短路径（即多源最短路径）
*
* 受限于文本格式，我们还是在 Floyd.md中详细讲解Floyd Algorithm
* */

import java.util.Arrays;

// 创建一个图类
class Graph {
    private char[] vertex;      // 存放顶点的数组
    private int[][] dis;        // 从各个顶点出发到其他各个顶点的距离。dis 也是Floyd算法的最终结果。相当于Floyd.md中的 M 表（距离表）
    private int[][] pre;        // 前驱节点，相当于Floyd.md中的 L 表（路径表）

    // Constructor
    public Graph(int length, int[][] matrix, char[] vertex) {
        /**
         * @param --> length 节点的个数
         * @param --> matrix 邻接矩阵
         * @param --> vertex 顶点数组
         */
        this.dis = matrix;
        this.vertex = vertex;
        this.pre = new int[length][length];

        // 初始化 pre 路径表
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示dis 距离数组 和 pre 路径数组
    public void show() {
        for (int k = 0; k < this.dis.length; k++) {
            // 输出 pre路径数组
            for (int i = 0; i < this.dis.length; i++) {
                System.out.print(pre[k][i] + " ");
            }
            // 输出 dis距离数组
            for (int i = 0; i < this.dis.length; i++) {
                System.out.print(dis[k][i] + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
}


public class Floyd {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;            // 表示不相连
        matrix[0] = new int[] {N, 5, 7, N, N, N, 2};
        matrix[1] = new int[] {5, N, N, 9, N, N, 3};
        matrix[2] = new int[] {7, N, N, N, 8, N, N};
        matrix[3] = new int[] {N, 9, N, N, N, 4, N};
        matrix[4] = new int[] {N, N, 8, N, N, 5, 4};
        matrix[5] = new int[] {N, N, N, 4, N, N, 6};
        matrix[6] = new int[] {2, 3, N, N, N, 6, N};

        Graph graph = new Graph(7, matrix, vertex);
        graph.show();
    }
}
