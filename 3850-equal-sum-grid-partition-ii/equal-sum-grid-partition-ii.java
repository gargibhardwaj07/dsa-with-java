class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for (int[] row : grid)
            for (int v : row) total += v;

        // Removability helper:
        // Can we remove a cell of value `need` from a section without disconnecting it?
        //
        // A section formed by a horizontal cut has dimensions (rows x n).
        // A section formed by a vertical cut has dimensions (m x cols).
        //
        // Connectivity rule:
        //   If the section is a single strip (1 row OR 1 col):
        //     only the two endpoint cells are removable (and only if strip length > 1)
        //   Otherwise (>=2 rows AND >=2 cols):
        //     any cell is removable
        //
        // For horizontal cuts: sectionRows x n
        //   single strip iff sectionRows==1  (then strip endpoints are col 0 and col n-1)
        //   multi        iff sectionRows>=2 AND n>=2  (always true since n>=1 and if n==1
        //                then it's a single-col strip → only row endpoints removable)
        //
        // Simpler unified rule for a sectionRows x n section:
        //   if sectionRows==1: endpoints are (0,0) and (0,n-1)
        //   if n==1:           endpoints are (0,0) and (sectionRows-1,0)
        //   else:              any cell (freq map lookup)
        //
        // We store endpoint values directly; for freq map we use a HashMap.

        // ── Horizontal cuts ──────────────────────────────────────────────
        {
            long topSum = 0;
            java.util.HashMap<Long, Integer> topFreq = new java.util.HashMap<>();

            for (int i = 0; i < m - 1; i++) {
                // Add row i to top section
                for (int c = 0; c < n; c++) {
                    topSum += grid[i][c];
                    topFreq.merge((long) grid[i][c], 1, Integer::sum);
                }
                long botSum = total - topSum;
                int topRows = i + 1;
                int botRows = m - 1 - i;

                if (topSum == botSum) return true;

                long diff = topSum - botSum;

                if (diff > 0) {
                    // Need to remove value `diff` from TOP section (topRows x n)
                    long need = diff;
                    if (topRows == 1) {
                        // single row: only col 0 or col n-1
                        if (n > 1 && (grid[0][0] == need || grid[0][n - 1] == need))
                            return true;
                    } else if (n == 1) {
                        // single col: only row 0 or row topRows-1 (= row i)
                        if (grid[0][0] == need || grid[i][0] == need)
                            return true;
                    } else {
                        // multi-row multi-col: any cell
                        if (topFreq.containsKey(need)) return true;
                    }
                } else {
                    // Need to remove value `-diff` from BOT section (botRows x n)
                    long need = -diff;
                    if (botRows == 1) {
                        // single row (row i+1): only col 0 or col n-1
                        if (n > 1 && (grid[i + 1][0] == need || grid[i + 1][n - 1] == need))
                            return true;
                    } else if (n == 1) {
                        // single col: only row i+1 or row m-1
                        if (grid[i + 1][0] == need || grid[m - 1][0] == need)
                            return true;
                    } else {
                        // multi-row multi-col: need botFreq → backward pass
                    }
                }
            }

            // Backward pass: multi-row multi-col bot where botSum > topSum
            long botSum2 = 0;
            java.util.HashMap<Long, Integer> botFreq = new java.util.HashMap<>();
            for (int i = m - 1; i > 0; i--) {
                for (int c = 0; c < n; c++) {
                    botSum2 += grid[i][c];
                    botFreq.merge((long) grid[i][c], 1, Integer::sum);
                }
                int botRows = m - i;
                // Only handle multi-row multi-col case here
                if (botRows < 2 || n < 2) continue;

                long topSum2 = total - botSum2;
                long diff = topSum2 - botSum2;
                if (diff < 0) {
                    long need = -diff;
                    if (botFreq.containsKey(need)) return true;
                }
            }
        }

        // ── Vertical cuts ────────────────────────────────────────────────
        {
            long leftSum = 0;
            java.util.HashMap<Long, Integer> leftFreq = new java.util.HashMap<>();

            for (int j = 0; j < n - 1; j++) {
                // Add col j to left section
                for (int r = 0; r < m; r++) {
                    leftSum += grid[r][j];
                    leftFreq.merge((long) grid[r][j], 1, Integer::sum);
                }
                long rightSum = total - leftSum;
                int leftCols  = j + 1;
                int rightCols = n - 1 - j;

                if (leftSum == rightSum) return true;

                long diff = leftSum - rightSum;

                if (diff > 0) {
                    // Need to remove value `diff` from LEFT section (m x leftCols)
                    long need = diff;
                    if (m == 1) {
                        // single row: only col 0 or col j (rightmost col of left section)
                        if (leftCols > 1 && (grid[0][0] == need || grid[0][j] == need))
                            return true;
                        // leftCols==1: removing empties the section
                    } else if (leftCols == 1) {
                        // single col (col 0): only row 0 or row m-1
                        if (grid[0][0] == need || grid[m - 1][0] == need)
                            return true;
                    } else {
                        // multi-row multi-col: any cell
                        if (leftFreq.containsKey(need)) return true;
                    }
                } else {
                    // Need to remove value `-diff` from RIGHT section (m x rightCols)
                    long need = -diff;
                    if (m == 1) {
                        // single row: only col j+1 or col n-1
                        if (rightCols > 1 && (grid[0][j + 1] == need || grid[0][n - 1] == need))
                            return true;
                        // rightCols==1: removing empties the section
                    } else if (rightCols == 1) {
                        // single col (col j+1): only row 0 or row m-1
                        if (grid[0][j + 1] == need || grid[m - 1][j + 1] == need)
                            return true;
                    } else {
                        // multi-row multi-col: backward pass
                    }
                }
            }

            // Backward pass: multi-row multi-col right where rightSum > leftSum
            long rightSum2 = 0;
            java.util.HashMap<Long, Integer> rightFreq = new java.util.HashMap<>();
            for (int j = n - 1; j > 0; j--) {
                for (int r = 0; r < m; r++) {
                    rightSum2 += grid[r][j];
                    rightFreq.merge((long) grid[r][j], 1, Integer::sum);
                }
                int rightCols = n - j;
                // Only handle multi-row multi-col case here
                if (m < 2 || rightCols < 2) continue;

                long leftSum2 = total - rightSum2;
                long diff = leftSum2 - rightSum2;
                if (diff < 0) {
                    long need = -diff;
                    if (rightFreq.containsKey(need)) return true;
                }
            }
        }

        return false;
    }
}