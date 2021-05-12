package Prim;


/*
* Prim Algorithm是一种非常经典的图算法。
* 受限于绘图功能，在阅读下面代码前，推荐先阅读Prim.md 文件中的算法和案例介绍。
*
* */


import java.util.Arrays;

class Graph{
    // 为了实现Prim Algorithm，我们先创建一个图
    int vertex;         // 节点个数
    char[] data;        // 存放节点的数据
    int[][] weight;     // 存放边，本质上是一个邻接矩阵

    public Graph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}

// 然后创建MST
class MST {
    // 创建图的邻接矩阵
    /**
     * @param graph --> 图
     * @param vertex --> 节点个数
     * @param data --> 图中顶点的值
     * @param weight --> 图的邻接矩阵
     */
    public void createGraph(Graph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {      // 顶点
            graph.data[i] = data[i];
            for(j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    // 打印图
    public void showGraph(Graph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    // PRIM Algorithm:
    public void Prim(Graph graph, int v) {
        /**
         * @param graph --> 图
         * @param v --> 表示从图的第几个顶点开始生成 'A' -> 0, 'B' -> 1, ...
         */
        int[] visited = new int[graph.vertex];
        visited[v] = 1;
        int h1 = -1, h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.vertex; k++) {            // 因为有graph.vertex顶底啊，PRIM算法结束后有graph.vertex - 1 条边
            // 确定每一次生成的子图 和 哪一个节点相距最近
            for (int i = 0; i < graph.vertex; i++) {        // i 表示第i个被访问过的节点
                for (int j = 0; j < graph.vertex; j++) {    // j 表示第j个未被访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];     // 替换minWeight，寻找已访问过节点和未访问节点中权值最小的边
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找打一条边是最小权重：
            System.out.println("边 <" + graph.data[h1] + ", " + graph.data[h2] + "> 权值: " + minWeight);
            // 将当前节点标记为 已访问
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}
public class Prim {
    public static void main(String[] args) {
        char[] data = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = new int[][] {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };              // 用 10000 表示两个节点之间不连通

        // 创建Graph对象
        Graph graph = new Graph(vertex);
        // 创建MST 对象
        MST mstTree = new MST();
        mstTree.createGraph(graph, vertex, data, weight);
        mstTree.showGraph(graph);

        // 测试Prim Algorithm:
        mstTree.Prim(graph, 0);
    }
}
