class Solution {
    public int characterReplacement(String s, int k) {
int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        int maxlen = 0;
        int maxFreq = 0; // keep track of the most frequent char in current window

        while (r < n) {
            char ch = s.charAt(r);

            // step 1: add current char frequency
            if (map.containsKey(ch)) {
                int oldcount = map.get(ch);
                map.put(ch, oldcount + 1);
            } else {
                map.put(ch, 1);
            }

            // update maxFreq (most frequent char count so far)
            maxFreq = Math.max(maxFreq, map.get(ch));

            // step 2: check if window is invalid
            // replacements needed = (window length - maxFreq)
            if ((r - l + 1) - maxFreq > k) {
                char lch = s.charAt(l);
                int count = map.get(lch);

                if (count == 1) {
                    map.remove(lch);
                } else {
                    map.put(lch, count - 1);
                }

                l++; // shrink window from left
            }

            // step 3: update max length
            int curlen = r - l + 1;
            maxlen = Math.max(curlen, maxlen);

            r++;
        }

        return maxlen;
    }
}