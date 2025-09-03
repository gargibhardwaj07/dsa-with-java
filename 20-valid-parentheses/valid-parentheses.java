class Solution {
    public boolean isValid(String s) {
           Stack<Character> st = new Stack<>();
      for(int i =0;i<s.length();i++){
          char ch = s.charAt(i);
          //opening condition
          if(ch == '(' | ch == '{' || ch =='[' ){
              st.push(ch);
          }
          else{
              // closing
              if(st.isEmpty()){
                  return false;
              }
              if((st.peek()=='(' && ch == ')')
              ||(st.peek()=='{' && ch == '}')
              ||(st.peek()=='[' && ch == ']')){
                  st.pop();
              }else
              {
                  return false;
              }
          }
          

      } 
      if(st.isEmpty()){
          return true;
      } 
      return false;
        
    }
}