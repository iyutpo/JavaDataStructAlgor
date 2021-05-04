package Tree;

/*
* B+ tree 是 B-tree 的一种变形，它的本质也是一种多路搜索树
*                                   +---------------+
*                                   |  [5, 20, 65]  |
*                                   |  P1, P2, P3   |
*                                   +---------------+
*                   /                       |                       \
*     +---------------+             +---------------+               +---------------+
*     |  [5, 20, 65]  |             |  [5, 20, 65]  |               |  [5, 20, 65]  |
*     |  P1, P2, P3   |             |  P1, P2, P3   |               |  P1, P2, P3   |
*     +---------------+             +---------------+               +---------------+
*    /       |        \             /       |       \               /       |        \
*  +---+   +---+    +----+       +---+    +---+    +---+         +---+    +---+     +---+
*  | 5 |   | 10|    | 20 |       | 28|    | 35|    | 56|         | 65|    | 80|     | 90|
*  | 8 |   | 15|    | 26 |       | 30|    | 38|    | 60|         | 73|    | 85|     | 96|
*  | 9 |   | 18|    | 27 |       | 33|    | 55|    | 63|         | 79|    | 88|     | 99|
*  | Q |-->| Q |--->| Q  |------>| Q |--->| Q |--->| Q |-------->| Q |--->| Q |---->| Q |
*  +---+   +---+    +----+       +---+    +---+    +---+         +---+    +---+     +---+
*
* 说明：
*   1. B+tree的搜索与 B-tree基本相同，区别在于 B+tree只有达到叶节点时，才能返回值。因此B+tree的搜索性能实际上相当于二分查找
*   2. 所有关键字只出现在B+tree的叶节点之中。B+tree的每个叶节点都是一个 linked list，且linked list中每个节点的值都是有序的。
*   3. 不可能在非叶节点处 完成查找。
*   4. 非叶节点相当于叶结点的索引（稀疏索引），叶节点相当于存储数据的数据层。
*   5. B+tree 更适合于文件索引系统
*   6. B-tree 和B+tree有各自的应用场景，二者之间没有必然的优劣之分。
*
* 接下来可以看一下 B*tree.java
* */
public class BPlusTree {
}