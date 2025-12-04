class Solution {
    public int minCost(String colors, int[] neededTime) {
        int total =0;
        int n = colors.length();
        int i = 0;

        while(i<n){
            char ch = colors.charAt(i);
            int sum =0;
            int max = 0;

            while(i<n && colors.charAt(i) == ch){
                sum += neededTime[i];
                max = Math.max(max, neededTime[i]);
                i++;
            }

            total += sum - max;
        }
        return total;
    }
}