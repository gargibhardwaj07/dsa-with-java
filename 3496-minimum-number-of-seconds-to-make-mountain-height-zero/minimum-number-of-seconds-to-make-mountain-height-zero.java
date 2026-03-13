class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
              PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(a[0],b[0]));
        
        for(int t : workerTimes){
            pq.add(new long[]{t, t, 1});
        }
        
        long ans = 0;
        
        for(int i = 0; i < mountainHeight; i++){
            long[] cur = pq.poll();
            long time = cur[0];
            long base = cur[1];
            long k = cur[2];
            
            ans = Math.max(ans, time);
            
            pq.add(new long[]{time + base * (k + 1), base, k + 1});
        }
        
        return ans; 
    }
}