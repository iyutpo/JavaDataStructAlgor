package DoublyLinkedList;

import java.awt.datatransfer.SystemFlavorMap;

/*
 * 我们这里要实现一个单链表
 * 该单链表中需要有如下功能
 *   1. 打印链表的所有节点值 --> list() --> return void
 *   2. 添加节点到链表 --> add(HeroNode) --> return void （只需要插入到链表最后即可）
 *   3. 更新一个节点的内容 --> update(HeroNode) --> return void
 *   4. 删除链表中的一个节点 --> del(int number) --> return void (temp.next = temp.next.next)
 *   5. 添加节点到链表 --> addByOrder(HeroNode) --> return void （将节点添加到指定位置）
 * */
class DoublyLinkedList {
    // 先初始化一个头节点 head， 头节点不动， 也不存放任何值
    private HeroNode head = new HeroNode(0, "", "");

    // 定义一个 getter function来获取 头节点 head
    public HeroNode getHead() {
        return head;
    }

    // 遍历一个双向链表并打印出来
    public void list() {
        if (head.next == null) {
            System.out.println("This doubly linked list is empty.");
        }
        // 因为head头节点不能动，所以创建一个temp变量来辅助遍历
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 向 doubly linked list 添加一个节点到最后
    public void add(HeroNode heroNode) {
        // 同样，head节点必须保持不懂，所以我们创建一个temp变量
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 遍历到最后一个节点时，添加新的节点
        temp.next = heroNode;       // next指针指向新节点
        heroNode.prev = temp;       // prev指针指向temp节点（原链表的最后一个节点）
    }

    // 更新一个节点的内容
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("This is an empty doubly linked list.");
        }
        // 首先要找到需要更新的节点
        // 定义一个辅助变量 temp
        HeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (head.next == null) {
                System.out.println("This linked list is empty!");
            } else {
                while (temp.next != null) {
                    if (temp.number == heroNode.number) {
                        flag = true;
                        break;
                    }
                    temp = temp.next;
                }
                // 根据flag，如果找到需要修改的节点（flag==true）则
                if (flag) {
                    temp.name = heroNode.name;
                    temp.nickname = heroNode.nickname;
                } else {
                    System.out.println("Failed to find %d Hero, not able to change." + heroNode.number);
                }
            }
        }
    }

    // 从双向链表中删除一个节点
    public void del(int number) {
        // 首先判断是否为空链表
        if (head.next == null) {
            System.out.println("This doubly linked list is empty.");
        }
        HeroNode temp = head.next;    // 让 temp 为 将被删除的节点， 默认为head.next
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.number == number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) { // 如果发现了需要被删除的节点：
            temp.prev.next = temp.next;
            // 为了防止 被删除的节点是原双链表的最后一个节点 的情况，我们需要加一个if判断
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
        } else {
            System.out.println("Didn't find the node %d to be deleted." + number);
        }
    }

    // 根据编号添加节点到链表

}

// 定义一个 HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int number;
    public String name;
    public String nickname;
    public HeroNode next;  // 指向下一个节点，默认为null
    public HeroNode prev;  // 指向前一个节点，默认为null

    // HeroNode 的 constructor
    public HeroNode(int hNumber, String hName, String hNickname) {
        this.number = hNumber;
        this.name = hName;
        this.nickname = hNickname;
    }

    // 为了显示方便，我们可以override toString() 方法：
    @Override
    public String toString() {
        return "HeroNode [number=" + number + ", name=" + name + ", nickname=" + nickname + "]";
    }
}

public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        // 我们在main function中进行测试：
        System.out.println("Testing starts!");
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个双向链表
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.add(hero1);
        doublyLinkedList.add(hero2);
        doublyLinkedList.add(hero3);
        doublyLinkedList.add(hero4);
        doublyLinkedList.list();

        // 更新双向链表节点的信息
        HeroNode newHeroNode = new HeroNode(4, "公孙胜", "入云龙");
        doublyLinkedList.update(newHeroNode);
        System.out.println("Show doubly linked list after update: \n");
        doublyLinkedList.list();

        // 删除一个节点：
        doublyLinkedList.del(3);
        System.out.println("Show doubly linked list after delete: \n");
        doublyLinkedList.list();
    }
}








