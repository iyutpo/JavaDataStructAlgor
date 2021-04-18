package Sorting;


/*
* Quick Sort 是对冒泡排序的改进, 基本思想是:
*   通过一次排序将 要排序的数据分割成独立的两部分,其中一部分的所有数据都比另一部分
*   的所有数据要小, 然后再按此方法对这两部分数据分别进行 QuickSort,整个排序过程
*   要递归进行,以此达到整个数据变成有序序列
* */
public class QuickSort {
    public static void main(String[] args) {

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            // 如果 l >= r 说明 pivot  的左右两边的值,已经按照左边全部是 <= pivot, 右边
            // 全部是 > pivot 的.
            if (l )
        }
    }
}












