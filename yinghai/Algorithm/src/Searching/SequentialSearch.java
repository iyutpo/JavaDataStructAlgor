package Searching;

/*
* 这里将会实现一个线性查找算法。
* 其实非常简单，就是单纯地对一个array进行遍历，返回目标值对应的坐标
* input -> int[] arr, int target
* output-> int index
* 线性查找虽然慢，但是适用性广，即使是无序数组 也可以进行查找。
* */

public class SequentialSearch {
    public static void main(String[] args){
        int[] arr = {7, 1, 3, 2, 9};
        int target = 3;             // ==> expected output: 2
        System.out.println(sequentialSearch(arr, target));
    }

    public static int sequentialSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }
}
