package Hash;

/*
* hash table是非常常见的数据结构之一，是典型的用空间换取时间的一种数据结构。
* 这种类型的数据结构经常被用于搭建缓存（cache）。目前虽然有很多主流的缓存产品（如redis，memcache等）
* 但是，在这些产品出现之前，通常需要程序员自己搭建一个自己的缓存。比较常见的用于实现一个Hash Table的算法和数据结构是：
*   1. Hash Table = Array + LinkedList + Binary Tree
* Array<LinkedList>()   // hash table 可以用一个Array来实现，Array中是若干个LinkedList
* Array   LinkedList
* key  -> values
* [0]  -> [30 null] -> [301 null] -> ...
* [1]  -> [1 null] -> [233 null] -> ...
* [2]  -> [23 null] -> [180 null] -> ...
* [3]  -> [68 null] -> [584 null] -> ...
* [4]  -> [115 null] -> [613 null] -> ...
* [5]  -> [38 null] -> [397 null] -> ...
* [6]  -> [99 null] -> [421 null] -> ...
*
* 重点！
* Hash Table是如何实现的呢？
* 我们都知道Hash Table中有两个部分组成: key + value
* key是unique的，不能有重复，这一特性保证了key是可以被用作索引的。那么如何确保这么多key是不重复的呢？
*   A: 用一个映射函数（常叫做 散列函数）来实现。 举个例子：
* 假设我们有一个 hash table，里面有 7 个key。我们传入一个数 200，那么 200 mod 7 = 4 （取余）。所以200应该在第4个key上
*
* */
public class HashTable {

}
