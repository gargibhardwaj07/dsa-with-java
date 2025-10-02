class Solution {
    public int mostFrequentEven(int[] nums) {
          Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }
        }
        if (freqMap.isEmpty()) {
            return -1;
        }
        int ans = -1;
        int maxEven = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxEven || entry.getValue() == maxEven && entry.getKey() < ans) {
                maxEven = entry.getValue();
                ans = entry.getKey();
            }
        }
        return ans;
        
    }
}