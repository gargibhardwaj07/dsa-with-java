class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal-1);
    }
   
      int atMost(int[] nums, int goal){
             if(goal < 0){
            return 0;
        }
        int n = nums.length;
        int l =0;
        int r =0;
        int scount =0;
        int sum =0;

        //here we first find sum <= goal
        while(r <n){
            sum += nums[r];

            while(sum > goal){ //jn bhi sum bd gya goal s we shrink left
            sum -= nums[l];
            l++;
         }
            scount = scount + (r-l+1); // we know length jitni he h subraay
             r++;
        }
       
        return scount;
    }
}