class Solution {
    public String convert(String s, int numRows) {
         // Special case: if only 1 row, zigzag = original string
        if (numRows == 1) return s;

        // Array of Strings, each row will collect its characters
        String[] rows = new String[numRows];

        // Initialize each row as empty string
        for (int i = 0; i < numRows; i++) {
            rows[i] = "";
        }

        int index = 0;           // current index in string s
        int length = s.length(); // total length of input
       
        while (index < length) {
            // Step 1: Go DOWN through all rows (0 → numRows-1)
            for (int row = 0; row < numRows && index < length; row++) {
                rows[row] += s.charAt(index);
                index++;
            }

            // Step 2: Go UP diagonally (numRows-2 → 1)
            for (int row = numRows - 2; row > 0 && index < length; row--) {
                rows[row] += s.charAt(index);
                index++;
            }
        }

        // Combine all rows into a single string
        String result = "";
        for (int i = 0; i < numRows; i++) {
            result += rows[i];
        }

        return result;
    
    }
}