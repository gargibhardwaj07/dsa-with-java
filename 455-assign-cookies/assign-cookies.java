class Solution {
    public int findContentChildren(int[] g, int[] s) {
      Arrays.sort(g); // sort children greed
        Arrays.sort(s); // sort cookie sizes

        int child = 0;  // pointer for children
        int cookie = 0; // pointer for cookies

        // loop until one list finishes
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                // cookie can satisfy child → assign
                child++;
                cookie++;
            } else {
                // cookie too small → try next bigger cookie
                cookie++;
            }
        }

        return child; // number of satisfied children , ye hmra child ki array ka h child hmesa use index pr hoga jitne satisfy hue h
    }
}