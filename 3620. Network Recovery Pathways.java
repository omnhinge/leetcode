class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        if (!online[0] || !online[n - 1]) {
            return -1;
        }

        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        int l = Integer.MAX_VALUE;
        int r = Integer.MIN_VALUE;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (!online[u] || !online[v]) continue;

            g[u].add(new int[]{v, w});

            l = Math.min(l, w);
            r = Math.max(r, w);
        }

        if (l == Integer.MAX_VALUE) return -1;

        int ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (check(g, k, mid, n)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(List<int[]>[] g, long k, int mid, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long d = cur[0];
            int u = (int) cur[1];

            if (d > dist[u]) continue;

            if (d > k) break;

            if (u == n - 1) return true;

            for (int[] edge : g[u]) {
                int v = edge[0];
                int w = edge[1];

                if (w < mid) continue;

                long nd = d + w;

                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{nd, v});
                }
            }
        }

        return false;
    }
}