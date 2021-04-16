package Sorting;

import java.util.Arrays;

/*
* Bubble Sort的基本思想是：
*   对一个数组从头到尾开始遍历，一次比较两个相邻值的大小，如果发现逆序则进行交换
* */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};

        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {  // 注意理解这里的 arr.length-1-i
                if (arr[j] > arr[j + 1]) {      // 检测到逆序
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("Bubble Sort Results: \n");
        System.out.println(Arrays.toString(arr));


        // 另外，在实际应用中，我们还可以对上面的代码进行优化
        // 由于冒泡排序会先将array中的大数优先放到array的最后，所以根据这个特点
        // 我们可以创建一个flag变量，当发现当前元素arr[j]不再比arr[j+1]值大的时候，
        // 这表示arr[j]也不可能比arr[j+1]之后的元素值大，这时我们可以break掉当前的for loop
        int arr1[] = {3, 9, -1, 10, 20};

        int temp1;
        boolean flag = false;       // 标识变量
        for (int i = 0; i < arr1.length - 1; i++) {
            for (int j = 0; j < arr1.length - 1 - i; j++) {  // 注意理解这里的 arr.length-1-i
                if (arr1[j] > arr1[j + 1]) {      // 检测到逆序
                    flag = true;
                    temp1 = arr1[j];
                    arr1[j] = arr1[j+1];
                    arr1[j+1] = temp1;
                }
            }
            System.out.println("第"+(i+1) + "次排序后的数组");
            System.out.println(Arrays.toString(arr1));

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
