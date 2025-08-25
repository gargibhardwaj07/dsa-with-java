class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length <= 2){
            return nums.length;
        }

        int i = 2; // beacuse we dont need to check 1 and 2 if same or not because 2 same elemnt allowed
        for(int j = 2; j<nums.length; j++){
            if(nums[j] != nums[i-2]){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
        
    }
}