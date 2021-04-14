package Queue;

import java.util.Scanner;

/*
* 我们现在要对ArrayQueueDemo.java中的ArrayQueue进行改进。
* ArrayQueue的问题在于，当Array被添加满 值 之后，无法重复使用
* 该问题的解决方案是：
*   1. 将linear Array变成一个Circle Array
*   2. 具体做法是，通过对 front, rear 两个指针取 mod 来模拟 Circle的效果
*   3. 但要注意的是，在CircleArrayQueue中，我们要将front, rear的初值设置为 0, 0
*   4. 从而front默认指向的是Array的第一个元素；rear默认指向Array的最后一个元素的后一位
*   5. 这也意味着我们创建的CircleArray的长度是 maxSize + 1 的
*   6. 当 (rear + 1) % maxSize == front --> CircleArray 满（！好好思考一下这里）
*   7. 当 (rear == front) --> CircleArray 为空
*   8. 该CircleArray中有效数据的个数 --> (rear + maxSize - front) % maxSize （好好思考一下这里）
* 另外同理，我们需要实现如下几个function：
*   1. isFull() --> return boolean; 判断一个Queue是否满
*   2. isEmpty() --> return boolean; 判断Queue是否为空
*   3. addQueue(int value) --> 传入一个int值，将该值放到Queue的最后 (return null)
*
* */
class Queue {
    private int maxSize;
    private int front, rear;
    private int[] arr;

    // 创建Queue的Constructor, 在Constructor中定义一些成员变量
    public Queue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
//        front = 0;     // 这里front不是0 而是-1，是因为front要指向Array第一个数据的前一个位置。此时的Array为空
//        rear = 0;      // 指向队列的最后一个数据
        this.arr = new int[this.maxSize];
    }

    // 判断Queue是否满
    public boolean isFull() { return (this.rear + 1) % this.maxSize == this.front; }

    // 判断Queue是否为空
    public boolean isEmpty() { return this.rear == this.front; }

    // 添加一个数据到Queue：
    public void addQueue(int value) {
        if (this.isFull()) {
            System.out.println("Queue is full. You cannot add more data!");
            return;
        } else {
            this.arr[this.rear] = value;
            this.rear = (this.rear + 1) % this.maxSize;
        }
    }

    // 数据出Queue:
    public int getQueue() {
        if (this.isEmpty()) {
            // 通过抛出异常来处理
            throw new RuntimeException("Empty Queue!");
            // 注意这里不能print。因为执行完上面的throw后，程序就在上一行终止了
        } else {
            // 记住，front永远是指向Queue的第一个元素。
            // 1. 我们要将 front 的值保存到临时变量中
            // 2. 将 front 后移一位
            // 3. 将临时保存的变量返回
            int value = this.arr[this.front];
            this.front = (this.front + 1) % this.maxSize;
            return value;
        }
    }

    // 为了方便检验代码正确性，我们添加一个额外的function来打印所有Queue中的数据
    public void showQueue() {
        if (this.isEmpty()) { System.out.println("This Queue is Empty!"); }
        else {
            // 这里要注意从 front 开始遍历，遍历若干个元素（也就是 i 从 front 到 front + ? ）
            // 这里的 ? 就是被遍历的若干个元素。
            // 而我们最上面提到过，CircleArray中有效数据的个数是 (rear + maxSize - front) % maxSize
            // 所以 ? = (rear + maxSize - front) % maxSize = size()
            // 为了方便起见，我们将这个式子写到一个function size()中
            for(int i = this.front; i < this.front + this.size(); ++i) {
                System.out.printf("arr[%d]=%d\n", i % this.maxSize, this.arr[i % this.maxSize]);
            }
        }
    }

    // 求当前CircleArray的有效值的个数
    public int size() { return (this.rear + this.maxSize - this.front) % this.maxSize; }

    // 另外添加一个function来查看Queue的第一个数据。该function不会对Queue进行改动
    public int peakFront() {
        if (this.isEmpty()) { throw new RuntimeException("Cannot peak empty Queue"); }
        else {
            return this.arr[this.front];  // 记住，front永远指向Queue第一个数据的位置
        }
    }
}

public class ArrayQueueCircle {
    // 这里我们来测试一下数据
    public static void main(String[] args) {
        // 创建一个Queue
        Queue queue = new Queue(4);  // 如前面所说，虽然arrMaxSize=4，但实际有效数据个数为3
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
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':  // 取出一个数据
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':   // 查看队列头的数据
                    try {
                        int result = queue.peakFront();
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
            if (!loop) {
                System.out.println("程序成功退出！");
            }
        }
    }
}
