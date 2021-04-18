package Sorting;


import java.util.Arrays;

/*
* Shell Sort （希尔排序/缩小增量排序）是为了改进 Insertion Sort 而存在的
*   Insertion Sort中，当我们想插入一个元素的时候，就要从头遍历数组，当找到合适的位置时，
*   将该元素插入。但当我们要插入一个比较大的值时，就需要做很多次比较
*   （如 insert->9 to array->[2,3,4,5,5,5,6]）
* Shell Sort的基本思想：
*   是把数组按照下表的一定增量进行分组，对分组使用直接插入排序算法排序，随着增量逐渐减少，
*   每组包含的关键词越来越多，当增江减少到 1 时，整个文件恰好被分成一组，算法终止。
*
*   给出一个数组：    [8,9,1,7,2, 3,5,4,6,0]  和一个步长：5
*   将数组的元素配对：8,3   9,5   1,4   7,6   2,0（虽然配了对，但顺序没变，还是[8,9,1,7,2, 3,5,4,6,0]
*   然后分别对这5对进行insertion sort，得到：
*       [3,5,1,6,0,8,9,4,7,2]   然后用 5÷2 = 5/2 = 2（取整）
*       将 3,1,0,9,7 配成一组， 5,6,8,4,2 配成一组
*   然后在对这两组进行插入排序，得到：
*       [0,2,1,4,3,5,7,6,9,8]   然后用2÷2 = 1
*   我们此时可以发现，上面这个数组已经很接近排序好的数组了，当对上面数组进行遍历仅一遍的时候，
*   通过比较arr[i] 和 arr[i+1]，就能完成排序了。
* */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int[] arr2 = {8,9,1,7,2,3,5,4,6,0};
        shellSort(arr);
        shellSort2(arr2);
    }

    public static void shellSort(int[] arr) {
        // 最外层,我们逐渐缩小gap
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 因为第一轮排序是将10个数据分成5组, 第二轮是 gap = 5/2=2, ... 直到 gap = 1为止
            for (int i = gap; i < arr.length; i++) {    // 然后从 gap开始遍历数组
                // 遍历各组中所有的元素(共有gap个组,每组有arr.length/gap个元素),步长为gap
                for (int j = i - gap; j >= 0; j-=gap) {
                    // 如果当前元素大于 加上步长后的那个元素,说明要交换
                    if (arr[j] > arr[j+gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第 " + (++count) + "轮之后的数据: " + Arrays.toString(arr));
        }
    }

    // 对交换式希尔排序进行优化 -> 移位法
    public static void shellSort2(int[] arr) {
        int count2 = 0;
        // 设置增量gap,并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j-gap]) {
                        // 移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    // 当退出while之后,就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
            System.out.println("希尔排序第 " + (++count2) + "轮之后的数据: " + Arrays.toString(arr));
        }
    }
}










