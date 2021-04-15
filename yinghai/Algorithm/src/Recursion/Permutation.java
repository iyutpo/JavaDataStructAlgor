package Recursion;


import java.util.ArrayList;
import java.util.List;

/*
* 找到一个数组的全排列（Permutation）
* 思路：
*   每次从数组中取出一个数，然后在数组中再任意取出一个数，组成两个数，然后再在数组中剩下的数里取一个数
*   直到取完，形成一个新的数组
* */
public class Permutation {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int length;
    public List<List<Integer>> permute(int[] nums) {
        length = nums.length;
        List<Integer> select = new ArrayList<Integer>();
        permuteBacktrack(select, nums, 0);
        return result;
    }

    private void permuteBacktrack(List<Integer> s, int[] nums, int pos) {
        if (pos == length) {
            result.add(new ArrayList<Integer>(s));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (s.contains(num))
                continue;
            s.add(num);
            permuteBacktrack(s, nums, pos+1);
            s.remove(s.size()-1);
        }
    }
}
