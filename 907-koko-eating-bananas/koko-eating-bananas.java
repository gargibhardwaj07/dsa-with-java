class Solution {
    public int minEatingSpeed(int[] piles, int h) {
      int left = 1;
        int right = getMax(piles);
        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2; // try speed mid

            if (canFinish(piles, (long) mid, h)) {
                answer = mid;       // mid is possible
                right = mid - 1;    // try smaller
            } else {
                left = mid + 1;     // need larger
            }
        }
        return answer;
    }

    // Use long for k and for time sum to avoid overflow
    private boolean canFinish(int[] piles, long k, int h) {
        long time = 0L;
        for (int i = 0; i < piles.length; i++) {
            int bananas = piles[i];
            // ceil(bananas / k) computed in long arithmetic
            time = time + (bananas + k - 1) / k;

            // early exit if already more than allowed hours
            if (time > h) {
                return false;
            }
        }
        return time <= h;
    }

    private int getMax(int[] piles) {
        int max = piles[0];
        for (int i = 1; i < piles.length; i++) {
            if (piles[i] > max) {
                max = piles[i];
            }
        }
        return max;
    }   
    }
