class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int t = 0; // pointer for trainer
        int p = 0; //pointer for player

        while(t < trainers.length && p < players.length){
            //agr player ki capabitly kam ya equal h trainer s to trainer assihn hoga
            if(players[p] <= trainers[t] ){
                p = p+1;
                t = t+1;
                }else{
                    //agr nhi match hore we check for other trainer
                    t = t+1;
                }

        }
        return p;
        
    }
}