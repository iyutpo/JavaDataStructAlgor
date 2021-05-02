package Tree;


/*
* 为什么要引入二叉树（binary tree）？
*  - 比较常见的说法是，当我们的单链表长度过长，而我们又想要去索引其中的某一个节点的时候，就需要很长的时间去查找。
*  - 这时候，我们就可以用二叉树来优化查找时间。
*  更具体点来说，二叉树可以提高单链表的 add, search, delete, update 操作
*
*
* 我们将在下面的代码中实现 前、中、后序遍历；前、中、后续查找；删除
*   特别地，对于删除操作，如果：
*       1. 删除的是leaf node --> 则直接删除该节点
*       2. 删除的是non-leaf node --> 则删除该子树
*       测试案例如下（删除 5号节点  和  3号节点）：
*                1
*           /          \
*          2            3
*                   /       \
*                  5         4
* */

class bTree {
    public int value;
    public bTree left;
    public bTree right;

    public bTree(int value) {
        this.value = value;
    }

    public void preorder(bTree root) {
        System.out.println(root.value);
        if (root.left != null) { preorder(root.left); }
        if (root.right != null) { preorder(root.right); }
    }

    public void inorder(bTree root) {
        if (root.left != null) { inorder(root.left); }
        System.out.println(root.value);
        if (root.right != null) { inorder(root.right); }
    }

    public void postorder(bTree root) {
        if (root.left != null) { postorder(root.left); }
        if (root.right != null) { postorder(root.right); }
        System.out.println(root.value);
    }

    // 二叉树的查找
    public bTree preorderSearch(bTree root, int value) {
        if (root.value == value) return root;
        bTree result = null;
        if (root.left != null) { result = preorderSearch(root.left, value); }
        if (result != null) { return result; }
        if (root.right != null) { result = preorderSearch(root.right, value); }
        return result;
    }

    public bTree inorderSearch(bTree root, int value) {
        bTree result = null;
        if (root.left != null) { result = inorderSearch(root.left, value); }
        if (result != null) { return result; }
        if (root.value == value) return root;
        if (root.right != null) { result = inorderSearch(root.right, value); }
        return result;
    }

    public bTree postorderSearch(bTree root, int value) {
        bTree result = null;
        if (root.left != null) { result = postorderSearch(root.left, value); }
        if (result != null) { return result; }
        if (root.right != null) { result = postorderSearch(root.right, value); }
        if (root.value == value) return root;
        return result;
    }

    // 二叉树的删除：
    public void delete(bTree root, int value) {
        /* 我们要判断一个节点是否需要被删除，如果需要的话，要将其父节点的指针指向null，如：
         * 我们想删除下面二叉树的 5 号节点。我们唯一要做的就是让 3 号节点的left指针指向null。所以在遍历做判断的时候，
         * ※ 要写成 if (3.left need to be deleted) then 3.left = null;  ※
         * ※ 当然另一种情况是删除子树的情况，如删除 3号节点。这种情况就很适合使用递归。
         * ※ 当3号节点的左右子节点都返回一个false（左右子节点都不需要删除）时，就像上递归。直到找到某节点x 的 x 左或右子节点需要被删除。
         *                1
         *           /          \
         *          2            3
         *                   /       \
         *                  5         4
         */
        if (root.left != null && root.left.value == value) {
            root.left = null;
            return;
        } else if (root.right != null && root.right.value == value) {
            root.right = null;
            return;
        } // 如果上面都不满足，就分别向左右两个子树进行递归
        if (root.left != null) { root.left.delete(root.left, value); }
        if (root.right != null) { root.right.delete(root.right, value); }
    }
}


public class BinaryTree {
    public static void main(String[] args) {
        bTree root = new bTree(9);
        root.left = new bTree(4);
        root.right = new bTree(14);
        root.left.left = new bTree(8);
        root.left.right = new bTree(25);
        root.right.left = new bTree(6);
        System.out.println("Preorder traversal: ");
        root.preorder(root);
        System.out.println("Inorder traversal: ");
        root.inorder(root);
        System.out.println("Postorder traversal: ");
        root.postorder(root);

        System.out.println("Preorder Search: ");
        bTree r1 = root.preorderSearch(root,4);
        System.out.println(r1.value);
        System.out.println("Inorder Search: ");
        bTree r2 = root.inorderSearch(root,25);
        System.out.println(r2.value);
        System.out.println("Postorder Search: ");
        bTree r3 = root.postorderSearch(root,6);
        System.out.println(r3.value);

        System.out.println("Delete node 3: ");
        bTree r4 = new bTree(1);
        r4.left = new bTree(2);
        r4.right = new bTree(3);
        r4.right.left = new bTree(5);
        r4.right.right = new bTree(4);
        r4.delete(r4, 3);
        r4.preorder(r4);
        System.out.println("Delete node 5: ");
        bTree r5 = new bTree(1);
        r5.left = new bTree(2);
        r5.right = new bTree(3);
        r5.right.left = new bTree(5);
        r5.right.right = new bTree(4);
        r5.delete(r5, 5);
        r5.preorder(r5);
    }
}
