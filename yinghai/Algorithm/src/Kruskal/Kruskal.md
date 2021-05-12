### Kruskal Algorithm

Kruskal Algorithm: Kruskal 也是用于查找最小生成树的算法。
* PRIM Algorithm是从一个节点开始出发，逐个寻找下一个权重最小的边，直到找到最小生成树为止
* Kruskal Algorithm则是先找到所有权重最小的边，然后找到所有权重次最小的边…… 直到被找到的边能构成一个最小生成树 为止。

#### 1. 应用案例

我们还是通过一个应用案例来介绍Kruskal Algorithm。假设我们有下图所示的7个村庄，7个村庄之间的距离用权重来表示，且7个村庄之间没有道路相连。现在，我们想在这7个村庄之间修路，连接7个村庄的同时，保证道路总里程最短。

<img src="D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\Kruskal\graph.png" alt="graph" style="zoom: 67%;" />

#### 2. Kruskal Algorithm介绍

1. 基本思想：按照权重从小到大的顺序 选出 n - 1条边，并保证这 n - 1条边不构成 [环](https://zh.wikipedia.org/wiki/%E7%92%B0_(%E5%9C%96%E8%AB%96))。
2. 具体做法：首先构造一个只有n 个顶点的森林，然后按照权重从小到大的顺序，连通网 中选择边 加入到森林中，并使森林中不产生环，直到森林变成一棵树为止。

#### 3. 问题分析

对于上面给定的问题来说，下面是几个可能的最小生成树：

![results](D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\Kruskal\results.jpg)

我们看到不同的最小生成树有不同的总权重值。

假设现在我们用 R 来保存当前最小生成树的结果那么：

<img src="D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\Kruskal\s1.jpg" alt="s1" style="zoom:50%;" />

<img src="D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\Kruskal\s2.jpg" alt="s2" style="zoom:50%;" />

（注意）第四步中选择CE或CF会构成环，所以只能选BF

<img src="D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\Kruskal\s3.jpg" alt="s3" style="zoom:50%;" />

上面的过程应该比较易懂。现在的问题在于：

1. 如果对图中所有的边按照权重大小进行排序
2. 在将边添加到最小生成树R 中时，如何判断是否构成了环

对于问题1，我们可以用排序算法进行排序

对于问题2，我们需要记录顶点在最小生成树中的终点，顶点的终点是”在最小生成树中与它连通的最大顶点“。然后每次需要将一条边添加到最小生成树时，判断该边的两个顶点的终点是否重合，重合的话则会构成环。

我们通过之前的第四步来理解问题2的解法：

<img src="D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\Kruskal\q1.jpg" alt="q1" style="zoom:50%;" />

在这一步时，虽然CF, CE的权重小，但是会构成环，因此只能选BF。现在已经加入到最小生成树R的边有CD, DE, EF。我们创建一个对应关系：

* C的终点是F
* D的终点是F
* E的终点是F
* F的终点是F

有了这个对应关系后，虽然CE是权重较小的边，但C, E两个顶点的终点都是F，所以有着相同的终点。因此如果加入CE边的话，会构成环。用这种方式就可以判断是否构成环了。















