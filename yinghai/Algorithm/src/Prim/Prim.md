### Prim Algorithm

#### 问题场景

有7个村庄A, B, ..., G，现在我们想要将7个村庄相连。图中各个村庄之间的距离用权重表示，如A-B之间为5km。问：如何修路才能保证各个村庄之间都能连通，且公路总里程数最短。

该问题是非常典型的求[最小生成树](https://zh.wikipedia.org/wiki/%E6%9C%80%E5%B0%8F%E7%94%9F%E6%88%90%E6%A0%91)（Minimum Spanning Tree, MST）的问题。**最小生成树**是指在一个连通加权无向图中，一棵权重之和最小的树。前边的链接提供了非常详细的关于MST的介绍，这里不再专门讲述MST。我们着重看一下Prim Algorithm。

![graph](D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\Prim\graph.jpg)

#### 1. Prim Algorithm思路

Prim Algorithm求最小生成树，也就是在包含 n 个顶点的连通图中，找出只有 (n - 1) 条边 且 包含所有 n 个顶点的连通子图，即所谓的最小生成树

#### 2. Prim Algorithm步骤

1. 设G = (V, E) 是一个连通有权图， T = (U, D) 是一个最小生成树。V, U 是两个顶点集合；E, D是两个边的结合
2. 若从顶点 u 开始构造最小生成树，则从集合 V 中取出顶点 u 放入集合 U 中，标记顶点 v 的 visited[u] = 1
3. 若集合U中的顶点 $u_i$与集合V-U 中的顶点$v_j$之间存在边，则寻找这些边中 权重最小的边，但不能够构成 [环](https://zh.wikipedia.org/wiki/%E7%92%B0_(%E5%9C%96%E8%AB%96))。接着将顶点$v_j$加入到集合U中，将边（$u_i, v_j$）加入集合D中，标记 visited[$v_j$] = 1 
4. 重复第2步，直到 U == V为止，即所有的定点都被标记为“visited"。此时 D 中一共有 n - 1条边

我们之后会通过代码来帮助我们理解上面的过程。在看代码之前，先看一下具体的步骤：

##### 计算步骤：

1. 假设我们从节点A开始，发现与A相连且不构成环的边是 AC, AG, AB。其中权重最低的是AG，所以选中AG
2. 接着从A, G开始，发现与A, G相连且不构成环的边是AC, GE, GB, AB, GF。其中权重最低的是GB，所以选中GB
3. 接着从A, G, B开始，发现与A, G, B相连且不成环的边是AC, GE, GF, BD（注意AB边会成环）。其中权重最低的是EG
4. 接着从A, G, B, E ……
5. 接着从A, G, B, E, F ……
6. 接着从A, G, B, E, F, D ……
7. 接着从A, G, B, E, F, D, C，发现所有的节点都被访问过了，此时我们得到了最小生成树。被选中的边是：AG, GB, GE, AC, EF, DF