package Sorting;


/*
* Quick Sort 是对冒泡排序的改进, 基本思想是:
*   通过一次排序将 要排序的数据分割成独立的两部分,其中一部分的所有数据都比另一部分
*   的所有数据要小, 然后再按此方法对这两部分数据分别进行 QuickSort,整个排序过程
*   要递归进行,以此达到整个数据变成有序序列
* */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSort(arr, left, pi - 1);
            quickSort(arr, pi + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int tmp = arr[i+1];
        arr[i+1] = arr[right];
        arr[right] = tmp;
        return i + 1;
    }

    public static void quickSort1(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int temp;
        int pivot = arr[(left + right) / 2];    // pivot相当于数组arr最中间的元素
        while (l < r) {
            while (arr[l] < pivot) {    // 在pivot左半边一直找，直到找到一个小于arr[pivot]的值的元素为止
                l++;
            }
            while (arr[r] > pivot) {    // 在pivot右半边一直找，直到找到一个大于arr[pivot]的值的元素为止
                r--;
            }
            // 如果 l >= r 说明 pivot  的左右两边的值,已经按照左边全部是 <= pivot, 右边
            // 全部是 > pivot 的.
            if (l >= r) {  // 当 l索引大于等于 r 索引，说明整个arr的左半边都是小于arr[pivot]的元素，右半边都是大于arr[pivot]的元素
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换完成后，如果arr[l] == arr[pivot] 的值，就要前移一位
        }
    }
}












