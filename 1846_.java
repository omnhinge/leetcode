class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] count = new int[n + 1];

        for (int x : arr) {
            count[Math.min(x, n)]++;
        }

        int ans = 1;
        for (int i = 2; i <= n; i++) {
            ans = Math.min(ans + count[i], i);
        }

        return ans;
    }
}