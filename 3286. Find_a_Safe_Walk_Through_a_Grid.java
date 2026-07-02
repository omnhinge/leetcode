class Solution {
    int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] arr = grid.stream()
                .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);

        int[][] dist = new int[m][n];

        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[0] - b[0]);

        dist[0][0] = arr[0][0];
        pq.offer(new int[]{arr[0][0], 0, 0});

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int cost = cur[0];
            int x = cur[1];
            int y = cur[2];

            if (x == m - 1 && y == n - 1)
                return true;

            if (cost > dist[x][y])
                continue;

            for (int[] d : dir) {

                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                    continue;

                int nextCost = cost + arr[nx][ny];

                if (nextCost >= health)
                    continue;

                if (nextCost < dist[nx][ny]) {

                    dist[nx][ny] = nextCost;

                    pq.offer(new int[]{nextCost, nx, ny});
                }
            }
        }

        return false;
    }
}