class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return ""; // if s smaller, not possible
        }

        int[] need = new int[128]; // store count of t
        for (char c : t.toCharArray()) {
            need[c]++; // mark how many times each char needed
        }

        int l = 0; // left pointer
        int r = 0; // right pointer
        int required = t.length(); // total chars needed
        int minLen = Integer.MAX_VALUE; // smallest window size
        int start = 0; // start index of answer

        while (r < s.length()) {
            char ch = s.charAt(r);

            // if this char is needed, reduce required
            if (need[ch] > 0) {
                required--;
            }
            need[ch]--; // use this char in window
            r++;

            // when we have all chars
            while (required == 0) {
                // check window size
                if (r - l < minLen) {
                    minLen = r - l;
                    start = l;
                }

                // try removing left char
                char leftChar = s.charAt(l);
                need[leftChar]++; // put it back
                if (need[leftChar] > 0) {
                    required++; // we are missing one char now
                }
                l++; // shrink window
            }
        }

        // return smallest substring
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + minLen);
    }
}
