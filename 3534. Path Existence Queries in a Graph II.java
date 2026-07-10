class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
        }

        int log = 1;
        while ((1 << log) < n) {
            log++;
        }

        int[][] up = new int[n][log];

        int r = 0;
        for (int l = 0; l < n; l++) {
            if (r < l) {
                r = l;
            }
            while (r + 1 < n && arr[r + 1][0] - arr[l][0] <= maxDiff) {
                r++;
            }
            up[l][0] = r;
        }

        for (int j = 1; j < log; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int[] ans = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {
            int src = pos[queries[k][0]];
            int dest = pos[queries[k][1]];

            if (src > dest) {
                int temp = src;
                src = dest;
                dest = temp;
            }

            if (src == dest) {
                ans[k] = 0;
                continue;
            }

            int curr = src;
            int hops = 0;

            for (int j = log - 1; j >= 0; j--) {
                if (up[curr][j] < dest) {
                    curr = up[curr][j];
                    hops += (1 << j);
                }
            }

            if (up[curr][0] >= dest) {
                ans[k] = hops + 1;
            } else {
                ans[k] = -1;
            }
        }

        return ans;
    }
}