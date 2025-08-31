/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        //in this we copy value of next node like i have 1->2->3->4 , dlt= 3
        //so i do node.val = node.next.val aab node 2 ki value me uske next ki val aa jyge mtlb 3
        node.val = node.next.val;
        node.next = node.next.next;
    }
}