class Solution {
    public int firstUniqChar(String s) {
        //we take freq array to store a to z character freuncy 
        int freq[] = new int[26];
        //here we take int so we return index we can also solve by store chaarcter
        Queue<Integer> q = new LinkedList<>();

        //now we traverse on string and add elemnt in queue and freq++;
        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            q.add(i);
          //  freq[ch - 'a']++;
//This result (0 for 'a', 1 for 'b', etc.) is then used as an index to access an element within the arr array.
            freq[ch - 'a']++; 
        }

//now we check if koi elemnt h jiski freq 1 se jyda h usko pop kr denge
//here fre[q.peek()- 'a'] means index 0 then index ++
        while(!q.isEmpty() && freq[s.charAt(q.peek()) -'a'] > 1){
            q.remove();

        }
        //agr sb remove ho gye to mtlb koi bhi ni h uniue -1
        if(q.isEmpty()){
         return -1;
        }
        else{
          return q.peek();
        }
    }
}