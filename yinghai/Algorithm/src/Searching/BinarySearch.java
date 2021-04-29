package Searching;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
 * 二分查找是非常高效的一种搜索算法，但是问题在于，该算法只适用于一个有序数组
 * Leetcode上面有许多基于二分查找的 衍生问题，如第k个最近的值等 。我会在之后的代码中尽可能补全和回答这些衍生问题
 *
 * */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 5, 7, 9, 12, 13, 13, 20};
        int target = 13;
        System.out.println("Solution 1: " + binarySearch(arr, target));
        System.out.println("Solution 1 recursive: " + recurBinarySearch(arr, 0, arr.length-1, target));
        System.out.println("Solution 2: " + binarySearchMultipleResults(arr, target));
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {           // 要注意这里的边界条件。
            int middle = (left + right) / 2;
            if (arr[middle] == target)
                return middle;
            else if (arr[middle] < target)
                left = middle + 1;
            else
                right = middle - 1;
        }
        return -1;
    }

    public static int recurBinarySearch(int[] arr, int left, int right, int target) {
        int middle = (left + right) / 2;
        if (arr[middle] == target) return middle;
        else if (arr[middle] < target)
            return recurBinarySearch(arr, middle + 1, right, target);
        else
            return recurBinarySearch(arr, left, middle - 1, target);
    }

    public static ArrayList binarySearchMultipleResults(int[] arr, int target) {
        /**
         * 该function解决的是，当一个array中有多个重复值，而该重复值恰好等于target。我们想要找到所有等于target的值的索引
         * input -> int[] arr, int target
         * output-> ArrayList indice
         */
        /*
        * 思路在于，我们还是用传统的binary search找到一个满足条件的元素的索引值。
        * 然后在这个值的基础上，分别向左右两边查找满足条件的元素
        * */
        int middle = binarySearch(arr, target);
        int left = middle - 1;
        int right = middle + 1;
        List<Integer> indice = new ArrayList<>();
        indice.add(middle);
        while (left >= 0 && arr[left] == target) {
            indice.add(left);
            left--;
        }
        while (right < arr.length && arr[right] == target) {
            indice.add(right);
            right++;
        }
        return (ArrayList) indice;
    }
}
