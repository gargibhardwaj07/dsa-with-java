class Solution {
    public int trapRainWater(int[][] heightMap) {
         if (heightMap == null || heightMap.length == 0) return 0;

        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[2] - b[2]
        );

        // Add boundary cells
        for (int i = 0; i < m; i++) {
            minHeap.offer(new int[]{i, 0, heightMap[i][0]});
            minHeap.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = visited[i][n - 1] = true;
        }

        for (int j = 0; j < n; j++) {
            minHeap.offer(new int[]{0, j, heightMap[0][j]});
            minHeap.offer(new int[]{m - 1, j, heightMap[m - 1][j]});
            visited[0][j] = visited[m - 1][j] = true;
        }

        int water = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            int row = cell[0];
            int col = cell[1];
            int height = cell[2];

            for (int[] d : dirs) {
                int r = row + d[0];
                int c = col + d[1];

                if (r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]) {
                    visited[r][c] = true;

                    water += Math.max(0, height - heightMap[r][c]);

                    minHeap.offer(new int[]{
                        r,
                        c,
                        Math.max(height, heightMap[r][c])
                    });
                }
            }
        }

        return water;
    }
}