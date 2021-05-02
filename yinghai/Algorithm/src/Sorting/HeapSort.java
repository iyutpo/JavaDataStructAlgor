package Sorting;

/*
* heap sort 介绍
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
*             /            \      (level-order)     ==>  arr = [10, 20, 15, 25, 50, 30, 40, 35, 45]
*           20              15
*       /       \       /       \           小根堆特点：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]
*      25       50     30        40                  当前节点     左子节点       当前节点     右子节点
*    /   \
*   35    45
*
* 重要：一般来说 升序排序用大根堆， 降序排序用小根堆
*
*
* */

class HeapSortDemo {

}

public class HeapSort {

    public static void main (String[] args) {

    }
}





















