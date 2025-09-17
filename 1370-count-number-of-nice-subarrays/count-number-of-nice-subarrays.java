class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        //here we want odd sum so we make if its odd no = 1 and even =0; 
          for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                nums[i] = 0;
            } else {
                nums[i] = 1;
            }
        }

        return atMost(nums, k) - atMost(nums, k-1);
    }
   
      int atMost(int[] nums, int k){
             if(k < 0){
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

            while(sum > k){ //jn bhi sum bd gya goal s we shrink left
            sum -= nums[l];
            l++;
         }
            scount = scount + (r-l+1); // we know length jitni he h subraay
             r++;
        }
       
        return scount;
        
    }
}