class Solution {

    int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        Queue<int[]> q = new ArrayDeque<>();

        // dist[i][j] stores distance from nearest thief + 1
        int[][] dist = new int[n][n];

        // Initialize queue with all thief cells
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int val = grid.get(i).get(j);
                dist[i][j] = val;

                if(val == 1){
                    q.offer(new int[]{i, j});
                }
            }
        }

        // Multi-source BFS
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for(int i = 0; i < 4; i++){
                int r = x + dir[i][0];
                int c = y + dir[i][1];

                if(r < 0 || r >= n || c < 0 || c >= n || dist[r][c] > 0)
                    continue;

                dist[r][c] = dist[x][y] + 1;
                q.offer(new int[]{r, c});
            }
        }

        // Max Heap for Dijkstra
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );

        pq.offer(new int[]{dist[0][0], 0, 0});
        dist[0][0] = -1;

        while(!pq.isEmpty()){

            int[] curr = pq.poll();

            int safe = curr[0];
            int x = curr[1];
            int y = curr[2];

            if(x == n - 1 && y == n - 1){
                return safe - 1;
            }

            for(int i = 0; i < 4; i++){

                int r = x + dir[i][0];
                int c = y + dir[i][1];

                if(r < 0 || r >= n || c < 0 || c >= n || dist[r][c] < 0)
                    continue;

                int newSafe = Math.min(safe, dist[r][c]);

                pq.offer(new int[]{newSafe, r, c});
                dist[r][c] = -1;
            }
        }

        return 0;
    }
}