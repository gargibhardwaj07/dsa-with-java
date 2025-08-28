class Solution {
    //int lowerbound for ist occurence
   int lowerbound(int nums[], int target){
    int n = nums.length;
    int low =0;
    int high = n-1;
    int ans = n;
    while(low <= high){
        int mid = low+(high-low)/2;
        //lower bound conditionn
        if(nums[mid] >= target){
            ans = mid;
            high = mid-1;
        }
        else{
            low = mid+1;
        }
    }
        return ans;
    
   }

       //int lowerbound for ist occurence
   int upperbound(int nums[], int target){
    int n = nums.length;
    int low =0;
    int high = n-1;
    int ans = n;
    while(low <= high){
        int mid = low+(high-low)/2;
        //lower bound conditionn
        if(nums[mid] > target){
            ans = mid;
            high = mid-1;
        }
        else{
            low = mid+1;
        }
    }
        return ans;
    
   }

    public int[] searchRange(int[] nums, int target) {
        int lb = lowerbound(nums, target);

        // if target is not present
       // lowerbound(10) → no element ≥ 10, so it sets ans = n = 4. we take ans = n above 
        if(lb == nums.length || nums[lb] != target){
            return new int[]{-1, -1};
        }

       int ub = upperbound(nums, target);
       // Example 1: target exists
       //nums = [5, 7, 7, 8, 8, 10], target = 8
       // lb = lowerbound(8) → index 3 (first 8).
       // ub = upperbound(8) → index 5 (first element > 8, which is 10).
       // So range = {3, 5 - 1} = {3, 4}. ✅ Correct.
        return new int[]{lb, ub - 1};
    }
}