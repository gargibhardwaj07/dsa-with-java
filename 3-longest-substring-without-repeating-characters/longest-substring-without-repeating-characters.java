class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxlen = 0;
        int n = s.length();

        while(right < n ){
            char curr = s.charAt(right); 
            //put elmnt ut we check before that if tgat elmnt exist or not
            if (map.containsKey(curr)){
                //find index of that elemnt
                int lastidx = map.get(curr);
                //now we update left ek elmnt aage us index s jispe vo phle se h
                //pr sirf aage check krenge backward ni
                if(lastidx+1 > left){
                    left = lastidx+1;
                }
            }
            // now update index with new char
                map.put(curr,right);

                //now get currlenth
                int currlen = right - left+1;
                maxlen = Math.max(currlen, maxlen);
                right++;
        }
        return maxlen;
    }
}