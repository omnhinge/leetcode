import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> count = new HashMap<>();
        long maxVal = 0;

        for (int num : nums) {
            count.put((long) num, count.getOrDefault((long) num, 0) + 1);
            maxVal = Math.max(maxVal, num);
        }

        int ans = 1;

        // Special case for 1
        if (count.containsKey(1L)) {
            int ones = count.get(1L);
            ans = Math.max(ans, (ones % 2 == 0) ? ones - 1 : ones);
        }

        for (int num : nums) {
            if (num == 1) continue;

            long x = num;
            int len = 0;

            while (x <= maxVal && count.getOrDefault(x, 0) >= 2) {
                len += 2;
                x *= x;
            }

            if (count.containsKey(x))
                ans = Math.max(ans, len + 1);
            else
                ans = Math.max(ans, len - 1);
        }

        return ans;
    }
}