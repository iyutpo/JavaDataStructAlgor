package Kruskal;

/*
* Kruskal Algorithm: Kruskal 也是用于查找最小生成树的算法。
* PRIM Algorithm是从一个节点开始出发，逐个寻找下一个权重最小的边，直到找到最小生成树为止
* Kruskal Algorithm则是先找到所有权重最小的边，然后找到所有权重次最小的边…… 直到被找到的边能构成一个最小生成树 为止。
*
*
*
* */

public class Kruskal {
    private int edgeNum;        // 边的个数
    private char[] vertex;      // 顶点数组
    private int[][] matrix;     // 邻接矩阵
    // 使用INF表示两个节点之间不连通
    private static final int INF = Integer.MAX_VALUE;

    // Constructor:
    public Kruskal(char[] vertex, int[][] matrix) {
        int vlen = vertex.length;
        // 初始化顶点
        this.vertex = new char[vlen];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        this.matrix = new int[vlen][vlen];      // 初始化边
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        // 统计边
        for(int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    // Print 邻接矩阵
    public void print() {
        System.out.println("邻接矩阵： \n");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%13d", matrix[i][j]);
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };
        // 创建一个Kruskal对象实例
        Kruskal kruskal = new Kruskal(vertex, matrix);
        kruskal.print();
    }
}






















