import java.util.Arrays;

class Solution {
    private int[][][] dp;
    private final int MOD = 1000000007;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        dp = new int[n][maxVal + 1][maxVal + 1];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }

        return solve(0, 0, 0, nums);
    }

    private int solve(int i, int g1, int g2, int[] nums) {
        if (i == nums.length) {
            if (g1 > 0 && g2 > 0 && g1 == g2) {
                return 1;
            }
            return 0;
        }

        if (dp[i][g1][g2] != -1) {
            return dp[i][g1][g2];
        }

        long skip = solve(i + 1, g1, g2, nums);
        long take1 = solve(i + 1, gcd(g1, nums[i]), g2, nums);
        long take2 = solve(i + 1, g1, gcd(g2, nums[i]), nums);

        long total = (skip + take1 + take2) % MOD;
        return dp[i][g1][g2] = (int) total;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}