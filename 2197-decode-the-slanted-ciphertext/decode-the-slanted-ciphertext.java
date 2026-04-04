class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (rows == 1) return encodedText;

        int cols = n / rows;
        StringBuilder sb = new StringBuilder();

        for (int c = 0; c < cols; c++) {
            int i = 0, j = c;
            while (i < rows && j < cols) {
                sb.append(encodedText.charAt(i * cols + j));
                i++;
                j++;
            }
        }

        // remove trailing spaces
        int end = sb.length();
        while (end > 0 && sb.charAt(end - 1) == ' ') end--;

        return sb.substring(0, end);
    }
}