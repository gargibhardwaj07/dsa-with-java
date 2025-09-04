class Solution {
    public String reverseVowels(String s) {
      char[] ch = s.toCharArray();
        int start = 0;
        int end = ch.length - 1;

        while (start < end) {
            // move start forward until it points to a vowel
            while (start < end && !isVowel(ch[start])) {
                start++;
            }
            // move end backward until it points to a vowel
            while (start < end && !isVowel(ch[end])) {
                end--;
            }

            // swap vowels
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;

            start++;
            end--;
        }

        return new String(ch);
       
        
    }

     private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}