class Solution {
    public int largestPerimeter(int[] nums) {
       Arrays.sort(nums);
         for (int i = nums.length - 3; i >= 0; i--) {
            int a = nums[i], b = nums[i + 1], c = nums[i + 2];
            if (a + b > c) {
                return a + b + c;
            }
        }
        return 0;
    }
}
//brute force 