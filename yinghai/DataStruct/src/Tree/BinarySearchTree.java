package Tree;


/*
* BST要求所有 非叶节点的左子节点的值 都要大于等于 右子节点
* 接下来我们将看一下如何用 array 创建一个 BST （包括如何添加节点）
* 然后将对这个 BST 进行 in-order traversal
* */


import com.sun.source.tree.Tree;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    public TreeNode(int value) {
        this.value = value;
    }

    public void addNode(TreeNode node) {
        if (node == null) { return; }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.addNode(node);
            }
        } else  {
            if(this.right == null) {
                this.right = node;
            } else {
                this.right.addNode(node);
            }
        }
    }

    public void inorder() {
        if (this.left != null) {
            this.left.inorder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.inorder();
        }
    }

    // 打印节点
    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }
}

class BST {
    private TreeNode root;

    public void addNode(TreeNode node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    public void inorder() {
        if (root != null) {
            root.inorder();
        } else {
            System.out.println("Empty BST! Please add node first!");
        }
    }
}

public class BinarySearchTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BST bst = new BST();
        for (int node : arr) {
            bst.addNode(new TreeNode(node));
        }
        System.out.println("Start printing inorder traversal of BST: ");
        bst.inorder();
    }
}
