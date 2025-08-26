class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int bestBuy = prices[0]; // kyuki abhi 0 index pr usse phle koi value he nhi h to abhi yhi h
        for(int i =0;i<prices.length; i++){
            if(bestBuy < prices[i]){
                //we know profit is when we check if buy is less than sell so we 
                int profit = prices[i] - bestBuy;
                max = Math.max(max, profit);
                
            }
            // and hmara best buy hoga mimium in all array price
            bestBuy = Math.min(bestBuy, prices[i]);
        } 
        return max;
        
    }
}