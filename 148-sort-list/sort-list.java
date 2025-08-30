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
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode mid = getMid(head);
        ListNode rightHead = mid.next;
        mid.next = null;   // break into 2 halves
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        return mergeTwoLists(left, right);

        
    }

    //get mid
    public ListNode getMid(ListNode head){
        ListNode slow = head;
        //head.next is usually chosen for a more even split in merge sort.
        ListNode fast = head.next;
        while(fast !=null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
         return slow;

    }

       
     public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null){
            return list2;
        }
        else if(list2 == null){
            return list1;
        }

     //Basically In this we check by recusrion that which is small and we make small one head 
     //jb hume  small milgya to hum us list k next se or dusre list ko compare krenge konsa elmnt chota h aab
        if(list1.val <= list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        else{
              list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
        
    }
}