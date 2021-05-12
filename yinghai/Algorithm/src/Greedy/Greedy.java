package Greedy;

/*
* Greedy Algorithm（贪心算法），顾名思义，就是指在对某个问题进行求解时，在每一步的求解中，都选择最优解，从而希望能够最终获得全局最优解的算法。
* 要注意，贪心算法并不能保证一定能达到全局最优解。因此该算法通常只能用于逼近全局最优解的结果。
* 另外在面试中，除非有特定要求，否则也不推荐使用贪心算法来求解。因为需要证明你所使用的贪心算法会得到全局最优，而这种证明可能需要复杂的逻辑和数学推导
*
* 我们来看一个贪心算法的案例。假设有K1, K2, ..., K5共 5个广播台，以及这些广播台所能覆盖的城市。问如何用最少的广播台个数，覆盖所有的城市？
*           广播台                 覆盖城市
*           K1                    北京，上海，天津
*           K2                    广州，北京，深圳
*           K3                    成都，上海，杭州
*           K4                    上海，天津
*           K5                    杭州，大连
* 最容易想到的解法就是穷举法了。用穷举法列出每个可能的广播台的集合（称为 幂集）。假设现在有 n 个广播台，则广播台的组合总共有 2^n - 1 个。
* 我们看到穷举法的复杂度是指数级增长的，显然这并不是一个高效的算法。
* 贪心算法：
*   (1). 遍历所有的广播台，找到一个覆盖最多 “未覆盖城市”的 广播台，该广播台可能也会包含一些已经覆盖了的城市，但没有关系
*   (2). 将这个广播台加入到一个集合中，想办法把该广播台覆盖的地区在下次比较时去掉
*   (3). 重复(1)，直到所有城市都被覆盖为止
*
* 我们来看具体步骤：
*   (1). 找到所有的城市： cities = {"北京", "上海", "天津", "杭州", "深圳", "成都", "广州", "大连"}, 被选出的城市： selected = {};
*   (2). 列举出每个广播台覆盖的 “未覆盖城市” 的个数： K1 --> 3, K2 --> 3, K3 --> 3, K4 --> 2, K5 --> 2
*   (3). 我们发现 K1, K2, K3 都覆盖了3 个“未覆盖城市”，所以我们任选一个。假设选择了 K1。要注意，一旦选择了 K1， K1就变成 K1 --> 0 ，
*        同理，我们还要更新其他 4 个广播台： K2 --> 2, K3 --> 2, K4 --> 0, K5 --> 2;        selected = {K1};
*   (4). 那么我们不断重复上面的过程，就能得到最终解了。 K1 --> 0, K2 --> 0, K3 --> 2, K4 --> 0, K5 --> 2;   selected = {K1, K2}
*   (5). K1 --> 0, K2 --> 0, K3 --> 0, K4 --> 0, K5 --> 1;          selected = {K1, K2, K3};
*   (6). K1 --> 0, K2 --> 0, K3 --> 0, K4 --> 0, K5 --> 0;          selected = {K1, K2, K3, K5};
 *
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {

    public static void main(String[] args) {
        //  创建广播台：
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        // 将广播台放入：
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");
        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");
        // 加入到map：
        broadcasts.put("K1", set1);
        broadcasts.put("K2", set2);
        broadcasts.put("K3", set3);
        broadcasts.put("K4", set4);
        broadcasts.put("K5", set5);

        // 存放所有地区
        HashSet<String> areas = new HashSet<>();
        areas.add("北京");
        areas.add("上海");
        areas.add("天津");
        areas.add("广州");
        areas.add("成都");
        areas.add("杭州");
        areas.add("深圳");
        areas.add("大连");

        // 存放所有被选择了的广播台：
        ArrayList<String> selected = new ArrayList<>();
        // 定义临时变量存放遍历过程中，广播台覆盖的地区和当前还没有覆盖的城市的交集：
        HashSet<String> tempSet = new HashSet<>();

        String maxKey;
        while (areas.size() != 0) {
            // 每次进行while时，将maxKey变为null
            maxKey = null;

            for (String key : broadcasts.keySet()) {
                tempSet.clear();

                HashSet<String> area = broadcasts.get(key);
                tempSet.addAll(area);
                tempSet.retainAll(areas);   // 取出 hashset1和hashset2 之间共有的部分，并赋予hashset1

                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selected.add(maxKey);
                areas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的结果： " + selected);
    }
}





















