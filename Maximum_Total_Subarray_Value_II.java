// Maximum Total Subarray Value II
import java.util.*;

class Solution {
    public long maxTotalValue(int[] nums, int k) {

        List<Long> values = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int min = nums[i];
            int max = nums[i];

            for (int j = i; j < nums.length; j++) {

                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);

                values.add((long)(max - min));
            }
        }

        values.sort(Collections.reverseOrder());

        long result = 0;

        for (int i = 0; i < k && i < values.size(); i++) {
            result += values.get(i);
        }

        return result;
    }
}

public class Maximum_Total_Subarray_Value_II {
    public static void main(String[] args) {

        int[] arr = {11, 8};
        int k = 2;

        Solution sol = new Solution();

        System.out.println(sol.maxTotalValue(arr, k));
    }
}