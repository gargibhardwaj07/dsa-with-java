class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans =0;
        int l =0;
        int r =0;
        int minlen = Integer.MAX_VALUE;

        while(r<n){
            ans = ans + nums[r];

            while(ans >= target){
                minlen = Math.min(minlen, r-l+1);
                ans = ans- nums[l];
                l++;
            }
        
            r++;
        }

        if (minlen == Integer.MAX_VALUE) {
            return 0; // means no subarray found
        } else {
            return minlen; // minimum length found
        }
    }
}