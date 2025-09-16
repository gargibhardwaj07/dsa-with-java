class Solution {
    public int numberOfSubstrings(String s) {
        int lastidx[] ={-1, -1, -1};
        int ans =0;
        for(int i =0; i<s.length();i++){
            char ch = s.charAt(i);
            lastidx[ch - 'a'] = i; //like if ch is b, b-a is 1 so that means on idex its b 
        
        //if all seen we find minimum wndow
        if(lastidx[0] != -1 && lastidx[1] != -1 && lastidx[2] != -1){
            int minidx = Math.min(lastidx[0], Math.min(lastidx[1], lastidx[2]));
            ans += (minidx+1);
        }
        }
        return ans;
        
    }
}
// Step 2: Why last[ch - 'a'] = i?

// We want to remember where this character was last seen.

// Suppose i = 5 and ch = 'b':

// 'b' - 'a' = 1

// So it updates last[1] = 5
// (meaning: last time we saw 'b' was at index 5).