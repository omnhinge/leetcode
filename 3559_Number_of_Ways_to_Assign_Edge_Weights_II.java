class Solution {

    static final int MOD = 1_000_000_007;
    static final int LOG = 18;

    int[] depth;
    int[][] lift;
    List<Integer>[] graph;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {

        int n = edges.length + 1;

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        depth = new int[n + 1];
        lift = new int[n + 1][LOG];

        bfs(n);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                lift[i][j] = lift[lift[i][j - 1]][j - 1];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int ancestor = lca(u, v);

            int dist = depth[u] + depth[v] - 2 * depth[ancestor];

            if (dist == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) modPow(2, dist - 1);
            }
        }

        return ans;
    }

    private void bfs(int n) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(1);
        visited[1] = true;
        lift[1][0] = 1;

        while (!q.isEmpty()) {

            int curr = q.poll();

            for (int next : graph[curr]) {

                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                depth[next] = depth[curr] + 1;
                lift[next][0] = curr;

                q.offer(next);
            }
        }
    }

    private int lca(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) == 1) {
                u = lift[u][j];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {

            if (lift[u][j] != lift[v][j]) {
                u = lift[u][j];
                v = lift[v][j];
            }
        }

        return lift[u][0];
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