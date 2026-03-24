class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;

        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            long bucket = num < 0 ? (num + 1) / w - 1 : num / w;

            if (map.containsKey(bucket)) return true;
            if (map.containsKey(bucket - 1) && Math.abs(num - map.get(bucket - 1)) < w) return true;
            if (map.containsKey(bucket + 1) && Math.abs(num - map.get(bucket + 1)) < w) return true;

            map.put(bucket, num);

            if (i >= k) {
                long old = nums[i - k];
                long oldBucket = old < 0 ? (old + 1) / w - 1 : old / w;
                map.remove(oldBucket);
            }
        }

        return false;
    }
}