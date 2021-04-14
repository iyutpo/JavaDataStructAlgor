package Queue;
/*
* 使用Array 来实现一个Queue。要求要有如下几个功能：
*   1. isFull() --> return boolean; 判断一个Queue是否满
*   2. isEmpty() --> return boolean; 判断Queue是否为空
*   3. addQueue(int value) --> 传入一个int值，将该值放到Queue的最后 (return null)
*   4.
* 实现思路是：
*   1. 要在ArrayQueue类中定义两个private指针变量front, rear分别表示Queue的首尾两个指针，
*      从而每次执行addQueue(int value)时，rear指针向后移动一位，即rear = rear + 1
*   2. 此外，当创建一个ArrayQueue的时候，就要指定该ArrayQueue的长度maxSize。从而当指针
*      rear == maxSize - 1时，表示该ArrayQueue已满，无法再存入数据
*   3. 最后，由于我们要使用Array来模拟这个Queue，所以还需要一个整型数据 int[] arr
*
* 存在的问题：
*   1. 该ArrayQueue一旦被填满，就无法pop内部的数据 --> 可以通过将Array改成一个环形Array的方式来解决
*      上述问题的解决方案请参考 ArrayQueueCircle.java
* */

import java.util.Scanner;

// 使用Array实现Queue。先创建一个ArrayQueue的类
class ArrayQueue {
    private int maxSize;
    private int front, rear;
    private int[] arr;

    // 创建ArrayQueue的Constructor, 在Constructor中定义一些成员变量
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        front = -1;     // 这里front不是0 而是-1，是因为front要指向Array第一个数据的前一个位置。此时的Array为空
        rear = -1;      // 指向队列的最后一个数据
        arr = new int[maxSize];
    }

    // 判断ArrayQueue是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断ArrayQueue是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加一个数据到ArrayQueue：
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("ArrayQueue is full. You cannot add more data!");
            return;
        }
        rear += 1;
        arr[rear] = value;
    }

    // 数据出ArrayQueue:
    public int getQueue() {
        if (isEmpty()) {
            // 通过抛出异常来处理
            throw new RuntimeException("Empty ArrayQueue!");
            // 注意这里不能print。因为执行完上面的throw后，程序就在上一行终止了
        }
        front++;        // front后移一位
        return arr[front];
    }

    // 为了方便检验代码正确性，我们添加一个额外的function来打印所有ArrayQueue中的数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("This ArrayQueue is Empty!");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 另外添加一个function来查看ArrayQueue的第一个数据。该function不会对ArrayQueue进行改动
    public int peakFront() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot peak empty ArrayQueue");
        }
        return arr[front + 1];  // 记住，front永远指向ArrayQueue第一个数据的前一个位置，所以这里要 + 1
    }
}

public class ArrayQueueDemo {
    // 这里我们来测试一下数据
    public static void main(String[] args) {
        // 创建一个ArrayQueue
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';     // 接收用户指令
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 显示队列头的数据");
            key = scanner.next().charAt(0);    // 接受一个用户传入的字符
            switch(key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':  // 取出一个数据
                    try {
                        int result = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':   // 查看队列头的数据
                    try {
                        int result = arrayQueue.peakFront();
                        System.out.printf("数据的头部是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();    // 如果不关闭scanner，会有一场
                    loop = false;
                default:
                    break;
            }
            if (loop == false) {
                System.out.println("程序成功退出！");
            }
        }
    }
}









