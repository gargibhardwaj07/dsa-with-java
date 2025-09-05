class Solution {
    public int longestConsecutive(int[] nums) {

        int n = nums.length;
        if(n==0){
            return 0;
        }

        //take set to store array elemnts
        Set<Integer> set = new HashSet<>();

        //now put all array elmnt in set
        for(int i = 0; i<n; i++){
            set.add(nums[i]);
        }
        int longest = 1;
//now we check in set that if current elmnt-1 like for 1 is 0 avaibale to make sequnce startimg point by using iteratore becuse we take run loop on set
        for(int it : set){
    
            //agr nhi milta h usse kam to whi starting h 
            if(!set.contains(it-1)){
                int count = 1;
                int curr = it;

//and jb tk set apne se jyda elmnt milege hum count bda denge or elmnt ko bhi aage kr denge
            while(set.contains(curr+1)){
                curr = curr+1;
                count = count+1;
            }
            longest = Math.max(longest, count);
            }
        }
return longest;
           
        
    }
}