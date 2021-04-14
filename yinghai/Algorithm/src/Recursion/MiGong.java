package Recursion;

/*
* 我们将创建一个 8 * 7 的迷宫，然后从一点出发，找到到达另一点的路径（不唯一）。迷宫中可能会有障碍物
* 当找到一条路径后，打印出该路径
* */
public class MiGong {
    public static void main(String[] args) {
        // 初始化这个迷宫。迷宫的四个边都是障碍物，用数值 1 表示
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {  // 上下两个边
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {  // 左右两个边
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置几个障碍物
        map[3][1] = map[3][2] = 1;
        // 打印出该迷宫
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 使用 setWay(int[][], int, int) function 来给小球找路：
        setWay(map, 1, 1);

        // 输出新的迷宫，小球走过、并标记过的递归
        System.out.println("小球走过并标记过的迷宫情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 另一个情况是不存在从起点到终点的路径：
        map[1][2] = map[2][2] = 1;
        System.out.println("小球走过并标记过的迷宫情况（不存在路径）：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 使用递归来寻找路径，默认的终点为(6,5)
    /**
     * @param i, j --> 起始位置的横纵坐标 (1,1)
     * @param map 表示迷宫
     * @return boolean --> 是否存在该路径
     * 当 map[i][j] 为 0 时表示没有走过的节点； 为 1 时表示撞墙；为 2 时表示是通路，可以走；若为 3 表示该节点已经走过，但走不通
     * 由于每次有 4 个方向可以走，所以我们默认的顺序为 下 -> 右 -> 上 -> 左
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // base case:
        if (map[6][5] == 2) { return true; }
        else {
            if (map[i][j] == 0) {                   // 该节点还未走过
                map[i][j] = 2;
                if (setWay(map, i+1, j)) {      // 上 为true
                    return true;
                } else if (setWay(map, i, j+1)) {  // 右 为tru
                    return true;
                } else if (setWay(map, i-1, j)) {  // 下 为true
                    return true;
                } else if (setWay(map, i, j-1)){   // 左 为true
                    return true;
                } else {                        // 最后一种是走不通的情况，即为 3 的情况
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0, i.e., map[i][j] = 1, 2, 3
                return false;
            }
        }
    }
}











