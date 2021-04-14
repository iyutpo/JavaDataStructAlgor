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
        // 打印出该迷宫
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.println(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
