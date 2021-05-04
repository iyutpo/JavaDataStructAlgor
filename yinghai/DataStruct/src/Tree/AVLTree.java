package Tree;

/*
* AVL 树 的出现是为了 防止  BST 的一种极端不平衡的情况，如下图。因此 AVL树 也叫 平衡二叉树
*       1
*        \
*         2
*          \
*           3
*            \
*             4
* AVL 树  的定义： 它是一个 空树 或者它的左右两个子树的高度差的绝对值不超过 1。
* AVL 树 的实现：通常可以用 红黑树、AVL、替罪羊树、Treap、伸展树 等来实现 AVL 树
*
* 下面我们给出一个 BST（不平衡），如何能将其变成 AVL tree呢？
*                   4
*               /       \
*            3             6
*                      /       \
*                    5           7
*                                  \
*                                   8
* 通常要进行6个步骤，我们先分别看 每个步骤之后，树的结构变化，最后我们来总结一下这 6 个步骤：
* 1. 创建一个新的节点 newNode ，该节点的value 为 BST 根节点的值 TreeNode newNode = new TreeNode(4); （此时树没有变化）
* 2. 把 newNode的左子树 设置为 当前节点（当前节点为根节点）的左子树:  newNode.left = curr.left
*                   4                           (newNode)  4            4  (root node/curr)
*               /       \                              (left)\     /          \
*            3             6               ===>                 3              6
*                      /       \                                          /        \
*                    5           7                                      5            7
*                                  \                                                   \
*                                   8                                                   8
* 3. 把当前节点的右子树 设置为当前节点的右子树的左子树： newNode.right = curr.right.left;
*    注意看下图，此时 newNode 的左右指针分别指向了  3号节点 和 5号节点
*    (newNode)  4            4  (root node/curr)                                 4  (root node/curr)
*                 \     /           \                                         /      \
*                    3                6         ===>                       3            6
*                                /        \                        (left)/            /    \
*                              5            7              (newNode) 4  ---right---- 5       7
*                                            \                                                 \
*                                             8                                                 8
* 4. 把当前节点的值 curr.value 替换为右子节点的值： curr.value = curr.right.value;
*                           4  (root node/curr)                          6   (root node/curr) （注意，这里只换了值）
*                       /      \                                      /      \
*                     3            6          ===>                 3            6
*             (left)/            /    \                    (left)/            /    \
*    (newNode) 4  ---right---- 5       7            (newNode) 4 ---right--- 5        7
*                                        \                                            \
*                                         8                                            8
* 5. 把当前节点的右子树设置成右子树的右子树：curr.right = right.right;  (第一个6号节点 指向 7号节点)
*                           4  (root node/curr)          (root node/curr) 6 ---------+ 这个是 root node的右指针
*                       /      \                                      /              |
*                     3            6          ===>                 3            6    |  此时这个 6号节点没有被指针指向，所以会被Java的garbage collection机制回收掉
*             (left)/            /    \                    (left)/            /    \ |
*    (newNode) 4  ---right---- 5       7            (newNode) 4 ---right--- 5        7
*                                        \                                            \
*                                         8                                            8
* 6. 吧当前节点的左子树设置为新节点： curr.left = newNode;
*      (root node/curr)  6  ---------+(right)         (left)+--(root node/curr) 6 -----+(right)
*                     /              |                      |                          |
*                   3                |      ===>            |             3            |
*          (left)/                   |                      |          /               |
*   (newNode) 4 ---right--- 5        7                      |      /                   7
*                                     \              (newNode)  4 ---right--- 5          \
*                                      8                                                   8
* 整理一下最后得到的tree如下：
*                   6
*              /        \
*           4              7
*       /       \             \
*     3           5             8
*
* 下面再来看一下右旋转的过程。右旋转也有 6个步骤我们分别来看下图：
* 1. 首先创建一个新的节点 newNode，并以根节点 10号节点的 10 来初始化 newNode:  treenodeAVL newNode = new treenodeAVL(10);
* 2. 然后将新节点的右子树 设置为当前节点 的 右子树：
*                           10  (root/curr)            (root/curr) 10        10 (newNode)
*                       /       \                               /       \   /
*                     8           12        ===>              8           12
*                   /   \                                   /   \
*                 7       9                                7     9
*               /                                        /
*              6                                        6
* 3. 把 当前节点的左子树 换为 当前节点（root）的左子树的右子树： newNode.left = curr.left.right (9号节点)
*        (root/curr) 10        10 (newNode)         (root/curr) 10     (newNode) 10 ----+
*                /       \   /                             /        \      /(right)     |(left)
*              8           12               ===>         8             12               |
*            /   \                                    /     \                           |
*           7     9                                 7        9 -------------------------+
*          /                                      /
*         6                                     6
* 4. 吧当前节点的值 换为左子节点的值： curr.value = curr.left.value:
*     (root/curr) 10     (newNode) 10 ----+                 (root/curr) 8     (newNode) 10 ----+
*            /        \      /(right)     |(left)                   /      \        /(right)   | (left)
*          8             12               |         ===>          8           12               |
*       /     \                           |                     /   \                          |
*     7        9 -------------------------+                    7     9 ------------------------+
*    /                                                        /
*   6                                                        6
* 5. 把当前节点的 左子树 设置为 左子树的 左子树： curr.left = curr.left.left
*     (root/curr) 8     (newNode) 10 -----+                +--- (root/curr) 8     (newNode) 10 ----+
*            /        \      /(right)     |(left)          |(left)             \        /(right)   | (left)
*          8             12               |         ===>   |            8           12             |
*       /     \                           |                |          /   \                        |
*     7        9 -------------------------+                +-------- 7     9 ----------------------+
*    /                                                              /
*   6                                                              6
* 6. 把当前节点 的右子树 设置为新节点： curr.right = newNode;
*     +--- (root/curr) 8     (newNode) 10 ----+                 +--- (root/curr) 8---->(newNode) 10 ----+
*     |(left)             \        /(right)   | (left)          |(left)         (right)      /(right)   | (left)
*     |            8           12             |         ===>    |            8           12             |
*     |          /   \                        |                 |          /   \                        |
*     +-------- 7     9 ----------------------+                 +-------> 7     9 <---------------------+
*              /                                                         /
*             6                                                         6
* 最后整理一下上面的 树的结构得到：
*                   8
*               /       \
*             7           10
*            /          /    \
*           6          9      12
*
* 思考：只用一次左或右旋转是否能将所有的 不平衡二叉搜索树 都变成 AVL 呢？ —— 不能
* 对于下面这棵树，我们发现需要先进行右旋转。但是右旋转后的树是：
*               10                                          7
*           /       \       (left rotate)               /       \
*         7           11        ====>                 6           10
*       /   \                                                  /      \
*      6     8                                                8        11
*             \                                                 \
*              9                                                 9
* 我们发现旋转后得到的还是一个 不平衡二叉搜索树。该问题是因为：
* 1. 当符合右旋转条件时，如果 root 的左子树的 右子树 高度 > 它的左子树高度，就要先对这个节点的左节点进行左旋转
*    这里  7号节点的 右子树高度为 2 > 7号节点的左子树高度 1，所以要先对  7号节点左旋转。完成后再对 10号节点进行右旋转即可
* 下面给出 两次旋转的结果：
* (1). 对 7号节点进行左旋转：
*               10                                               10
*           /       \       (left rotate at 7)               /       \
*         7           11          ====>                    8          11
*       /   \                                             / \
*      6     8                                           7   9
*             \                                        /
*              9                                      6
* (2). 对 10号节点进行 右旋转：
*               10                                               8
*           /       \       (left rotate at 10)              /       \
*         8           11          ====>                    7          10
*       /   \                                             /         /    \
*      7     9                                          6          9      11
*     /
*    6
*
**/

// 这里的treenodeAVL 类 是基于 BinarySearchTree.java 中的 TreeNode类，添加了几个function
class treenodeAVL {
    int value;
    treenodeAVL left;
    treenodeAVL right;
    public treenodeAVL(int value) {
        this.value = value;
    }

    public void addNode(treenodeAVL node) {
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
        // 当添加完一个节点后， 如果 (右子树高度 - 左子树高度) > 1，左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树 高度 大于它右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 要先进行 右旋转
                right.rightRotate();
                // 然后进行 左旋转
                leftRotate();
            } else {        // 否则直接进行左旋转
                leftRotate();
            }
            return;
        }
        // 当添加完一个节点后，如果 (左子树高度 - 右子树高度） > 1，右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树 高度 大于它左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 要先进行左旋转
                left.leftRotate();
                // 然后进行右旋转
                rightRotate();
            } else {    // 否则直接进行右旋转
                rightRotate();
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
    public treenodeAVL search(int value) {
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
    public treenodeAVL searchParent(int value) {
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

    // 新添加的function如下：
    // 返回当前节点的高度（树高）
    public int height() {
        return Math.max(left == null ? 0 : left.height(),
                right == null ? 0 : right.height()) + 1;
    }

    // 计算左子树的高度：
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }
    // 计算右子树的高度：
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 当左子树的高度 < 右子树的高度时，进行左旋转
    private void leftRotate () {
        treenodeAVL newNode = new treenodeAVL(value);
        // 将新节点左子树 设置成当前节点的左子树
        newNode.left = left;
        // 将新节点的右子树 设置为当前节点的左子树 的 右子树
        newNode.right = right.left;
        // 吧当前节点的值 替换成右子节点的值
        value = right.value;
        // 把当前节点的右子节点 设置成当前节点的 右子节点的 右子节点
        right = right.right;
        // 吧当前节点的左子节点 设置成新的节点
        left =  newNode;
    }

    // 当右子树的高度 < 左子树的高度时，进行右旋转：
    private void rightRotate() {
        treenodeAVL newNode = new treenodeAVL(value);
        // 把新节点的右子树 设置为 当前节点的右子树
        newNode.right = right;
        // 把 当前节点的左子树 换为 当前节点（root）的左子树的右子树
        newNode.left = left.right;
        // 吧当前节点的值 换为左子节点的值
        value = left.value;
        // 把当前节点的 左子树 设置为 左子树的 左子树
        left = left.left;
        // 把当前节点 的右子树 设置为新节点： curr.right = newNode;
        right = newNode;
    }
}

// 除此之外还需要创建一个AVL 的类：（这个类与 BinarySearchTree.java 中的 BST 类一模一样
class AVL {
    private treenodeAVL root;
    public void addNode(treenodeAVL node) {
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
    public treenodeAVL getRoot() {
        return root;
    }

    // 查找要删除的节点：
    public treenodeAVL search(int value) {
        if (root == null) { return null; }
        else { return root.search(value); }
    }

    // 查找父节点：
    public treenodeAVL searchParent(int value) {
        if (root == null) { return null; }
        else { return root.searchParent(value); }
    }

    // 删除节点：
    public void delNode(int value) {
        if (root == null) { return; }
        else {
            treenodeAVL targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 如果 BST只有一个节点：
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 否则要找到targetNode的父节点
            treenodeAVL parent = searchParent(value);
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
                treenodeAVL targetRight = targetNode.right; // targetRight将找到 5或9号节点的父节点 这里我们找 9号节点的父节点
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

public class AVLTree {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AVL avl = new AVL();
        // 添加节点：
        for (int value : arr) {
            avl.addNode(new treenodeAVL(value));
        }
        // 中序遍历打印：
        System.out.println("Inorder traversal AVL: ");
        avl.inorder();
        System.out.println("Height of AVL: " + avl.getRoot().height() +
                ". left subtree height: " + avl.getRoot().leftHeight() +
                ". right subtree height: " + avl.getRoot().rightHeight());

        int[] arr1 = {10, 12, 8, 9, 7, 6};
        AVL avl1 = new AVL();
        // 添加节点：
        for (int value : arr1) {
            avl1.addNode(new treenodeAVL(value));
        }
        // 中序遍历打印：
        System.out.println("Inorder traversal AVL: ");
        avl1.inorder();
        System.out.println("Height of AVL: " + avl1.getRoot().height() +
                ". left subtree height: " + avl1.getRoot().leftHeight() +
                ". right subtree height: " + avl1.getRoot().rightHeight() +
                ". current root node is: " + avl1.getRoot());
    }
}











