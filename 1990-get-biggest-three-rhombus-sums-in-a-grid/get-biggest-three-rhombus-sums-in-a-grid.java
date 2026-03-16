class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                add(set, grid[r][c]);

                for (int k = 1; r - k >= 0 && r + k < m && c - k >= 0 && c + k < n; k++) {
                    int sum = 0;

                    int x = r - k, y = c;
                    for (int i = 0; i < k; i++) sum += grid[x + i][y + i];

                    x = r; y = c + k;
                    for (int i = 0; i < k; i++) sum += grid[x + i][y - i];

                    x = r + k; y = c;
                    for (int i = 0; i < k; i++) sum += grid[x - i][y - i];

                    x = r; y = c - k;
                    for (int i = 0; i < k; i++) sum += grid[x - i][y + i];

                    add(set, sum);
                }
            }
        }

        int size = set.size();
        int[] res = new int[size];
        int i = size - 1;
        for (int val : set) res[i--] = val;
        return res;
    }

    private void add(TreeSet<Integer> set, int val) {
        set.add(val);
        if (set.size() > 3) set.pollFirst();
    }
}