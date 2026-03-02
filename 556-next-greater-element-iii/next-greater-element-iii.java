class Solution {
    public int nextGreaterElement(int n) {
               char[] arr = String.valueOf(n).toCharArray();
        
        int i = arr.length - 2;
        
        // Step 1: Find first decreasing digit
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        
        if (i < 0) return -1;
        
        // Step 2: Find just greater digit from right
        int j = arr.length - 1;
        while (arr[j] <= arr[i]) {
            j--;
        }
        
        // Step 3: Swap
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
        // Step 4: Reverse right part
        reverse(arr, i + 1);
        
        long result = Long.parseLong(new String(arr));
        
        return (result > Integer.MAX_VALUE) ? -1 : (int) result;
    }
    
    private void reverse(char[] arr, int start) {
        int end = arr.length - 1;
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}