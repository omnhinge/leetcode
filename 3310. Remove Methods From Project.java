class Solution {
    private void markSuspicious(int u, List<List<Integer>> adj, boolean[] suspicious) {
        suspicious[u] = true;
        for (int v : adj.get(u)) {
            if (!suspicious[v]) {
                markSuspicious(v, adj, suspicious);
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        // Phase 1: Find all suspicious methods starting from k
        boolean[] suspicious = new boolean[n];
        markSuspicious(k, adj, suspicious);

        // Phase 2: Check if any non-suspicious node points to a suspicious node
        boolean safeToRemove = true;
        for (int[] inv : invocations) {
            int u = inv[0], v = inv[1];
            if (!suspicious[u] && suspicious[v]) {
                safeToRemove = false;
                break;
            }
        }

        // Phase 3: Collect results
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!safeToRemove || !suspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}