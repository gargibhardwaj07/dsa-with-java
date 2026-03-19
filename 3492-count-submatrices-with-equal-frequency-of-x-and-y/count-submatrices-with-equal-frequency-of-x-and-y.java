class Solution {
    public int numberOfSubmatrices(char[][] grid) {
       int m = grid.length, n = grid[0].length;
        int result = 0;

        int[][] prefixX = new int[m + 1][n + 1];
        int[][] prefixY = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixX[i][j] = prefixX[i - 1][j] + prefixX[i][j - 1] - prefixX[i - 1][j - 1];
                prefixY[i][j] = prefixY[i - 1][j] + prefixY[i][j - 1] - prefixY[i - 1][j - 1];

                if (grid[i - 1][j - 1] == 'X') prefixX[i][j]++;
                if (grid[i - 1][j - 1] == 'Y') prefixY[i][j]++;

                if (prefixX[i][j] == prefixY[i][j] && prefixX[i][j] > 0) result++;
            }
        }

        return result;  
    }
}