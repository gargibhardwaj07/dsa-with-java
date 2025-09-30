class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map =new HashMap<>();
        for(int i =0;i<n;i++){
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        //traverse on map key by advanced foreach loop
        for(Integer Key : map.keySet()){
            if(map.get(Key) > n/3){
               ans.add(Key);
            }
        }
        return ans;
    }
}