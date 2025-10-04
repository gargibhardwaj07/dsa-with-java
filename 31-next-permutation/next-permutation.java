class Solution {
    public void nextPermutation(int[] nums) {
         int n = nums.length;
        int i = -1;

        // Step 1: Find the first index i from the end where nums[i] < nums[i + 1]
        for (int k = n - 2; k >= 0; k--) {
            if (nums[k] < nums[k + 1]) {
                i = k;
                break;
            }
        }

        // Step 2: If such an index exists, find j (rightmost element greater than nums[i])
        if (i != -1) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }

        // Step 3: Reverse the subarray from i + 1 to n - 1
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}