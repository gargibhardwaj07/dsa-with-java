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
    public ListNode removeElements(ListNode head, int val) {
        //jb tk head ki value null nhi h or head ki val mil gye to head aab next head hoga vo dlt ho jyga
        while(head != null && head.val == val){
            head = head.next;
        }
        ListNode curr = head;
       
        while(curr != null && curr.next != null){
            //now check from head.next kyuki head ka upr check kr liya
            if(curr.next.val == val){
             curr.next = curr.next.next;
            }else{
                curr = curr.next;
            }
          
        }
        return head;
    }
}