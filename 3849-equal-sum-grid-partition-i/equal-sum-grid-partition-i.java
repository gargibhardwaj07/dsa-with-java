class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for (int[] row : grid) {
            for (int val : row) total += val;
        }

        long curr = 0;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                curr += grid[i][j];
            }
            if (curr * 2 == total) return true;
        }

        curr = 0;

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                curr += grid[i][j];
            }
            if (curr * 2 == total) return true;
        }

        return false;
    }
}