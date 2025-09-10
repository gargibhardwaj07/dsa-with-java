class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];  // nearest smaller to left
        int[] right = new int[n]; // nearest smaller to right
        Stack<Integer> st = new Stack<>();

        // 1. Calculate nearest smaller to left
        //for left forward loop
        for(int i = 0; i< n; i++){
            int curr = heights[i];
            while(!st.isEmpty() && heights[st.peek()] >= curr){
             st.pop();
            }
            if(st.isEmpty()){
                left[i] = -1;
            }else{
                left[i] = st.peek();
            }
            st.push(i); // push index
        }
   
       // clear stack for reuse
        st.clear();

     
        // 2. Calculate nearest smaller to right
        //here backward loop
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                right[i] = n; // no smaller to right
            } else {
                right[i] = st.peek();
            }
            st.push(i); // push index
        }

    //calclulate leargest rectangle
    int ans = 0;
    for(int i = 0; i< n ; i++){
        int width = right[i] - left[i]-1;
        int currarea = heights[i] * width;
        ans = Math.max(ans, currarea);
    }
    return ans;
    }
}