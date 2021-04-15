package Recursion;

/*
* 8-Queen problem
* 思路：
*   1. 先将第一个皇后放到第一行第一列
*   2. 第二个皇后放在第二行第一列，然后判断时候OK，如果不OK，就继续放在第二列、
*      第三列......依次把所有列都放完，直到找到一个合适的位置
*   3. 继续放第三个皇后，还是第一列、第二列......知道第8个皇后也能放在一个不冲突
*      的位置，就算是找到了一个正确的解
*   4. 当得到一个正确解时，在stack回退到上一个stack时，就会开始回溯，即，将第一个
*      皇后放到第一列的所有正确解，全部得到
*   5. 然后回头继续第一个皇后放到第二列，后面继续循环执行1，2，3的步骤
*   说明：理论上应该先创建一个二维数组来表示棋盘，但是实际上可以通过算法用一个一维数
*        组来解决该问题
*
* 输出：
*   一个数组 arr[8] = [0, 4, 7, 5, 2, 6, 1, 3]，表示分别在第0, 1, ..., 7 行的第
*   0, 4, 7, ..., 3 列放置皇后
* */
public class Queen8 {
    // 定义一个max变量来表示有多少个皇后
    int max = 8;
    // 定义一个数组来存放结果
    int[] arr = new int[max];
    static int count = 0;          // 统计一共有几种解法

    public static void main(String[] args) {
        // 测试
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法", count);
    }

    // 查看当我们放置第 n 个皇后，就去检测该皇后是否与之前已经放置的皇后 有冲突
    private boolean judge(int n) {
        /**
         * @param n --> 第 n 个皇后
         * @return boolean
         */
        for (int i = 0; i < n; i++) {
            // 分别判断了 同一列(arr[i] == arr[n])  和 同一斜线(Math.abs)
            // 斜线的判断举个例子来说明： n=1时（放在第1列），arr[1] = 1
            // 此时，Math.abs(1-0)=1,  Math.abs(arr[n] - arr[i]) = Math.abs(1-0)=1
            // 由于我们是遍历了行，所以不需要检查是否存在 行冲突
            if (arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    // 放置第 n 个皇后
    // 注意：check是每一次递归时，进入到check中都有一个for loop，因此会有回溯
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {     // 如果不冲突
                check(n+1);     // 接着递归放置第n+1个皇后
            }
            // 如果冲突，就continue，也就是继续执行arr[n] = i，即 将第n个皇后放置在本行的 后一个位置
        }
    }

    // 将皇后的摆放位置输出出来
    private void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}















