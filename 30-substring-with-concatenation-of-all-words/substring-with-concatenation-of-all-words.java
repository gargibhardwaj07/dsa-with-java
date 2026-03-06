class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int windowSize = wordLen * wordCount;

        Map<String, Integer> map = new HashMap<>();

        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for(int i = 0; i < wordLen; i++){

            int left = i;
            int count = 0;

            Map<String, Integer> seen = new HashMap<>();

            for(int j = i; j + wordLen <= s.length(); j += wordLen){

                String word = s.substring(j, j + wordLen);

                if(map.containsKey(word)){

                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    while(seen.get(word) > map.get(word)){

                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    if(count == wordCount){
                        result.add(left);
                    }

                } else {
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }
}