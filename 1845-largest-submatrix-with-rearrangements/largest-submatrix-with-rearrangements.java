class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                height[j] = matrix[i][j] == 0 ? 0 : height[j] + 1;
            }

            int[] sorted = height.clone();
            Arrays.sort(sorted);

            for (int j = 0; j < n; j++) {
                int h = sorted[j];
                int width = n - j;
                max = Math.max(max, h * width);
            }
        }

        return max;
    }
}