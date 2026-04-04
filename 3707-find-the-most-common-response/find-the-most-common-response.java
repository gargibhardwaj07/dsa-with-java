

class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> freq = new HashMap<>();

        for (List<String> day : responses) {
            Set<String> set = new HashSet<>(day);
            for (String s : set) {
                freq.put(s, freq.getOrDefault(s, 0) + 1);
            }
        }

        String ans = "";
        int max = 0;

        for (String key : freq.keySet()) {
            int count = freq.get(key);
            if (count > max || (count == max && key.compareTo(ans) < 0)) {
                max = count;
                ans = key;
            }
        }

        return ans;
    }
}