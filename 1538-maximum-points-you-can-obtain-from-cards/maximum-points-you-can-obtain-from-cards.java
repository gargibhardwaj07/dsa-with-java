class Solution {
    public int maxScore(int[] cardPoints, int k) {
    //step 1 only calculate left and then shrink left and calculate from back
    int lsum =0;
    int rsum =0;
    int maxsum = 0;
    int n = cardPoints.length;

    //in this loop we check from start to k and so our max is in left
    for(int i = 0; i<k;i++){
        lsum = lsum+cardPoints[i];
    }

      maxsum = lsum;
      rsum =0;

    //now we shrink left part and add one by one from back but for window k
    //k is size to hr br hum ek elmnt km krre h to --
    int rindex =n-1; // right sum ka index last elmnt he to h
    for(int i = k-1; i>=0; i--){
        //for shrink left
        lsum = lsum - cardPoints[i];
        rsum = rsum+cardPoints[rindex];
        rindex = rindex-1; //becuse hum left se ek km krre h right se lere h 
        maxsum = Math.max(maxsum, lsum+rsum); // we have to aslo add left and right sum 

    }
  return maxsum;
        
    }
}