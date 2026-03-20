class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
    int sumA = 0, sumB = 0;
        for (int a : aliceSizes) sumA += a;
        for (int b : bobSizes) sumB += b;

        int diff = (sumA - sumB) / 2;

        Set<Integer> set = new HashSet<>();
        for (int a : aliceSizes) set.add(a);

        for (int b : bobSizes) {
            if (set.contains(b + diff)) {
                return new int[]{b + diff, b};
            }
        }

        return new int[]{};     
    }
}