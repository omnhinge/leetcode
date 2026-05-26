import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> hm.get(a) - hm.get(b));
        
        for (int num : hm.keySet()) {
            minHeap.add(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
}
public class top_k_frequent_element {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int nums[] = {21,21,1, 2, 1, 21, 2, 3};
        int k = 3;
        int arr[]= sol.topKFrequent(nums, k);
        for(int i =0;i<k;i++){
            System.err.println(arr[i]);
        }
    }
}