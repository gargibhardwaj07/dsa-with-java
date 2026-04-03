class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        final int NEG_INF = Integer.MIN_VALUE / 2;

        // dp[i][j][k] = max coins at (i,j) with k neutralizations remaining
        int[][][] dp = new int[m][n][3];
        for (int[][] a : dp)
            for (int[] b : a)
                java.util.Arrays.fill(b, NEG_INF);

        // Helper: apply arriving at cell with value `val`, given best[k] from predecessor
        // Returns updated dp values for this cell (does NOT mutate in place mid-loop)

        // Base: (0,0)
        int v00 = coins[0][0];
        dp[0][0][2] = v00;
        dp[0][0][1] = (v00 < 0) ? 0 : v00;          // neutralize if negative
        dp[0][0][0] = (v00 < 0) ? 0 : v00;           // neutralize if negative (use 2nd slot too)
        // with 2 neutralizations available, using one gives k=1; using none gives k=2
        // Re-derive carefully:
        // k=2 remaining after (0,0): didn't neutralize → dp[0][0][2] = v00
        // k=1 remaining after (0,0): neutralized one   → dp[0][0][1] = (v00<0 ? 0 : v00)
        //   but if v00>=0, neutralizing wastes a slot; still valid, value same
        // k=0 remaining after (0,0): neutralized two   → dp[0][0][0] = (v00<0 ? 0 : v00)
        //   wasting 2 slots if not negative, but still valid
        // Simplify: at (0,0), best with exactly used_neutralizations used:
        //   used=0: dp[0][0][2] = v00
        //   used=1: dp[0][0][1] = v00 < 0 ? 0 : v00
        //   used=2: dp[0][0][0] = v00 < 0 ? 0 : v00  (same, can't double-neutralize one cell)
        // Already set correctly above.

        // Fill using a fresh "best from predecessors" approach per cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int val = coins[i][j];
                // best[k] = best coins arriving at (i,j) before applying val, with k neutralizations left
                int[] best = new int[3];
                java.util.Arrays.fill(best, NEG_INF);

                // From top
                if (i > 0) {
                    for (int k = 0; k <= 2; k++) {
                        if (dp[i-1][j][k] != NEG_INF)
                            best[k] = Math.max(best[k], dp[i-1][j][k]);
                    }
                }
                // From left
                if (j > 0) {
                    for (int k = 0; k <= 2; k++) {
                        if (dp[i][j-1][k] != NEG_INF)
                            best[k] = Math.max(best[k], dp[i][j-1][k]);
                    }
                }

                // Now apply val to each k level independently — no cross-contamination
                for (int k = 0; k <= 2; k++) {
                    if (best[k] == NEG_INF) continue;
                    // Option 1: don't neutralize
                    dp[i][j][k] = Math.max(dp[i][j][k], best[k] + val);
                    // Option 2: neutralize (only if val < 0 and k > 0)
                    if (val < 0 && k > 0)
                        dp[i][j][k-1] = Math.max(dp[i][j][k-1], best[k]);
                }
            }
        }

        return Math.max(dp[m-1][n-1][0], Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}

class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] coins = {{-7,12,12,13},{-6,19,19,-6},{9,-2,-10,16},{-4,14,-10,-9}};
        System.out.println(sol.maximumAmount(coins)); // expected 60
    }
}