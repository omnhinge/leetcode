class Solution {
    static final long MOD = 1_000_000_007L;
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        int maxDepth = 0;

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(new int[]{1, 0});
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int node = cur[0];
            int depth = cur[1];

            maxDepth = Math.max(maxDepth, depth);

            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, depth + 1});
                }
            }
        }

        if (maxDepth == 0) {
            return 0;
        }

        return (int) modPow(2, maxDepth - 1);
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}