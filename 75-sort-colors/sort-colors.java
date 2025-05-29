class Solution {
    public void sortColors(int[] nums) {
       int n = nums.length;
       int low =0;
       int mid =0;
       int high = n-1;
       while(mid <= high){
        if(nums[mid] == 0){
            //swap 0 ko phle le jana h low pr 
            int temp = nums[mid];
            nums[mid] = nums[low];
            nums[low] = temp;
            mid++;
            low++;
        }
        else if(nums[mid] == 1){
            mid++;
        }
        else{
            //swap agr 2 h to last me high pr le jynge mid s
            int temp = nums[mid];
            nums[mid] = nums[high];
            nums[high] = temp;
            high--;
        }
       }
    }
}