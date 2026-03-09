class Solution {

    static final int MOD = 1_000_000_007;
    int[][][] dp;

    public int numberOfStableArrays(int zero, int one, int limit) {

        dp = new int[zero + 1][one + 1][2];

        for (int i = 0; i <= zero; i++)
            for (int j = 0; j <= one; j++)
                dp[i][j][0] = dp[i][j][1] = -1;

        long ans = dfs(zero, one, 0, limit) + dfs(zero, one, 1, limit);

        return (int)(ans % MOD);
    }

    private int dfs(int z, int o, int last, int limit) {

        if (z == 0 && o == 0) return 1;

        if (dp[z][o][last] != -1) return dp[z][o][last];

        long ways = 0;

        if (last == 0) { // last placed 0 → must place 1 next
            for (int i = 1; i <= limit && i <= o; i++) {
                ways += dfs(z, o - i, 1, limit);
            }
        } else { // last placed 1 → must place 0 next
            for (int i = 1; i <= limit && i <= z; i++) {
                ways += dfs(z - i, o, 0, limit);
            }
        }

        return dp[z][o][last] = (int)(ways % MOD);
    }
}