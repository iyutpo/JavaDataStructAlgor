package Tree;

/*
* 一个二叉树和一个数组之间是可以相互转换的
* 例如我们想将如下的二叉树 按照前序遍历 生成一个数组：
*           1             储存成                          前序遍历后
*       /       \         ===>     [1, 2, 3, 4, 5, 6, 7]   ===>    [1, 2, 4, 5, 3, 6, 7]
*      2         3                                       中序遍历后
*   /    \    /     \                                      ===>    [4, 2, 5, 1, 6, 3, 7]
*  4      5  6       7                                   后序遍历后
*                                                          ===>    [4, 5, 2, 6, 7, 3, 1]
* BST 中第 n 个元素的左子节点为 2 * n + 1
*       第 n 个元素的右子节点为 2 * n + 2
*       第 n 个元素的父节点为 (n - 1) / 2
*
* */

public class BinaryTreeArray {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7};
        BinaryTreeArrayDemo btArray = new BinaryTreeArrayDemo(arr);
        System.out.println("Pre order: ");
        btArray.preOrder();
        System.out.println("In order: ");
        btArray.inOrder();
        System.out.println("Post order: ");
        btArray.postOrder();
    }
}


class BinaryTreeArrayDemo {
    private int[] arr;

    public BinaryTreeArrayDemo(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void inOrder() {
        this.inOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行前序遍历");
        }
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void inOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行前序遍历");
        }
        if ((index * 2 + 1) < arr.length) {
            inOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            inOrder(2 * index + 2);
        }
    }

    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行前序遍历");
        }
        if ((index * 2 + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}





















