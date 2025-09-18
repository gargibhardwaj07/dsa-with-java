class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return func(nums, k) - func(nums, k-1);
      
    }
    int func(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int l =0;
        int r =0;
        int count =0;
        while(r<n){
            int curr = nums[r];
            if(map.containsKey(curr)){
                int oldc = map.get(curr);
                map.put(curr,oldc+1);
            }else{
             map.put(curr, 1);
            }
          
        
        while(map.size()> k){
            int lcurr = nums[l];
            int lcount = map.get(lcurr);
            if(lcount == 1){
                map.remove(lcurr);
            }else{
                map.put(lcurr, lcount-1);
            }
            l++;
        }
        count += r-l+1;
        r++;
        }
        return count;
    }
    
}