package Kruskal;

/*
* Kruskal Algorithm: Kruskal 也是用于查找最小生成树的算法。
* PRIM Algorithm是从一个节点开始出发，逐个寻找下一个权重最小的边，直到找到最小生成树为止
* Kruskal Algorithm则是先找到所有权重最小的边，然后找到所有权重次最小的边…… 直到被找到的边能构成一个最小生成树 为止。
*
*
*
* */

import java.util.Arrays;

// 定义一个边类，它的对象实例表示一条边
class edgeData {
    char start, end;        // 边的起点和终点
    int weight;             // 边的权重

    // Constructor
    public edgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    // 重写toString 函数：
    @Override
    public String toString() {
        return "edgeData [<" + start + ", " + end + "> = " + weight + "]";
    }
}


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
            for (int j = i+1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0;                  // 表示最后结果数组的索引
        int[] ends = new int[edgeNum];  // 保存”已有最小生成树“中的每个顶点在最小生成树中的终点
        edgeData[] results = new edgeData[edgeNum]; // 保存最后的最小生成树
        edgeData[] edges = getEdges();      // 获取图中所有边的 集合
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length);  // 12条

        // 按照边权重大小进行排序
        sortEdge(edges);
        // 遍历edges数组， 将边添加到最小生成树中，判断准备加入的边是否构成了环
        for (int i = 0; i < edgeNum; i++) {
            // 获取第i条边的第一个顶点：
            int p1 = getPosition(edges[i].start);
            // 获取第i条边的第2个顶点：
            int p2 = getPosition(edges[i].end);

            // 获取p1顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取p2顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);

            // 判断是否构成环：
            if (m != n) {       // 没构成环
                ends[m] = n;    // 设置m 在”已有最小生成树“中的终点
                results[index++] = edges[i];    // 有一条边加入到results
            }
        }
        // 打印出”最终的最小生成树“results
        System.out.println("最终的最小生成树为： ");
        for (int i = 0; i < index; i++)
            System.out.println(results[i]);
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

    // 对边的权重进行排序（冒泡）：
    private void sortEdge(edgeData[] edges) {
        /**
         * @param --> 边的集合
         */
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j+1].weight) {
                    edgeData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    private int getPosition(char ch) {
        /**
         * @param ch --> 顶点的值， 如'A', 'B', ...
         * @return --> 返回ch顶点对应的下标，如果找不到，返回-1
         */
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) { return i; }
        }
        return -1;
    }

    private edgeData[] getEdges() {
        /**
         * @return --> 返回图中的边，放到edgeData[] 数组中，便于我们之后遍历该数组
         * 通过matrix 邻接矩阵来获取
         * edgeData[] 形式： [['A', 'B', 12], ['B', 'F', 7], ... ]
         */
        int index = 0;
        edgeData[] edges = new edgeData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i+1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new edgeData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    private int getEnd(int[] ends, int i) {
        /**
         * 获取下标为 i 的顶点的终点，判断两个顶点的终点是否相同
         * @param --> ends 记录了各个顶点所对应的顶点。ends数组是在遍历过程中逐步形成的
         * @param --> i 表示传入的顶点所对应的下标
         * @return --> 返回下表为 i 的顶点  所对应的终点的下标
         */
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
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

        // 未排序的边：
        edgeData[] edges = kruskal.getEdges();
        System.out.println("未排序的边: " + Arrays.toString(kruskal.getEdges()));
        // 排序后的边：
        kruskal.sortEdge(edges);
        System.out.println("排序的后边: " + Arrays.toString(edges));

        // 打印出最终的最小生成树
        kruskal.kruskal();
    }
}






















