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
    public ListNode reverseKGroup(ListNode head, int k) {
        //step 1 to check if k nodes exist or not
        int count =0;
        ListNode temp = head;
        while(count < k){
            //agr temp khali h mtlb count kamh 
            if(temp == null){
                return head;
            }
            temp = temp.next;
            count++;
        }
        
        //step 2 rest of ll reverse
        //here we get prev node like k =3 so 4 is new prev node we have to link this with head
        ListNode prevNode = reverseKGroup(temp, k);

        //step 3 now reverse current group 
        count =0; temp = head;
        while(count < k){
            //now we have to make point 1 to next to 4 
            //but before point 1 to 4 we have to store 2 also
            ListNode next = temp.next;
            temp.next = prevNode;
            //now again we have 1->4 but we have to link 2 to 1 so we make 1 prev node
            prevNode = temp;
            //now temp is 2 so process do again till reverse
            temp = next;
            count++;
        }
        return prevNode;
    }
}