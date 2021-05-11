### KMP

#### 1. 介绍

KMP 是 ”字符串匹配“ 问题中非常常用的一种算法。

参考：

[1]: https://www.youtube.com/watch?v=y-cH2HEuPew&list=PLmOn9nNkQxJFvyhDYx0ya4F75uTtUHA_f&index=161
[2]: https://zh.wikipedia.org/wiki/KMP%E7%AE%97%E6%B3%95	"（强烈推荐Wikipedia）"
[3]: https://www.cnblogs.com/zzuuoo666/p/9028287.html

##### 1.1. 问题描述

当有一个字符串 S = "ABC ABCDAB ABCDABCDABDE", 和另一个字符串 W = "ABCDABD"，要判断 S中是否含有 W。如果存在，就返回W 在 S 中第一次出现的位置。如果没有，就返回-1.

#### 1.2. 爆破算法求解（不建议跳跃阅读此部分）

不难看出该问题是可以用 暴破 求解的，时间复杂度为 O(n m)。虽然暴破的时间复杂度很高，但是为了介绍KMP，我们还是需要了解一下暴破的步骤：

下图是S, W 两个字符串最初的位置，同时我们让m, i 分别为 S, W的索引。我们发现在 W[3]="D"的地方失配：

<p style="text-align:center">Table 1</p>

| m     | 0                                 | 1                                 | 2                                 | 3                                | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   | 15   | 16   | 17   | 18   | 19   | 20   | 21   | 22   |
| ----- | --------------------------------- | --------------------------------- | --------------------------------- | -------------------------------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **S** | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> |                                  | A    | B    | C    | D    | A    | B    |      | A    | B    | C    | D    | A    | B    | C    | D    | A    | B    | D    | E    |
| **W** | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> | <span style="color:red">D</span> | A    | B    | D    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |
| **i** | <span style="color:blue">0</span> | <span style="color:blue">1</span> | <span style="color:blue">2</span> | <span style="color:red">3</span> | 4    | 5    | 6    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |

此时，如果按照暴破解法，我们需要将W字符串向右移动一位，从而从头开始匹配。也就是说我们需要分别从 S[1], W[0]：

<p style="text-align:center">Table 2</p>

| m     | 0                                 | 1                                 | 2                                 | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   | 15   | 16   | 17   | 18   | 19   | 20   | 21   | 22   |
| ----- | --------------------------------- | --------------------------------- | --------------------------------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **S** | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> |      | A    | B    | C    | D    | A    | B    |      | A    | B    | C    | D    | A    | B    | C    | D    | A    | B    | D    | E    |
| **W** |                                   | <span style="color:red">A</span>  | B                                 | C    | D    | A    | B    | D    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |
| **i** |                                   | <span style="color:red">0</span>  | 1                                 | 2    | 3    | 4    | 5    | 6    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |

S[2], W[1]：

<p style="text-align:center">Table 3</p>

| m     | 0                                 | 1                                 | 2                                 | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   | 15   | 16   | 17   | 18   | 19   | 20   | 21   | 22   |
| ----- | --------------------------------- | --------------------------------- | --------------------------------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **S** | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> |      | A    | B    | C    | D    | A    | B    |      | A    | B    | C    | D    | A    | B    | C    | D    | A    | B    | D    | E    |
| **W** |                                   |                                   | <span style="color:red">A</span>  | B    | C    | D    | A    | B    | D    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |
| **i** |                                   |                                   | <span style="color:red">0</span>  | 1    | 2    | 3    | 4    | 5    | 6    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |

…… 直到 S[4], W[0] 时，才会发生W[0]与某个S[?] 之间的匹配：

<p style="text-align:center">Table 4</p>

| m     | 0                                 | 1                                 | 2                                 | 3    | 4                                 | 5                                 | 6                                 | 7                                 | 8                                 | 9                                 | 10                               | 11   | 12   | 13   | 14   | 15   | 16   | 17   | 18   | 19   | 20   | 21   | 22   |
| ----- | --------------------------------- | --------------------------------- | --------------------------------- | ---- | --------------------------------- | --------------------------------- | --------------------------------- | --------------------------------- | --------------------------------- | --------------------------------- | -------------------------------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **S** | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> |      | A                                 | B                                 | C                                 | D                                 | A                                 | B                                 |                                  | A    | B    | C    | D    | A    | B    | C    | D    | A    | B    | D    | E    |
| **W** |                                   |                                   |                                   |      | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> | <span style="color:blue">D</span> | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:red">D</span> |      |      |      |      |      |      |      |      |      |      |      |      |
| **i** |                                   |                                   |                                   |      | <span style="color:blue">0</span> | <span style="color:blue">1</span> | <span style="color:blue">2</span> | <span style="color:blue">3</span> | <span style="color:blue">4</span> | <span style="color:blue">5</span> | <span style="color:red">6</span> |      |      |      |      |      |      |      |      |      |      |      |      |



#### 1.3. KMP 算法介绍

从Table 2 到 Table 4 之间的步骤似乎非常多余。有没有什么方法可以帮我们从Table 1直接跳到Table 4呢？——有。

我们重新观察 W 字符串和Table 1发现，W的子串'AB' 在W的首尾处重复出现。如果能利用这一特征，似乎就可以帮我们从Table 1 跳跃到 Table 4，从而省掉中间多余的匹配过程。为了实现这一目的，就要介绍KMP算法中最重要的一个部分——**部分匹配表**（有时候也可以用一个 **next 数组**来表示，也叫做**失配函数**）。

**失配函数的作用是，让算法无需多次匹配S 中的任何字符。能够实现在O(n) 时间内搜索的关键是在 S 字符串中检查 W 字符串的初始字段（我们的例子中就是"AB"），从而我们可以确切的知道在当前位置之前的一个潜在的匹配位置。**

##### 1.3.1. 创建部分匹配表

注意，部分匹配表是针对 W 字符串而言的，目的是找到W 中的重复部分，我们**用T来表示生成出来的匹配表**。我们先来看代码：

```python
def kmpTable(W):
    T = [0] * len(W)		# 初始化匹配表，T是与W等长的全0数组
    T[0] = -1				# 默认上，T[0] ≡ -1。另外，T的长度默认 >= 2。如果 < 2，则没必要使用KMP
    pos, cnd = 2, 0			# pos, cnd分别是T 和 W 的索引
    while (pos < len(W)):
        if (W[pos - 1] == W[cnd]):	# 当W类似于"AAAAC"，"BBC", "AAABBC"这种W[0]=W[1]=...时
            cnd += 1				
            T[pos] = cnd			# 就让T[pos] = cnd
            pos += 1
        elif (cnd > 0):				# 当 cnd > 0，说明发现了重复部分
            cnd = T[cnd]			
        else:						# 其他情况下，T[pos] = 0
            T[pos] = 0
            pos += 1
	return T
```

调用上面`kmpTable`函数来看两个案例：

案例一：

```python
W = "ABCDABD"
print(kmpTable(W))
```

<p style="text-align:center">Table 5</p>

| **i** | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
| ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| **W** | A     | B     | C     | D     | A     | B     | D     |
| **T** | -1    | 0     | 0     | 0     | 0     | 1     | 2     |



案例二：

```python
W = "PARTICIPATE IN PARACHUTE"
print(kmpTable(W))
```

<p style="text-align:center">Table 6</p>

| m     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   | 15   | 16   | 17   | 18   | 19   | 20   | 21   | 22   | 23   |
| ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **W** | P    | A    | R    | T    | I    | C    | I    | P    | A    | T    | E    |      | I    | N    |      | P    | A    | R    | A    | C    | H    | U    | T    | E    |
| **T** | -1   | 0    | 0    | 0    | 0    | 0    | 0    | 0    | 1    | 2    | 0    | 0    | 0    | 0    | 0    | 0    | 1    | 2    | 3    | 0    | 0    | 0    | 0    | 0    |

看到这里，你可能还是疑惑，部分匹配表`T`的含义到底是什么。**简单来说部分匹配表`T`就是除了当前字符外 的最长相同前缀后缀。**我们还以上面的W = "ABCDABD"为例，分别找到W的前缀和后缀。**要注意，前缀和后缀不能是W本身，且前缀不能包括W[-1]，后缀不能包括W[0]**（其中，W[-1]是W 的最后一个字符）。

<p style="text-align:center">Table 7</p>

| W       | 前缀                                                   | 后缀                                                | 最长相同前缀后缀 |
| ------- | ------------------------------------------------------ | --------------------------------------------------- | ---------------- |
| A       | None                                                   | None                                                | 0                |
| AB      | A                                                      | B                                                   | 0                |
| ABC     | A, AB                                                  | BC, C                                               | 0                |
| ABCD    | A, AB, ABC                                             | BCD, CD, D                                          | 0                |
| ABCDA   | <span style="color:red">A</span>, AB, ABC, ABCD        | BCDA, CDA, DA, <span style="color:red">A</span>     | 1                |
| ABCDAB  | A, <span style="color:red">AB</span>, ABC, ABCD, ABCDA | BCDAB, CDAB, DAB, <span style="color:red">AB</span> | 2                |
| ABCDABD | A, AB, ABC, ABCD, ABCDA, ABCDAB                        | BCDABD, CDABD, DABD, ABD, BD                        | 0                |

我们将Table 7最后一列写成 `longest = [0, 0, 0, 0, 1, 2, 0]`。而Table 5计算出的部分匹配表为`T = [-1, 0, 0, 0, 0, 1, 2]`。`T`和`longest`非常相似，实际上，`T`就是由`longest`数组pop() 出最后一位`0`，然后在最前边加上`-1`而来的。



##### 1.3.2. 应用部分匹配表查找匹配位置

我们还是先来看代码：

<p style="text-align:center">Table 1</p>

| m     | 0                                 | 1                                 | 2                                 | 3                                | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   | 15   | 16   | 17   | 18   | 19   | 20   | 21   | 22   |
| ----- | --------------------------------- | --------------------------------- | --------------------------------- | -------------------------------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **S** | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> |                                  | A    | B    | C    | D    | A    | B    |      | A    | B    | C    | D    | A    | B    | C    | D    | A    | B    | D    | E    |
| **W** | <span style="color:blue">A</span> | <span style="color:blue">B</span> | <span style="color:blue">C</span> | <span style="color:red">D</span> | A    | B    | D    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |
| **i** | <span style="color:blue">0</span> | <span style="color:blue">1</span> | <span style="color:blue">2</span> | <span style="color:red">3</span> | 4    | 5    | 6    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |

```python
T = kmpTable(W)
def KMP(S, W):
    slen, wlen = len(S), len(W)
    m, i = 0, 0
    while (m < slen and i < wlen):
        if (i == -1 or S[m] == W[i]):	# 当发生了匹配时，就继续检查W的下一个字符是否匹配
            i += 1
            m += 1
        else:			# 当失配时，如S[3]和W[3],我们让 i = T[i=3] = 0，表示需要重新搜索 W了
            i = T[i]	# 但我们发现只有i=0，而m = 3，所以这就保证了线性的搜索时间					
    if (i == wlen): return m - i
    else: return -1
```

如果想看`KMP`的执行细节，可以参考下面的.gif文件或视频文件：

![ezgif.com-gif-maker](D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\KMP\ezgif.com-gif-maker.gif)

<video src="D:\LANGUAGE\IntelliJUltimate\JavaDataStructAlgor\yinghai\Algorithm\src\KMP\ezgif.com-gif-maker.mp4"></video>

##### 1.3.3. KMP算法的完整代码

```python
def kmpTable(W):
    T = [0] * len(W)		# 初始化匹配表，T是与W等长的全0数组
    T[0] = -1				# 默认上，T[0] ≡ -1。另外，T的长度默认 >= 2。如果 < 2，则没必要使用KMP
    pos, cnd = 2, 0			# pos, cnd分别是T 和 W 的索引
    while (pos < len(W)):
        if (W[pos - 1] == W[cnd]):	# 当W类似于"AAAAC"，"BBC", "AAABBC"这种W[0]=W[1]=...时
            cnd += 1				
            T[pos] = cnd			# 就让T[pos] = cnd
            pos += 1
        elif (cnd > 0):				# 当 cnd > 0，说明发现了重复部分
            cnd = T[cnd]			
        else:						# 其他情况下，T[pos] = 0
            T[pos] = 0
            pos += 1
	return T

def KMP(S, W):
    T = kmpTable(W)
    slen, wlen = len(S), len(W)
    m, i = 0, 0
    while (m < slen and i < wlen):
        if (i == -1 or S[m] == W[i]):	# 当发生了匹配时，就继续检查W的下一个字符是否匹配
            i += 1
            m += 1
        else:			# 当失配时，如S[3]和W[3],我们让 i = T[i=3] = 0，表示需要重新搜索 W了
            i = T[i]	# 但我们发现只有i=0，而m = 3，所以这就保证了线性的搜索时间					
    if (i == wlen): return m - i
    else: return -1

# For test use only:
S, W = 'ABC ABCDAB ABCDABCDABDE', 'ABCDABD'
print(KMP(S, W))			# Output --> 15
```



#### 2.1. 推荐阅读

如果你对KMP算法非常感兴趣，那么非常推荐[BM算法](http://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html)、[有限状态自动机](http://d.pr/i/NEiz)、[Sunday算法](http://blog.csdn.net/sunnianzhong/article/details/8820123)。













