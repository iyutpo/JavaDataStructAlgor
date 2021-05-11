package KMP;

/* 在运行该代码之前，（下面的注释，由于不方便绘图，所以写的比较乱）强烈建议阅读一下 KMP.md 文件
* KMP 是 ”字符串匹配“ 问题中非常常用的一种算法。（参考：https://www.youtube.com/watch?v=y-cH2HEuPew&list=PLmOn9nNkQxJFvyhDYx0ya4F75uTtUHA_f&index=161）
*   问：当有一个字符串 str1 = "hello world",  str2 = "llo wl"，要判断 str1 中是否含有 str2。如果存在，就返回
*      str2 在 str1 中第一次出现的为止。如果没有，就返回-1.
*   不难看出该问题是可以用 暴破 求解的，时间复杂度为 O(n m)。虽然暴破的时间复杂度很高，但是为了介绍KMP，我们还是需要了解一下暴破的步骤：
*   (1). 首先我们有两个指针i, j 用于遍历 str1, str2。最初的位置如下图所示：
*    str1 --> hello world             str2 --> llo wl
*             i                                j
*   (2). 下面我们快进一下：
*    str1 --> hello world             str2 --> llo wl
*               i                              j
*       此时，i, j所指向的字符串发生了匹配
*   (3). 这种匹配现象一直持续到下图：
*    str1 --> hello world             str2 --> llo wl
*                    i                              j
*       这时，i, j所指向的字符串不再匹配。在暴破中，i 会重新指向str1的第二个 "l"； j 会重新指向str2的第一个 "l"，变成：
*    str1 --> hello world             str2 --> llo wl
*                i                              j
*   重点：我们看到， 从 str2的第一个"l"开始到str2的第三个 "l"之间发生了很多次匹配，由于最后一个字符不匹配，所以i和j指针必须重置，而这个重置
*        不可避免地造成了”前功尽弃“。而KMP 算法就是为了消除这种”前功尽弃“ 而存在的算法，也因此提高了暴破解法的效率。
*   我们接下来会同时实现 暴破 和 KMP 算法，以作为比较。
*
* KMP 算法：
*   KMP 算法的思想就是利用之前判断过的信息，通过一个 next array 来记录之前的判断过的信息，保存字符串中 前后最长公共子序列的长度，每次”重置“时，
*   通过 next array 找到 前面匹配过的位置，从而省去了大量的计算时间。 （很详细的KMP介绍：https://www.cnblogs.com/zzuuoo666/p/9028287.html）
*  我们来通过一个例子来说明一下KMP：
*   追随爆破的流程， 假设现在我们的 str1 匹配到了 i 位置， str2 匹配到了 j 位置：
*       (1). 如果 j = -1, 或者当前的字符匹配成功（i.e. str1[i] == str2[j]），则令 i++, j++，继续匹配下一个字符
*       (2). 如果 j != -1, 且当前字符匹配失败（i.e. str1[i] != str2[j]），则令 i 不变，j = next[j]。这意味着，失配时，str2 相对于 str1
*            向右移动了 j - next[j] 位。
*            换句话说，当失配时，str2 向右移动的位置是：”失配字符所在的位置 - 失配字符对应的next值”，也就是说，实际移动的位置为：j - next[j]，
*            且 该值一定 大于等于 1.
*   相信上面的介绍还是很难懂，最大的难点在于next到底是什么。我们会在接下来的文字中介绍next。
*   next array中各个值所代表的含义是：当前字符串 之前的字符串中，有多少长度相同的 前缀后缀。例如，如果next[j] = k，代表 j 之前的字符串中有
*                                 最大长度为 k 的相同前缀后缀。
*                                 这也意味着在某个字符失配时，该字符对应的 next 值 会告诉你下一步匹配中， str2应该跳转到哪个位置（跳到next[j]位置）
*                                 如果 next[j] = 0 或者 -1，则跳转到str2的开头字符。如果 next[j] = k 且 k > 0，则下次匹配跳到 j 之前的
*                                 某个字符，而不是开头，且这次跳跃 跳过了 k 个字符。转换为代码就是：
*           public static int kmpSearch(String str1, String str2) {
*               int i = 0, j = 0;
*               int s1Length = str1.length, s2Length = str2.length;
*               while (i < s1Length && j < s2Length) {
*                   // 如果 j == -1，或者当前字符匹配成功（即 str1[i] == str2[j]），就让 i++, j++
*                   if (j == -1 || str1[i] == str2[j]) {
*                       i++;
*                       j++;
*                   } else {
*                       // 如果 j != -1，且当前字符匹配失败（即str1[i] != str2[j]），则 i 不变， j = next[j]
*                       j = next[j];
*                   }
*               }
*               if (j == s2Length) { return i = j; }
*               else { return -1; }
*           }
*   继续看之间的案例，结合上面的代码，当 str1[7] != str2[5]时，KMP 不是像暴破那样 简单地把str2 右移一位，而是执行上面else部分的语句
*   “如果 j != -1, 且当前字符失配（即 str1[i] != str2[j]，则令 i 不变，j = next[j]）”即 j 从5 移动到
* */
public class KMP {
    public static void main(String[] args) {
        // Testing Brute Force Algorithm from here
        System.out.println("Testing Brute Force Algorithm from here: ");
        String str1 = "hello world", str2 = "llo wl";
        int index = bruteForce(str1, str2);
        System.out.println("index = " + index);

        // Testing KMP Algorithm from here
        System.out.println("Testing KMP Algorithm from here: ");
        String S = "ABC ABCDAB ABCDABCDABDE", W = "ABCDABD";
        System.out.println("Matched Position is: " + KMP(S, W));
    }

    // Brute-force solution:
    public static int bruteForce(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Length = s1.length;
        int s2Length = s2.length;

        int i = 0, j = 0;
        while (i < s1Length && j < s2Length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        // 判断是否存在 完全匹配的 案例：
        if (j == s2Length) {
            return i - j;
        }
        return -1;
    }


    // 下面是KMP算法的实现：
    // 首先是建立部分匹配表的函数
    private static int[] kmpTable(String W) {
        int[] T = new int[W.length()];      // 初始化匹配表，T是与W等长的全0数组
        T[0] = -1;                          // 默认上，T[0] ≡ -1。另外，T的长度默认 >= 2。如果 < 2，则没必要使用KMP

        int pos = 2, cnd = 0;               // pos, cnd分别是T 和 W 的索引
        while(pos < W.length()) {
            if (W.charAt(pos - 1) == W.charAt(cnd)) {   // 当W类似于"AAAAC"，"BBC", "AAABBC"这种W[0]=W[1]=...时
                cnd++;
                T[pos] = cnd;                           // 就让T[pos] = cnd
                pos++;
            } else if (cnd > 0) {                       // 当 cnd > 0，说明发现了重复部分
                cnd = T[cnd];
            } else {                                    // 其他情况下，T[pos] = 0
                T[pos] = 0;
                pos++;
            }
        }
        return T;
    }

    public static int KMP(String S, String W) {
    /**
     * @param S --> Longer String, e.g., "ABC ABCDAB ABCDABCDABDE"
     * @param W --> Shorter String, e.g., "ABCDABD"
     * @param output --> int. If not found then return -1; otherwise return the first position where W matches S
     * */
        int slen = S.length(), wlen = W.length();
        int m = 0, i = 0;
        int[] T = kmpTable(W);

        while (m < slen && i < wlen) {
            if (i == -1 || S.charAt(m) == W.charAt(i)) {
                i++;
                m++;
            } else {
                i = T[i];
            }
        }

        if (i == wlen) { return m - i; }
        else { return -1; }
    }
}















