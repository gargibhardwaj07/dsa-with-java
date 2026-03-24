class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
          int MOD = 12345;
        int m = grid.length, n = grid[0].length;
        int size = m * n;

        int[] arr = new int[size];
        int idx = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val % MOD;
            }
        }

        int[] prefix = new int[size];
        int[] suffix = new int[size];

        prefix[0] = arr[0];
        for (int i = 1; i < size; i++) {
            prefix[i] = (int)((long)prefix[i - 1] * arr[i] % MOD);
        }

        suffix[size - 1] = arr[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (int)((long)suffix[i + 1] * arr[i] % MOD);
        }

        int[][] res = new int[m][n];
        idx = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long left = (idx > 0) ? prefix[idx - 1] : 1;
                long right = (idx < size - 1) ? suffix[idx + 1] : 1;
                res[i][j] = (int)((left * right) % MOD);
                idx++;
            }
        }

        return res;  
    }
}