package Stack;

/*
* 我们将使用一个数组来模拟一个stack，并实现如下功能：
* 1. isFull() --> return boolean
* 2. isEmpty() --> return boolean
* 3. push(int) --> return void
* 4. pop() --> return int
* 5. list() --> return void --> print all elements in a stack in bottom-up order
* */


import java.util.Scanner;

class ArrayStack {
    private int maxSize;        // 栈的最大大小
    private int[] stack;        // 使用 int array 模拟一个栈
    private int top = -1;       // top指针指向stack的最下面一个元素的下面一位

    // 定义constructor，初始化 stack变量
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 判断stack是否已满
    public boolean isFull() {
        return this.maxSize - 1 == top;
    }

    // 判断stack是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // stack 的 push 方法：
    public void push(int value) {
        if (isFull()) {
            System.out.println("This stack is full.");
        }
        top++;
        this.stack[top] = value;
    }

    // stack 的 pop 方法：
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Empty stack has no data.");
        } else {
            int value = stack[top];
            top--;
            return value;
        }
    }

    // 遍历一个stack，并打印出所有数据。数据的顺序是从栈底到顶
    public void list() {
        if (isEmpty()) {
            System.out.println("Empty stack has no data.");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}

public class StackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch(key.hashCode()) {
                case 111185:
                    if (key.equals("pop")) {
                        try {
                            int res = stack.pop();
                            System.out.printf("出栈的数据是 %d\n", res);
                        } catch (Exception var8) {
                            System.out.println(var8.getMessage());
                        }
                    }
                    break;
                case 3127582:
                    if (key.equals("exit")) {
                        scanner.close();
                        loop = false;
                    }
                    break;
                case 3452698:
                    if (key.equals("push")) {
                        System.out.println("请输入一个数");
                        int value = scanner.nextInt();
                        stack.push(value);
                    }
                    break;
                case 3529469:
                    if (key.equals("show")) {
                        stack.list();
                    }
            }
        }

        System.out.println("程序退出~~~");
    }
}
