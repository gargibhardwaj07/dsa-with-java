class Solution {
    public String getHappyString(int n, int k) {
       List<String> list = new ArrayList<>();
        backtrack(n, "", list);
        if (k > list.size()) return "";
        return list.get(k - 1);
    }

    private void backtrack(int n, String cur, List<String> list) {
        if (cur.length() == n) {
            list.add(cur);
            return;
        }

        for (char c : new char[]{'a','b','c'}) {
            if (cur.length() > 0 && cur.charAt(cur.length() - 1) == c) continue;
            backtrack(n, cur + c, list);
        }  
    }
}