package Sorting;

/*
* Merge Sort
* 作为Divide and Conquer下面的一种经典算法，归并排序分为两个部分，Divide 和 Conquer
* 通常我们需要用递归的方式来实现
* 首先要从 conquer 开始，当一个array被切分到不能再切分时，我们得到 n 个单独的元素。n 是array长度
* 然后，对于任意两个元素A, B，如果A <= B，就将A放到左边；反之放到右边。
* 然后不断重复此过程，直到将所有散碎的元素拼接为一个完整的array为止。
*
* */

public class MergeSort {

    public static void main (String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        mergeSort(arr, 0, arr.length-1);
        for (int j : arr) {
            System.out.print(j + " ");
        }

        System.out.println("\n======= My Solution Below: =======");
        int[] arr1 = arr;
        int[] arr2 = mergeSort(arr1);
        for (int j : arr2) {
            System.out.print(j + " ");
        }
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (right - left) / 2 + left;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle+1, right);
            merge(arr, left, middle, right);
        }
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int lsize = middle - left + 1;  // left half array size
        int rsize = right - middle;     // right half array size

        int[] L = new int[lsize];
        int[] R = new int[rsize];

        for (int i = 0; i < lsize; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < rsize; j++) {
            R[j] = arr[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < lsize && j < rsize) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < lsize) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < rsize) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int middle = arr.length / 2;
        int[] left = mergeSort(getSliceOfArray(arr, 0, middle));
        int[] right = mergeSort(getSliceOfArray(arr, middle, arr.length));
        return merge(left, right);
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int left = 0, right = 0;
        int[] output = new int[arr1.length + arr2.length];
        while (left < arr1.length && right < arr2.length) {
            if (arr1[left] <= arr2[right]) {
                output[left + right] = arr1[left];
                left++;
            } else {
                output[left + right] = arr2[right];
                right++;
            }
        }
        while (left < arr1.length) {
            output[left + right] = arr1[left];
            left++;
        }
        while (right < arr2.length) {
            output[left + right] = arr2[right];
            right++;
        }
        return output;
    }

    private static int[] getSliceOfArray(int[] arr, int start, int end) {
        int[] slice = new int[end - start];
        for (int i = 0; i < slice.length; i++) {
            slice[i] = arr[start + i];
        }
        return slice;
    }
}
