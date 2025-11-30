class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum = (sum + num) % p;
        }
        if (sum == 0) return 0;
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, -1);
        int currentSum = 0;
        int minLength = n;
        for (int i = 0; i < n; i++) {
            currentSum = (currentSum + nums[i]) % p;
            int target = (currentSum - sum + p) % p;
            if (prefixSum.containsKey(target)) {
                minLength = Math.min(minLength, i - prefixSum.get(target));
            }
            prefixSum.put(currentSum, i);
        }return minLength == n ? -1 : minLength;
    }
}
