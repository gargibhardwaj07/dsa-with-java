class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n-1;
       while(low <= high){
        int mid = low+(high-low)/2;
        if(nums[mid] == target){
            return mid;
        }
        //now check which part is sorted left or right
        //agr elemnt chote h mid se left m to sorted h vrna right sorted h
       if(nums[low] <= nums[mid]){
        if(nums[low] <= target && target <= nums[mid]){
           high = mid-1;
        }else{
            low = mid+1;
        }

       }
       

       //agr upr wale condition true nhi h to mtlb right half sorted h
       else{
        //agr target mid se bda h or high s chota h to high peeche aa jyga
        if(nums[mid] <= target && target <= nums[high]){
            low = mid+1;

        }
        else{
            high = mid-1;
        }
       }
       }

        return -1;
    }
}