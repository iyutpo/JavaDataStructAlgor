package Sorting;

/*
* heap sort 介绍 (reference: https://www.geeksforgeeks.org/heap-sort/)
* 1. heap sort 是基于 heap 这种数据结构的一种排序算法，本质上是一种 选择排序（selection sort）
*    它的最佳、最坏、平均时间复杂度为 O(n log n)，它也是不稳定排序。
* 2. heap 的本质上是一个 完全二叉树，即每个结点的值都大于等于 其左右子节点的值 （这种叫做 大根堆）。反之则是小根堆
*    注意：左右子节点的值之间并没有大小关系
* 大根堆例子：
*                   50
*             /           \      (level-order)     ==>  arr = [50, 45, 40, 20, 25, 35, 30, 10, 15]
*           45             40
*       /       \       /      \            大根堆特点：arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]
*      20       25     35       30                   当前节点     左子节点       当前节点     右子节点
*     /  \
*   10    15
*
* 小根堆例子：
*                   10
*             /            \     (level-order)     ==>  arr = [10, 20, 15, 25, 50, 30, 40, 35, 45]
*           20              15
*       /       \       /       \           小根堆特点：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]
*      25       50     30        40                  当前节点     左子节点       当前节点     右子节点
*    /   \
*   35    45
*
* 重要：一般来说 升序排序用大根堆， 降序排序用小根堆
*
* 堆排序的基本思想：
*   1. 将待排序的序列构造成一个大根堆 （这个大根堆用array实现）
*   2. 此时，整个序列的最大值就是堆顶的 root node （注意，这里的root node是指有子节点的 节点）
*   3. 将此时的 root node 与 末尾的元素进行交换，此时末尾节点成了最大值节点
*   4. 然后将剩下的 n-1 个元素重新构造成一个 heap，这样会得到 n 个元素的次最小值
*   5. 如此反复，便能得到一个有序序列（升序）
*
* 下面我们用如下的列子来写出 heap sort 的代码：
*               4
*           /       \       ==>     arr = [4, 6, 8, 5, 9] （我们要对该序列进行升序排序）
*         6           8
*      /    \
*     5      9
*   (1). 接着，我们递归地遍历所有的 有 子节点的 root node （例如 6号节点）。发现 9 >= 6，所以将 6 和 9两个节点互换
*               4                                   4
*           /       \           ==>             /       \
*         6           8                       9           8
*      /    \                              /     \
*     5      9                            5       6
*   (2). 然后继续找到第二个有子节点的 root node （4号节点）。发现 9 >= 4，所以将 9 和 4 两个节点互换
*               4                                   9
*           /       \           ==>             /       \
*         9           8                       4           8
*      /    \                              /     \
*     5      6                            5       6
*   (3). 互换后发现左下角的子树 又需要进行互换。因此我们将 4号节点和 6号节点 互换
*               9                                   9
*           /       \           ==>             /       \
*         4           8                       6           8
*      /    \                              /     \
*     5      6                            5       4
*   (4). 当没有节点需要进行交换时，就进入第二大步：
*        第二大步是将真正的 root node 与最末尾的一个节点进行互换，即 9号节点 和 4号节点互换 （这一步称作 sift）
*               9                                   4
*           /       \           ==>             /       \
*         6           8                       6           8
*      /    \                              /     \
*     5      4                            5       9
*   (5). 经过上一步之后，9号节点就完成了排序。我们可以假象 9号节点已经从 heap中排除出去了。
*        此时我们发现，4号节点和 8号节点可以进行交换：
*               4                                   8
*           /       \           ==>             /       \
*         6           8                       6           4
*      /    \                              /     \
*     5      9                            5       9
*   (6). 此时有没有可以进行交换的节点了。所以将 heap最后一个节点 5号节点和 真正的root node 8号节点互换：
*               8                                   5
*           /       \           ==>             /       \
*         6           4                       6           4
*      /    \                              /     \
*     5      9                            8       9
*   (7). 现在又出现了可以进行交换的两个节点，因为 5号节点 <= 6号节点，所以互换：
*               5                                   6
*           /       \           ==>             /       \
*         6           4                       5           4
*      /    \                              /     \
*     8      9                            8       9
*   (8). 此时没有可以进行交换的节点，所以将 6号节点（root node）和 5号节点（未被排序的最后一个节点） 进行互换：
*               6                                   5
*           /       \           ==>             /       \
*         5           4                       6           4
*      /    \                              /     \
*     8      9                            8       9
*   (9). 此时还是没有可以进行交换的节点，所以将 5号节点（root node）和 4号节点互换：
*               5                                   4
*           /       \           ==>             /       \
*         6           4                       6           5
*      /    \                              /     \
*     8      9                            8       9
* 此时我们发现，所有的节点在 level-order的顺序上满足了升序，所以排序结束。
*
* 总结一下：1. 构建 大（小）根堆； 2. 将堆顶元素和 末尾元素进行交换，同时将最大元素 sift 到数组末尾；
*         3. 重新调整 heap的结构，使其满足heap的定义。然后继续进行 交换 和 sift 这两个操作，直到整个序列变得有序
* */

public class HeapSort {
    // 上面的案例不难看出，heapsort中最重要的就是 交换 和 sift这两步。交换是为了保证 heap 的特性，sift是为了将最大值放到最后
    // 下面的代码中， heapify就代表了交换这一步。 heapify（交换）是递归来实现的：
    public static void heapify(int[] arr, int n, int i) {
        /**
         * @param int[] arr -> input array
         * @param int n --> array的长度
         * @param int i --> array中最大元素的索引
         */
        int largest = i;        // 将最大元素索引初始化为 root node
        int l = 2 * i + 1;      // 从而，2 * i + 1 就是 root node的左子节点
        int r = 2 * i + 2;      // 从而，2 * i + 2 就是 root node的右子节点
        if (l < n && arr[l] > arr[largest])     // 当 l < array长度 且 左子节点 > 当前根节点的值 时：
            largest = l;                        // 就让largest索引指向左子节点
        if (r < n && arr[r] > arr[largest])     // 当 r < array长度 且 右子节点 > 当前根节点的值 时：
            largest = r;                        // 就让largest索引指向右子节点
        if (largest != i) {                     // 最后判断，如果上面两个if至少触发了一次时，说明largest != i 了
            int swap = arr[i];                  // 就将 largest 在array中所指向的值 和 i 所指向的值进行交换
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);           // 最后，以largest作为新的root node，依次递归下去
        }
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        // 将array转化为 heap：
        // n/2-1 是最后一个 非叶节点 在 array 中的索引 （e.g. [4, 6, 8, 5, 9], n/2-1 = 1，即6号节点是最后一个非叶节点
        // 6号节点之前的 所有array中的元素都是 非叶节点。所以我们对所有非叶节点都进行一次 heapify
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // 然后我们要从 heap 中逐个提取元素：
        // 注意这里需要倒序遍历，因为我们每次找到一个最大值节点后，都是将它放在array的最后，依次减小之后，需要进行heapify的数组长度也跟着减小
        // 所以for loop 中的 heapify 使用的是 n = i
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 4, 6, 8, 5, 9 };
        int n = arr.length;

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        printArray(arr);
    }
}

// youtube视频上的教程使用的是迭代，讲的很乱
//public class HeapSort {
//
//    public static void main (String[] args) {
//        int[] arr = {4, 6, 8, 5, 9};
//
//    }
//
//    public static void heapSort(int[] arr) {
//        System.out.println("Heap sort here: ");
//    }
//
//    // 第一步是将一个数组变成一个 大根堆：
//    public static void toHeap(int[] arr, int i, int length) {
//        /**
//         * 功能： 完成 将 以 i 所对应的非叶结点的 树 调整为 大根堆
//         * 例子： int[] arr = {4, 6, 8, 5, 9} ==> i = 1 ==> toHeap() ==> {4, 9, 8, 5, 6}
//         * 如果我们再次调用 toHeap() 且 i = 0 ==> 则得到 {4, 9, 8, 5, 6} ==> {9, 6, 8, 5, 4} （变成了大根堆）
//         * @param arr -> 数组
//         * @param i -> 表示非叶节点在数组中的索引
//         * @param lenth -> 表示arr中 还剩下几个元素需要进行调整
//         */
//        int temp = arr[i];      // 先取出当前元素的值，保存在 temp
//        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
//            if (k+1 < length && arr[k] < arr[k+1]) {        // 左子节点的值 小于右子节点
//                k++;            // 就让 k 指向 右子节点
//            }
//            if (arr[k] > temp) {            // 如果子节点的值大于父节点：
//                arr[i] = arr[k];        //  把较大的值给当前节点
//                i = k;                  // i 指向 k， 继续比较
//            } else {
//                break;
//            }
//        }
//
//    }
//}





















