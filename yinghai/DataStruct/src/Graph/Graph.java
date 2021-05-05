package Graph;

/*
* Graph 是一种实际生活和工作中非常常见的一种数据结构。Graph通常有多种存放方式
*   1. 邻接矩阵 --> 是一个 V * V 的 矩阵，V 是graph中 节点的个数，矩阵中的元素代表 两个点之间是否有 edge 相连
*   2. 邻接表  --> 是由 array + linked list 组成，array中存放  V 个graph中的节点，linked list中存放 与每个节点相连的 所有其他节点
*
*          1-------0--------3               0    1 --> 2 --> 3 --> 4 -->
*          |    /  |        |               1    0 --> 4 -->
*          |  /    |        |      邻接表    2    0 --> 4 --> 5 -->
*          |/      |        |     =====>    3    0 --> 5 -->
*          4-------2--------5               4    0 --> 1 --> 2 --> 3 --> 4 -->
*          |                |               5    2 --> 3 --> 4 -->
*          +----------------+
*
* 接下来的代码将实现下图，存储方式为邻接矩阵：
*           C------B-----E                  [0, 1, 1, 0, 0]
*           |    / |                        [1, 0, 1, 1, 1]
*           |  /   |             =====>     [1, 1, 0, 0, 0]
*            A     D                        [0, 1, 0, 0, 0]
*                                           [0, 1, 0, 0, 0]
*
* 此外，我们还将介绍graph的几种遍历方式（DFS 和 BFS）
* 1. DFS： 深度优先遍历。基本思想是：
*    (1). 首先从某一个起始节点开始出发，初始节点可能会有多个邻接节点，DFS的策略是，首先访问第一个邻接节点，然后再以这个被访问的邻接节点作为初始节点
*         访问它的第一个邻接节点。所以在每次访问完当前节点后，首先会访问当前节点的第一个邻接节点。
*    (2). 不难看出，这样的访问策略是在纵向深入，因此该过程是一个递归的过程
*   下面来看一下DFS图遍历的步骤：
*       1.1. 访问初始节点v，并将v节点标记为 已访问
*       1.2. 查找结点v 的第一个邻接节点 w
*       1.3. 若 w 存在，则继续执行 1.4.； 如果 w 不存在，则回到 第1.1.步，将从 v节点的 下一个节点结束
*       1.4. 若 w 未被访问，对 w 进行 DFS 遍历，即把w 当作另一个节点v，然后进行步骤 1.1.， 1.2.， 1.3.
*       1.5. 查找结点v 的 w 邻接节点的下一个邻接节点，转到 1.3.步。
*
* */


import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList;       // 存储所有vertex
    private int[][] edges;                      // 存储 邻接矩阵
    private int numOfEdges;                     // edges 的个数
    // 定义给定数组的 boolean[], 记录某个节点是否被访问
    private boolean[] isVisited;

    public Graph(int n) {                // constructor
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;                 // 因为我们不知道有多少edges，所以初始化为0
        isVisited = new boolean[n];
    }

    // 插入节点：
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    // 添加边：
    public void insertEdge(int v1, int v2, int weight) {
        /**
         * @param v1 --> 节点的下标，也就是第几个顶点
         * @param v2 --> 第二个顶点对应的下标
         * @param weight --> 表示 v1 - v2 之间的edge的权重
         * */
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
    // 返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }
    // 返回graph的边数
    public int getNumOfEdges() {
        return numOfEdges;
    }
    // 返回节点i 对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    // 返回 v1 和 v2的权重
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    // 显示 graph 所对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }

    // 从这里开始实现 Graph 的DFS遍历
    // 首先要得到第一个邻接节点的下标 w
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {          // 如果 下一个邻接节点是存在的
                return j;
            }
        }
        return -1;          // 否则返回-1
    }
    // 根据前一个邻接节点的下标来获取下一个邻接节点：
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    // DFS 算法：
    private void dfs(boolean[] isVisited, int i) {
        /**
         * @param isVisited -> 当前节点是否已经被访问过
         * @param i -> 表示遍历的次数，第一次为0
         */
        System.out.printf(getValueByIndex(i) + "-->");
        // 将该节点设置为 已访问
        isVisited[i] = true;
        // 这就是上面的1.2.步，查找节点i 的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {       // 说明找到了未被访问过的邻接节点
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果 w节点 已被访问过就进入 1.5.步
            w = getNextNeighbor(i, w);
        }
    }
    // 对 dfs 进行一个重载，遍历所有的节点，并进行 DFS
    public void dfs() {
        // 遍历所有的节点进行DFS：
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


    public static void main(String[] args) {
        int n = 5;      // 节点的个数
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        // 创建 Graph
        Graph graph = new Graph(n);
        // 添加节点到 graph：
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        // 添加边：
        graph.insertEdge(0, 1, 1);      // A-B
        graph.insertEdge(0, 2, 1);      // A-C
        graph.insertEdge(1, 2, 1);      // B-C
        graph.insertEdge(1, 3, 1);      // B-D
        graph.insertEdge(1, 4, 1);      // B-E

        graph.showGraph();

        // 测试DFS：
        System.out.println("Start DFS here: ");
        graph.dfs();
    }
}



























