package Hash;

/*
* hash table是非常常见的数据结构之一，是典型的用空间换取时间的一种数据结构。
* 这种类型的数据结构经常被用于搭建缓存（cache）。目前虽然有很多主流的缓存产品（如redis，memcache等）
* 但是，在这些产品出现之前，通常需要程序员自己搭建一个自己的缓存。比较常见的用于实现一个Hash Table的算法和数据结构是：
*   1. Hash Table = Array + LinkedList + Binary Tree
* Array<LinkedList>()   // hash table 可以用一个Array来实现，Array中是若干个LinkedList
* Array   LinkedList
* key  -> values
* [0]  -> [30 null] -> [301 null] -> ...
* [1]  -> [1 null] -> [233 null] -> ...
* [2]  -> [23 null] -> [180 null] -> ...
* [3]  -> [68 null] -> [584 null] -> ...
* [4]  -> [115 null] -> [613 null] -> ...
* [5]  -> [38 null] -> [397 null] -> ...
* [6]  -> [99 null] -> [421 null] -> ...
*
* 重点！
* Hash Table是如何实现的呢？
* 我们都知道Hash Table中有两个部分组成: key + value
* key是unique的，不能有重复，这一特性保证了key是可以被用作索引的。那么如何确保这么多key是不重复的呢？
*   A: 用一个映射函数（常叫做 散列函数）来实现。 举个例子：
* 假设我们有一个 hash table，里面有 7 个key。我们传入一个数 200，那么 200 mod 7 = 4 （取余）。所以200应该在第4个key上
*
*
* 我们接下来用一个实例来演示一下hash table的建立
* */

import java.util.Scanner;

class Employee {
    public int id;
    public String name;
    public Employee next;
    public Employee (int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

// 创建一个EmployeeLinkedList链表
class EmployeeLinkedList {
    // head指针，该链表的head直接指向第一个Employee emp
    private Employee head;  // 默认为null

    // 添加雇员到链表
    // 1. 假定当添加雇员时，ID是自动增加，确保ID是升序的
    // 因此我们将该雇员直接加入到本链表的最后即可
    public void add(Employee emp) {
        // 如果是添加一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员，则使用一个辅助指针，定位到链表最后
        Employee currEmp = head;
        while (true) {
            if (currEmp.next == null) {
                break;
            }
            currEmp = currEmp.next;
        }
        currEmp.next = emp;
    }

    // 遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no+1) + "个链表为空");
            return;
        }
        System.out.println("第" + (no+1) + "个链表的信息为：");
        Employee currEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", currEmp.id, currEmp.name);
            if (currEmp.next == null) {
                break;
            }
        }
    }
}

class hashTableExample {
    private EmployeeLinkedList[] employeeLinkedListArray;
    private int size;       // size 表示共有多少条链表

    // 构造器  相当于 __init__, hashTableExample 吃一个参数 size，表示hash table的大小
    public hashTableExample(int size) {
        // 初始化employeeLinkedListArray
        employeeLinkedListArray = new EmployeeLinkedList[size];
        this.size = size;
        // 不要忘记分别初始化每个列表
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
    }

    // 添加雇员
    public void add(Employee employee) {
        // 根据雇员id判断该员工应该被加在那个链表
        int employeeLinkedListNO = hashFunction(employee.id);
        // 将 employee 添加到对应的链表中
        employeeLinkedListArray[employeeLinkedListNO].add(employee);
    }

    // 遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i].list(i);
        }
    }

    // 编写一个散列函数（常见的是取模法）
    public int hashFunction(int id) {
        return id % size;
    }
}

public class HashTable {
    public static void main(String[] args) {
        // 创建hash table
        hashTableExample hashTab = new hashTableExample(7);
        // 写一个简单的菜单：
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 列出雇员");
            System.out.println("exit: 退出程序");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入ID");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名");
                    String name = scanner.next();
                    // 创建雇员
                    Employee employee = new Employee(id, name);
                    hashTab.add(employee);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}















