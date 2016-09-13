package lc;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by kpan on 6/24/16.
 */
public class L215KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

        for (int num : nums) {
            if (heap.size() < k || (heap.size() == k && num > heap.peek())) {
                heap.add(num);
            }

            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.remove();
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

        for (int num : nums) {
            if (heap.size() < k) {
                heap.add(num);
            } else if (heap.peek() < num) {
                heap.add(num);
                heap.remove();
            }
        }
        return heap.peek();
    }


    @Test
    public void testDeserialization() {

        Assert.assertEquals(15, findKthLargest(new int[] {2, 1,16, 5, 7, 10,15, 8,8,20,8,4,4,7,1,16,8}, 4));
    }
}
