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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //declare three pointers
        //1->2->3 // so here first is 1 and sc 2 and third 3
        ListNode first = head;
        ListNode second = head.next;
        ListNode prev = null; // we know no elmnt before 1st so its null 

        while(first != null && second != null){
            //if we have 1->2->3->4 like this so we make 2.next = 1 2->1 but before we have to save 3node adress
            ListNode third = second.next; //2ka next
            second.next = first;

            //till now we have like 2->1 now we have to make 1.next 3
            first.next = third; //now 2->1->3

            //if prev not null so we make prev.next = second manlo 2 s phel koi prev tha to prev.next bhi to 2 krna h agr nhi h null to 2 head ban jyga new head
            if(prev != null){
                prev.next = second;
            }else{
                head = second;
            }

            //but now we have to swap other paird pending so we update pointers
            //hwere we make 3 first, 4 second and 1 prev
            prev = first; // 1 is first pphle now we make prev 
            first = third; // now 3 is first

            //if only second exist manlo 4 ho he na 
            if(third != null){
            second = third.next;//now second is first 4 ka next basically 3 ka next 4 h aab
            }else{
                second = null; // so 3-> null if 4 not exist
            }


        }
      return head;
        
    }
}