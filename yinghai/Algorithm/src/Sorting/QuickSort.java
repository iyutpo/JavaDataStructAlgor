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
}












