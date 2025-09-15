class Solution {
    public int longestOnes(int[] nums, int k) {
        int l =0;
        int r =0;
        int zerocount= 0;
        int n = nums.length;
        int maxlength =0;
        //agr nums == 0 increse count
        while(r < n){
            if(nums[r] == 0){
            zerocount++;
            }
               //agr zer0 ka cout k se bd jata h to hum left ko move krenge
            if(zerocount > k){
            if(nums[l] == 0){
                zerocount--; //kyuki hum l ko move he isliye krre h jisse zise kamho
               
            }
             l++;
            }
            //agr zero ka size eqal ya kam h hum total len nikal lenge
            if( zerocount <= k){
                int currlength = r-l+1;
                maxlength = Math.max(maxlength, currlength);
            }
            
            r++;
        }
        return maxlength;
    }
}