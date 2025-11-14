class Solution {

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxlen = 0;
        int n = s.length();

        while (right < n) {
            char curr = s.charAt(right);
            if (map.containsKey(curr)) {
                int lastidx = map.get(curr);

                if (lastidx + 1 > left) {
                    left = lastidx + 1;
                }
            }

            map.put(curr, right);

            int currlen = right - left + 1;
            maxlen = Math.max(currlen, maxlen);
            right++;
        }
        return maxlen;
    }
}
