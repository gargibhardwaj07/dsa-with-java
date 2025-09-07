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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head; // empty or 1 node
        }

        //step 1 to find length of ll
        int length = 1;
        ListNode tail = head;
        while(tail.next != null){
            length = length+1;
            tail = tail.next;
        }

         // step 2: make it circular
        tail.next = head;

         // step 3: find new tail position
        k = k % length; // handle if k > length
        int stepsToNewTail = length - k;
        
        ListNode newTail = head;
        //now we have to make new head 
        for(int i =1; i<stepsToNewTail; i++){
          newTail = newTail.next;

        }
          // step 4: set new head and break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}