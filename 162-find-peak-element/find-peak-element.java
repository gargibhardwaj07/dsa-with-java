class Solution {
    public int findPeakElement(int[] nums) {
//In this we always have -infinity at index = -1 and index n+1; 
//ex: [1,2,3] so 1 is always larger then its left and last index is always larger then its right
// and  for check peak we have to check in both sides so we take low = 1 and high = n-2;

      int n = nums.length;

      //some cases
     // CASE 1: If only one element, it's always a peak (both sides -∞).
        if (n == 1) return 0;

        // CASE 2: Check if first element is a peak
        // (compare only with right neighbor since left is -∞).
        if (nums[0] > nums[1]) return 0;

        // CASE 3: Check if last element is a peak
        // (compare only with left neighbor since right is -∞).
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        // Binary search between index 1 and n-2
        int low = 1, high = n - 2;


      while(low <= high){
        int mid = low+(high-low)/2;

        //Agr mid apne left se bda h or right s to whi peak h
        // CASE 4: If nums[mid] is greater than both neighbors → peak
        if(nums[mid] > nums[mid+1] && nums[mid] > nums[mid-1]){
            return mid;
        }

        //agr mid mid k phke elmnt s bda h to aab right dekhnge ky apeak h
        // CASE 5: If mid is greater than left but smaller than right,
        // then the peak lies in the right half.
        else if(nums[mid] > nums[mid-1]){
            low = mid+1;
        }
        else {
            high =mid-1;
        }
      } 
      return -1;
        
    }
}