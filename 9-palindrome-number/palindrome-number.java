class Solution {
    public boolean isPalindrome(int x) {
      // Negative numbers are not palindrome
        if (x < 0) {
            return false;
        }

        int original = x;   // store the original number
        int reversed = 0;   // will store the reversed number

        while (x > 0) {
            int digit = x % 10;          // get last digit
            reversed = reversed * 10 + digit; // add it to reversed number
            x = x / 10;                  // remove last digit
        }

        // check if reversed number is same as original
        if (reversed == original) {
            return true;
        } else {
            return false;
        }
        
    }
}