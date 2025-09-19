class Solution {
    public int findContentChildren(int[] g, int[] s) {
         Arrays.sort(g);
        Arrays.sort(s);
        // int n = g.length; //greedy child
        // int m = s.length; //cokkie size
        int cokkie =0;
        int child =0;
       

        //now we check in size array that 
        while(cokkie < s.length && child < g.length){
            if(s[cokkie] >= g[child]){
                child = child+1;
                cokkie =cokkie+1;
            }else{
                cokkie = cokkie+1;
            }
        }
        return child;
    }
}