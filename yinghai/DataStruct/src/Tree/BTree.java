package Tree;
/*
* B-tree (Balanced-Tree) （reference: https://www.youtube.com/watch?v=e3wqBIDRzHE&list=PLmOn9nNkQxJFvyhDYx0ya4F75uTtUHA_f&index=144)
*   这种树在数据库中非常常用，MySQL就是使用了B-tree或者B+ tree：
*   1. 首先B-tree中引入了一个“阶”的概念。“阶”是节点最多子节点的个数。例如2-3树的“阶”就是 3。 2-3-4树的“阶”就是4。
*   2. B-tree的搜索，是先从根节点开始，对节点内的关键字进行二分查找（关键字是有序序列）。如果找到了结果就结束；否则就进入查询关键字所属范围的子节点。
*       如此重复，直到所对应的子节点为空，或者已经是叶节点为止。
*                                        +-----------------+
*                                        |    [17, 35]     |
*                                        |  [P1, P2, P3]   |
*                                        +-----------------+
*                               /                 |                  \
 *             +-----------------+       +-----------------+          +-----------------+
 *             |     [8, 12]     |       |    [26, 30]     |          |    [65, 87]     |
 *             |  [P1, P2, P3]   |       |  [P1, P2, P3]   |          |  [P1, P2, P3]   |
 *             +-----------------+       +-----------------+          +-----------------+
 *          /        |        \                   |                  /        |        \
 *      [3, 5]   [9, 16]   [13, 15]           [28, 29]        [36, 60]    [75, 79]    [90, 99]
 * 上面 P1, P2, P3表示指向左中右子节点的指针。假设现在我们想要查找 [3] 节点。进入 B-tree根节点，发现 3 < 17， 所以进入 P1指向的左子节点，
 * 发现 3 < 8， 再次进入 P1指向的 左子节点，然后找到了 [3]。（当然如果到了叶节点也没有找到目标值的话，就结束搜索，返回null）
 *
 * 注意，B-tree的每一个节点都存放数据。如根节点[17,35]中存放了 17 和 35两个数据。但是在B+树中，只有叶节点存放数据。
 * */
public class BTree {
}
