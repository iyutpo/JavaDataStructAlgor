package LinkedList;

/*
 * 我们这里要实现一个单链表
 * 该单链表中需要有如下功能
 *   1. 添加节点到链表 --> add(HeroNode) --> return void （只需要插入到链表最后即可）
 *   2. 添加节点到链表 --> addByOrder(HeroNode) --> return void （将节点添加到指定位置）
 *   3. 打印链表的所有节点值 --> list() --> return void
 *   4. 删除链表中的一个节点 --> del(int number) --> return void (temp.next = temp.next.next)
 *   5. 反转一个链表 --> reverse(HeroNode head) --> return void (可以尝试用迭代和递归两种方法来实现)
 * */

class SinglyLinkedList {
    // 先初始化一个头节点. 一般头节点不存放具体的数据
    // 我个人理解这里的 head 相当于一个 prev 节点，该节点没有任何值，且位于所有有值节点的前边，图解：
    // head -> 1 -> 2 -> 6 -> null
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单列表
    public void add(HeroNode heroNode) {
        // 由于成员变量 head 不能动，所以我们创建一个 temp 变量来帮助我们遍历 链表
        HeroNode temp = head;
        while (temp != null) {
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }
    }

    // 第二种插入结点的方式，根据排名将节点插入到指定位置
    // 如果已有这个排名，则打印出提示
    public void addByOrder(HeroNode heroNode) {
        // 同样，head 节点不能动，所以我们创建一个 temp 变量
        HeroNode temp = head;
        // 插入的位置（i.e. 排名）由 heroNode的 int number成员变量给出
        boolean flag = false;  // 用来标记被添加的编号是否已经存在，默认为false
        while (true) {
            if (temp.next == null) { break; }
            if (temp.next.number > heroNode.number) {  // 此时发现 下一个节点序号 > 被插入节点序号 > 前一个节点序号
                break;
            } else if (temp.next.number == heroNode.number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断 flag 的值
        if (flag) {
            System.out.println("This hero is existed already! %d " + heroNode.number);
        } else {
            HeroNode nextnode = temp.next;
            temp.next = heroNode;
            heroNode.next = nextnode;
        }

    }

    // 修改节点信息，根据number编号来修改
    // 根据 newHeroNode 的 number 成员变量来修改即可
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("This linked list is empty!");
        } else {
            // 找到需要修改的节点，根据number。 首先需要定义一个 temp
            HeroNode temp = head.next;
            boolean flag = false;
            while (temp.next != null) {
                if (temp.number == newHeroNode.number) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            // 根据flag，如果找到需要修改的节点（flag==true）则
            if (flag) {
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
            } else {
                System.out.println("Failed to find %d Hero, not able to change." + newHeroNode.number);
            }
        }
    }

    // 打印链表所有的节点值
    public void list() {
        // 判断是否为空链表
        if (this.head.next == null) {
            System.out.println("This is an empty linked list.");
        } else {
            HeroNode temp = head.next;
            while (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    // 删除链表中的一个节点：
    public void del(int number) {
        HeroNode temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.number == number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) { // 如果发现了需要被删除的节点：
            temp.next = temp.next.next;
        } else {
            System.out.println("Didn't find the node %d to be deleted." + number);
        }
    }

    // 反转列表（迭代）
    public void reverseIter() {
        // 如果当前的链表为空， 就不需要反转
        if (this.head == null) { return; }
        // 否则我们定义一个 辅助变量 curr 来帮助我们你遍历原来的链表
        HeroNode curr = this.head.next;   // head.next 是原来链表的第一个有效值节点
        HeroNode next = null;        // 指向当前节点 curr 的下一个节点
        HeroNode reversedHead = new HeroNode(0, "", "");
        while (curr != null ) {         // 开始遍历原来的链表
            next = curr.next;
            curr.next = reversedHead.next;
            reversedHead.next = curr;
            curr = next;
        }
        this.head.next = reversedHead.next;
    }
}

// 定义一个 HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int number;
    public String name;
    public String nickname;
    public HeroNode next;  // 指向下一个节点

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

// 定义一个Singly Linked List 来管理所有的英雄
public class SinglyLinkedListDemo {
    public static void main (String[] args) {
        // 我们在main function中进行测试：
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个单链表
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        // 向链表中添加节点：
        singlyLinkedList.addByOrder(hero1);
        singlyLinkedList.addByOrder(hero4);
        singlyLinkedList.addByOrder(hero3);
        singlyLinkedList.addByOrder(hero2);
        // 显示所有的节点值：
        singlyLinkedList.list();

        // 测试 update function:
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟——");
        singlyLinkedList.update(newHeroNode);
        System.out.println("Linked List after update: \n");
        singlyLinkedList.list();

        // 测试 del(int number) function:
        singlyLinkedList.del(1);
        System.out.println("删除一个节点之后：\n");
        singlyLinkedList.list();

        // 测试 reverseIter(HeroNode head) function：
        // 因为上面删掉了 hero1，所以我们在这里加回来：
        singlyLinkedList.addByOrder(hero1);
        System.out.println("未反转之前的链表：\n");
        singlyLinkedList.list();
        System.out.println("反转之后的链表: \n");
        singlyLinkedList.reverseIter();
        singlyLinkedList.list();



    }
}







