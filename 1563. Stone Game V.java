class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // Prefix sums
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[l][r] = maximum score for subarray [l...r]
        int[][] dp = new int[n][n];

        // Process smaller intervals first
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;

                // Try every split
                for (int k = l; k < r; k++) {
                    int left = prefix[k + 1] - prefix[l];
                    int right = prefix[r + 1] - prefix[k + 1];

                    if (left < right) {
                        // Bob removes right
                        dp[l][r] = Math.max(
                            dp[l][r],
                            left + dp[l][k]
                        );
                    } 
                    else if (left > right) {
                        // Bob removes left
                        dp[l][r] = Math.max(
                            dp[l][r],
                            right + dp[k + 1][r]
                        );
                    } 
                    else {
                        // Equal: Alice chooses which side to keep
                        dp[l][r] = Math.max(
                            dp[l][r],
                            left + Math.max(dp[l][k], dp[k + 1][r])
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
