class StockSpanner {
    Stack<int []> st;
     int index;        // global index

    public StockSpanner() {
        st = new Stack<>();
        index = -1;
        
    }
    
    public int next(int price) {
        index++; // move to next day
    
        //we want previous greater
        
        while(!st.isEmpty() && st.peek()[0] <= price){
            st.pop();
        }
        int ans;
        if(st.isEmpty()){
            ans = index+1;
        }
        else{
            ans = index-st.peek()[1];
        }
          // Push current {price, index} into stack
        st.push(new int[]{price, index});
        return ans;
    }
   
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */