package Trie;


/* (ref: https://zh.wikipedia.org/wiki/Trie)
* Trie 也叫 Prefix Tree 或者字典树。这是一种有序树，用于保存关联数组。其中的 key 通常是 String/Char。
* Trie 与 Binary Tree不同的是，Trie的 key不直接保存在 节点中，而是 由节点在树中的位置所决定。
* 一个节点的所有 子孙节点都有相同的 prefix，也就是这个界定对应的字符串。而根节点所对应的是 空字符串。
* 一般来说，不是所有的节点都有对应的值，只有叶节点和部分内部节点所对应的 key 才有相关的值。
* 我们来看一个 trie 的例子：
*                               None
*                   t/           |A            \i
*               t                A                  i
*      o/           \e          15                 11   \n
*     to            te                                     in
*     7          a/  d|  \n                             /n   5
*              tea   ted   ten                        inn
*               3     4     12                         9
* 在该 Trie 的例子中，保存了 8 个key，分别是 'A', 'to', 'tea', 'ted', 'ten', 'i', 'in', 'inn'
* 下面我们来实现一个Trie。同时我们还会实现对Trie的 insert， search的操作。
* */

import java.util.HashMap;

// 首先我们创建一个 Node 类来实现 Trie 的各个节点。
class Node {
    // 每个Node 都有各自的 children节点，children用一个HashMap来实现
    public HashMap<Character, Node> children = new HashMap<Character, Node>();
    public boolean endOfWord = false;
}


public class Trie {
    private Node root = new Node();

    public void insert(String word) {
        Node ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!ptr.children.containsKey(letter)) {    // 如果某个 key（letter）不存在于HashMap，就添加一个
                ptr.children.put(letter, new Node());
            }
            ptr = ptr.children.get(letter);
        }
        ptr.endOfWord = true;
    }

    public boolean search(String word) {
        Node ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!ptr.children.containsKey(letter)) {
                return false;
            }
            ptr = ptr.children.get(letter);
        }
        if (ptr.endOfWord) { return true; }
        else { return false; }
    }

    public boolean startsWith(String prefix) {
        Node ptr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char letter = prefix.charAt(i);
            if (!ptr.children.containsKey(letter)) {
                return false;
            }
            ptr = ptr.children.get(letter);
        }
        return true;
    }

    public static void main(String[] args) {
        // 测试我们的trie

    }
}





















