class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int low =0;
        int high = n-1;
        int ans = Integer.MAX_VALUE;
        while(low<= high){
            int mid = low+(high-low)/2;
           //now check for sorted part
           //if nums of low is smaller than mid that means leftpart sorted
           //and we know in sorted part 1st elemnt is always smaller
           //after we picked mimum from left we now check for smaller in next half by make low mid+1;
           if(nums[low] <= nums[mid]){
            ans = Math.min(ans, nums[low]);
            low = mid+1;

           }
           //if above if not correct that means right half sorted and right half sort means mid k bd k sare elemnt ussse bde h to mid chota h to mid is smaller
           else{
            ans = Math.min(ans, nums[mid]);
            high = mid-1;

              
           }
        }
        return ans;
        
    }
}