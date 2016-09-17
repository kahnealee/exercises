package lc;

/**
 * Created by kpan on 9/16/16.
 */
public class L189RotateArray {
    //https://leetcode.com/discuss/58135/a-7-line-time-o-n-in-place-solution-no-reversing
    public void rotate(int[] nums, int k) {
        if(nums.length == 0 || k % nums.length == 0) return;
        int start = 0, i = start, curNum = nums[i], count = 0;
        while(count < nums.length){
            i = (i + k) % nums.length;
            int tmp = nums[i];
            nums[i] = curNum;
            if(i == start){
                start++;
                i = start;
                curNum = nums[i];
            }
            else curNum = tmp;
            count++;
        }
    }
}
