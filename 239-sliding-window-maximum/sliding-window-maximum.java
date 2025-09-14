class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //Step 1 take array of size n-k+1, becuse we knoe=w we want that size of array
        int ans[] = new int[n-k+1]; 
        Deque<Integer> dq = new LinkedList<>();

        //res array idx
        int idx =0;

        //now in for loop we have to perform 4 step 1 to check if we  are in correct window like when we check from (3,-1,-3) is 1 to not exist so we pop from first kyuki hum hr br window aage le jare h to phle se remove krenge

        for(int i = 0; i<nums.length; i++){
            int curr = nums[i];

            if(!dq.isEmpty() && dq.peekFirst() <= i-k){
                dq.pollFirst();
            }
            //step 2 now from back we check in deque if any elmnt small then current we remove from back 
        while(!dq.isEmpty() && nums[dq.peekLast()] < curr){
            dq.pollLast();
        }

        //step 3 push curr elmnt index if greater
        dq.addLast(i);

        //step 4 check if window is vali add ans in ans array
        if(i >= k-1){
            ans[idx++] = nums[dq.peekFirst()]; //max is always on front
        }
        }
        return ans;
    }
}

// \U0001f511 Beginner Summary

// Deque stores indexes, not values.

// nums[dq.peekFirst()] = maximum of current window.

// 4 Steps for each element:

//1- Remove elements outside the window (i - k).

//2- Remove smaller elements from back.

//3- Add current element index.

//4- If window is complete (i >= k-1), add maximum to answer.

// Result array size = n - k + 1 because that’s how many windows we can form.