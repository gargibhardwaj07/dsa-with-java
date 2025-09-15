class Solution {
    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = fruits.length;
        int k =2; //only 2 basket
        int l =0;
        int r = 0;
        int maxlen = 0;

        while(r<n){
            int currFruit = fruits[r]; //array m cur rfruit right wala h right move krra h

            //step 1 to add curr fruit in map , if already exist make count ++
            if(map.containsKey(currFruit)){
                int oldcount = map.get(currFruit);
                map.put(currFruit, oldcount+1);
            }else{
                map.put(currFruit, 1);
            }

            //step 2 if 2 se jyda type h map me to left ko shrink kr denge or jo bhi left fruit ki value h kam kr denge jb 1 ho jyge remove kr denge
        if(map.size() > 2){
            int leftFruit = fruits[l];
            int count = map.get(leftFruit);
            if(count == 1){
            map.remove(leftFruit);
        }else{
            map.put(leftFruit, count-1 );
        }
            l++;
        }

            //step 3 find curr len and then compare with maxlen
            int currlen = r-l+1;
            maxlen = Math.max(currlen, maxlen);

            r++;
           
        }
        return maxlen;
        
    }
}