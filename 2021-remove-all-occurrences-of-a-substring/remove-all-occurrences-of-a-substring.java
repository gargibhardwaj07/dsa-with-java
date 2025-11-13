class Solution {
    public String removeOccurrences(String s, String part) {
        // Keep removing the first occurrence of 'part' while it exists in s
        while (s.contains(part)) {
            int idx = s.indexOf(part);        // find leftmost occurrence
            s = s.substring(0, idx)           // prefix before that occurrence
                + s.substring(idx + part.length());  // suffix after the occurrence
        }
        return s;
    }
}