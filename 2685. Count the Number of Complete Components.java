class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] info = new int[2];
                dfs(i, graph, visited, info);

                if (info[1] == info[0] * (info[0] - 1))
                    completeCount++;
            }
        }

        return completeCount;
    }

    private void dfs(int curr, List<List<Integer>> graph, boolean[] visited, int[] info) {
        visited[curr] = true;
        info[0]++;
        info[1] += graph.get(curr).size();

        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                dfs(next, graph, visited, info);
            }
        }
    }
}