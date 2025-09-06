/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); //take dummy node 
        ListNode curr = dummy; //abhi curr node dummy h
        int carry =0;
 
      // loop while there are numbers left in l1 or l2, or we still have a carry
        while(l1 != null || l2 != null || carry != 0){
            int sum = carry; //abhi sum carry he h kyuki hume carry bhi to sum me chahiye

            if(l1 != null){
                sum = sum + l1.val;
               l1 = l1.next; //move forward to calculate sum of other node
            }

             if(l2 != null){
                sum = sum + l2.val;
                l2 = l2.next; // move forward
            }
           
           //Suppose we are adding 9 + 8 + 0(carry)
           // sum = 17
           // digit = 17 % 10 = 7  (put in node)
          // carry = 17 / 10 = 1  (use in next addition)

            //new digit after sum of l1+l2 data
            int digit = sum % 10; // 

            carry = sum/10; 

       //nye node aan upr calclutaion s mil gye aab hum dummy ki next nye node ko bna denge 
            curr.next = new ListNode(digit);
            curr = curr.next; //abb curr aage chlta jyga
        }
        return dummy.next; //kyuki dummy k bd he head h hmara
        
    }
}