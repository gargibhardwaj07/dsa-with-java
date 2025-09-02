class Solution {
    public int[] nextGreaterElements(int[] nums) {
        //in this we take 2n because we have to check circular so we use approach like we copy same elmnt and then check for next greater but we want ans we use n%2 
        int n = nums.length;
        int ans[] = new int[n];
        Stack<Integer> s = new Stack<>();

        for(int i = 2*n-1; i>=0; i--){
            int curr = nums[i%n];
            while(!s.isEmpty() && s.peek() <= curr){
                s.pop();
            }
            if(s.isEmpty()){
                ans[i%n] = -1;
            }else{
                ans[i%n] = s.peek();
            }
            s.push(curr);
        } 
        return ans;
    }
}

//Code explain we take 2n [1,2,1] becuse we check like (1,2,1)1,2,1 like this but for correct mapping we use i%n so we give ans in our correct index