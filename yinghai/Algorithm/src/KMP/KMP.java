package KMP;

/*
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
* */
public class KMP {
    public static void main(String[] args) {
        String str1 = "hello world", str2 = "llo wl";
        int index = bruteForce(str1, str2);
        System.out.println("index = " + index);
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
}















