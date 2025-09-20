class Solution {
    public boolean canJump(int[] nums) {
        int maxlen =0; //idx 0 pr abhi 0 he h max 
        for(int i =0;i < nums.length; i++){
            //agr i bda h mtlb index max len se to mtlb hum nhi phoche last tk
            if(i > maxlen){
                return false;
            }
            //updat emaxlen
            maxlen = Math.max(maxlen, nums[i]+i);
            //agr maxlen ki value bdi hogye to mtlb hum index se jyda phoch gye
            if(maxlen > nums.length){
                return true;
            }
        }
        return true;
        
    }
}