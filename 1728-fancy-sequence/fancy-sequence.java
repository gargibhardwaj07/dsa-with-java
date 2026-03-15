class Fancy {

   static final int MOD = 1_000_000_007;
    List<Long> values;
    long mul = 1;
    long add = 0;

    public Fancy() {
        values = new ArrayList<>();
    }

    public void append(int val) {
        long inv = modInverse(mul);
        long stored = ((val - add) % MOD + MOD) % MOD;
        stored = (stored * inv) % MOD;
        values.add(stored);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= values.size()) return -1;
        long val = values.get(idx);
        return (int)((val * mul + add) % MOD);
    }

    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long a, long b) {
        long res = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
}
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */