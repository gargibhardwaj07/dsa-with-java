class Solution {
    public int searchInsert(int[] nums, int target) {
        //in this i am using lower bound approach
        int n = nums.length;
        int low = 0; //index 0
        int high = n-1; //index last
        int ans = n; //if not find ans ength index like if array of size 10 so ans 10
        while(low <= high){
            int mid = (low+high)/2;

            //agr mid bda h target se ex: target = 1 mid h hmara 3 to hum aab lower se mid tk dudhnge kyuki nd me nhi milega or bde elmnt se h aage
            //lowe bound = smallest index with (nums[i] >= target)
            if(nums[mid] >= target){ 
                ans = mid; //possibilty only
                high = mid-1;

            }
            else{
                low = mid+1;
            }

        }
 return ans;
        
    }
}