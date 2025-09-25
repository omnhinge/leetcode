class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
                if (triangle == null || triangle.isEmpty()) return 0;
        int n = triangle.size();
        int[] dp = new int[n];
        
        // Initialize with last row
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // Bottom-up DP
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }
        return dp[0];
    }
}