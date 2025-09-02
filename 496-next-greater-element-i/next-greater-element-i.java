class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // map for store nums2 value and next greater
        HashMap<Integer, Integer> map = new HashMap<>();
        //stack to find next greater in nums2
        Stack<Integer> st = new Stack<>();
       
        for(int i = nums2.length-1; i>=0 ; i--){
            int curr = nums2[i];
            //now push those elmnt hwihc is smaller than curr ekmnt
            while(!st.isEmpty() && st.peek() <= curr){
                st.pop();
            }

            //for store next greater value
            int nextgreater;
            if(st.isEmpty()){
                nextgreater = -1;
            }else{
                nextgreater = st.peek();
            }
              // Store the result in the map
            map.put(curr, nextgreater);
            st.push(curr);
        }

        //step 2 now we take ans array of size array 1 and get next greater from array2
       int ans[] = new int[nums1.length];
       for(int i =0; i< nums1.length; i++){
        //here we get value of i becuse in above 2 have value for key so here it return value 
        ans[i] = map.get(nums1[i]);
       }

        return ans;
    }
}