package Tree;


/*
* BST要求所有 非叶节点的左子节点的值 都要大于等于 右子节点
* 接下来我们将看一下如何用 array 创建一个 BST （包括如何添加节点）
* 然后将对这个 BST 进行 in-order traversal
*
*                           7
*                     /            \
*                  3                  10
*               /     \            /      \
*             1         5        9          12
*               \
*                2
*
* 接下来是BST的删除。BST节点的删除比较复杂，会有如下三个情况需要考虑。假设要删除的节点是 targetNode， targetNode的父节点是 parent：
*   1. 删除一个叶节点（如: 2, 5, 9, 12）：
*       假设我们要删除 2号节点，那么我们需要
*           (1). 找到 2号节点；
*           (2). 而且要找到 2号节点的父节点（也就是 1号节点）；
*           (3). 且要保证 1号节点是存在的；
*           (4). 而且还要知道 2号节点是 1号节点的 左子节点还是右子节点。
*       然后根据情况来删除（parent.left = null 或 parent.right = null）
*   2. 删除只有一棵子树的节点（如 1）
*       假设我们要删除 1号节点:
*       (1). 如果 targetNode 有左子节点：
*           <1>. 如果 targetNode 是 parent 的左子节点： parent.left = targetNode.left
*           <2>. 如果 targetNode 是 parent 的右子节点： parent.right = targetNode.left
*       (2). 如果 targetNode 有右子节点：
*           <1>. 如果 targetNode 是 parent 的左子节点： parent.left = targetNode.right
*           <2>. 如果 targetNode 是 parent 的右子节点： parent.right = targetNode.right
*   3. 删除有两棵子树的节点（如：7, 3, 10）
*       假设我们要删除 10号节点：
*       (1). 先找到要删除的节点 targetNode
*       (2). 找到targetNode的 父节点 parent
*       (3). 从targetNode 的右子树找到最小节点
*       (4). 用一个临时变量，将最小节点的值保存， 并删除该最小节点
*       (5). targetNode.value = temp
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

    // 下面是 BST node 的删除。首先要定义一个 search targetNode的函数 来找到需要被删除的节点 targetNode
    public TreeNode search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {    // 当 targetNode.value 小于当前节点值时，就向左子树递归查找
            if (this.left == null) { return null; }
            return this.left.search(value);
        } else {                            // 当 targetNode.value 大于当前节点值时，就向右子树递归查找
            if (this.right == null) { return null; }
            return this.right.search(value);
        }
    }
    // 除了查找 targetNode的函数外，还需要有一个查找 targetNode 的 父节点的函数
    public TreeNode searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {    // 如果当前节点就是要删除的节点的父节点，就返回 当前节点
            return this;
        } else {
            if (value < this.value && this.left != null) {      // 如果targetNode的值（value)小于当前节点的值，并且当前节点的左节点不为空
                return this.left.searchParent(value);
            } else if ( value >= this.value && this.right != null) {    // 如果targetNode的值（value)大于等于当前节点的值，并且当前节点的右节点不为空
                return this.right.searchParent(value);
            } else {        // 否则表示当前节点不存在 父节点 （如 7号节点）
                return null;
            }
        }
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

    // 返回根节点
    public TreeNode getRoot() {
        return root;
    }

    // 查找要删除的节点：
    public TreeNode search(int value) {
        if (root == null) { return null; }
        else { return root.search(value); }
    }

    // 查找父节点：
    public TreeNode searchParent(int value) {
        if (root == null) { return null; }
        else { return root.searchParent(value); }
    }

    // 删除节点：
    public void delNode(int value) {
        if (root == null) { return; }
        else {
            TreeNode targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 如果 BST只有一个节点：
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 否则要找到targetNode的父节点
            TreeNode parent = searchParent(value);
            // 如果要删除的是叶节点：
            if (targetNode.left == null && targetNode.right == null) {
                // 判断 targetNode 是父节点的左子节点 还是右子节点：
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if ((targetNode.left != null && targetNode.right == null)
                    || (targetNode.left == null && targetNode.right != null)) { // 当删除的是只有一棵子树的节点：
                if (targetNode.left != null) {      // 如果 targetNode 有左子节点
                    if (targetNode.value == parent.left.value) { // targetNode 是 parent的左子节点
                        parent.left = targetNode.left;
                    } else if (targetNode.value == parent.right.value) { // targetNode 是 parent的右子节点
                        parent.left = targetNode.right;
                    }
                } else if (targetNode.right != null) {
                    if (targetNode.value == parent.left.value) {
                        parent.left = targetNode.right;
                    } else if (targetNode.value == parent.right.value) {
                        parent.right = targetNode.right;
                    }
                }
            } else if (targetNode.left != null && targetNode.right != null) {   // 被删除的节点有左右两个子节点
                // 此时我们需要 找到 targetNode 的左子树的 最后一个 右子节点；或者 找到 targetNode 的右子树的 最后一个 左子节点
                // 例如，如果targetNode 是 7号节点（root node）。我们需要找到 5号节点 或者 9号节点。用这两个节点其中之一来替换 7号节点
                TreeNode targetRight = targetNode.right; // targetRight将找到 5或9号节点的父节点 这里我们找 9号节点的父节点
                while (targetRight.left != null && targetRight.left.left != null) {
                    targetRight = targetRight.left;
                }
                if (targetRight.left != null) {
                    // 此时找到了 9号节点的父节点。找到后，我们要删除这个 9号节点，同时，将 9号节点的值 赋予 7号节点：
                    int swapValue = targetRight.left.value;     // swapValue = 9
                    targetNode.value = swapValue;
                    targetRight.left = null;        // 删除 9号节点
                } else if (targetRight.left == null) {      // 这种是当我们要删除 10号节点的情况，此时 targetRight.left 为空
                    // 此时我们只需从10号节点的左或右子节点中任选一个替换掉 10号节点即可。这里我们选 10号的  右子节点12
                    targetNode.value = targetRight.value;
                    targetNode.right = null;
                }

            }
        }
    }
}

public class BinarySearchTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BST bst = new BST();
        for (int node : arr) {
            bst.addNode(new TreeNode(node));
        }
        System.out.println("Start printing inorder traversal of BST: ");
        bst.inorder();

        // 删除叶节点：
        bst.delNode(3);
        System.out.println("After deleting a leaf node: ");
        bst.inorder();
    }
}
