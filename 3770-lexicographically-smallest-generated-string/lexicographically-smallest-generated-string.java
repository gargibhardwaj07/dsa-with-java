class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int L = n + m - 1;

        char[] res = new char[L];
        Arrays.fill(res, '?');

        boolean[] locked = new boolean[L];

        // Step 1: enforce all 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int idx = i + j;
                    if (res[idx] == '?' || res[idx] == str2.charAt(j)) {
                        res[idx] = str2.charAt(j);
                        locked[idx] = true;
                    } else {
                        return "";
                    }
                }
            }
        }

        // Step 2: fill remaining with 'a'
        for (int i = 0; i < L; i++) {
            if (res[i] == '?') res[i] = 'a';
        }

        // Step 3: handle 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    boolean changed = false;

                    for (int j = m - 1; j >= 0; j--) {
                        int idx = i + j;
                        if (locked[idx]) continue;

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c != str2.charAt(j)) {
                                res[idx] = c;
                                changed = true;
                                break;
                            }
                        }

                        if (changed) break;
                    }

                    if (!changed) return "";
                }
            }
        }

        return new String(res);
    }
}