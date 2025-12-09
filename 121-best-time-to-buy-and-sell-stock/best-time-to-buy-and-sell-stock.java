class Solution {
    public int maxProfit(int[] prices) {
     int buyPrice = Integer.MAX_VALUE; // because we have to buy in less so we compare later
        int maxprofit =0;
        for(int i =0; i<prices.length;i++){
            if(buyPrice < prices[i]){ //profit 
                int profit = prices[i]- buyPrice;
                maxprofit = Math.max(maxprofit, profit);
            }else{
                buyPrice = prices[i]; //agr profit n ho to buy krlo 
            }
        }
        return maxprofit;
    }
}