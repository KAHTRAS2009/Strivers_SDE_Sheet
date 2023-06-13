// LEETCODE 25

/*
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out 
nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.
*/

// Input:
/*
5
1 2 3 4 5
2
*/

// Output:
/*
[2,1,4,3,5]
*/

// Approach: 
/*
Consider the input - 12345 and k = 2.
So, total size is 5 and there will be 2 groups (5/k). So, initially, 12 gets reversed and then remaining remains intact. 

2->1->3->4->5
After this 34 gets reversed and other remains intact.
2->1->4->3->5. So, if we carefully observe that after 34 gets reversed, 3's next is 5 and 1's next is 4. So, we have to store 3(group Start 
position),4(group end Position),5(next element to the group),1 (previous element to the group) position. 

Have a DRY RUN.
*/

import java.util.*;

public class _33ReverseNodeInKGroup {
    
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] arg) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            int k = scn.nextInt();

            ListNode head = construct(nums);            

            ListNode node = reverseKGroup(head,k);
            
            display(node);
        }
    }

    // MAIN FUNCTION:
    public static ListNode reverseKGroup(ListNode head, int k) {
    
        int size = size(head);
        
        int cnt = 0;
        
        // To have the maximum number of groups that have to be reversed.
        int maxLimit = size/k;
        
        ListNode tmp = null;                            // tmp -> previous element to the group.
        ListNode curr = head;                           // curr -> next element to fetch.
        ListNode ans = head;
        
        while(cnt < maxLimit) {

            int shift = k;
            ListNode tmp2 = curr,pre = null;            // tmp2 -> group Start position
            // pre -> group end position
            
            // reverse the current group .
            while(shift > 0) {
                
                ListNode nxt = curr.next;
                curr.next = pre;
                pre = curr;
                curr = nxt;
                
                shift--;
            }
            
            // if tmp is null, then the answer is not yet modified.
            if(tmp == null) {
                ans = pre;
                tmp = tmp2;
                tmp2.next = curr;
            } else {
                tmp2.next = curr;
                tmp.next = pre;
                tmp = tmp2;
            }
                        
            cnt++;
        }
        
        return ans;
    }
    
    private static int size(ListNode head) {
        ListNode tmp = head;
        int cnt = 0;

        while(tmp != null) {
            tmp = tmp.next;
            cnt++;
        }

        return cnt;
    }
    
    public static ListNode construct(int[] nums) {
        
        ListNode head = new ListNode(0);
        ListNode tmp = head;

        for(int num:nums) {
            ListNode nn = new ListNode(num);
            head.next = nn;
            head = nn;
        }

        return tmp.next;
    }

    public static void display(ListNode head) {

        ListNode tmp = head;
        while(tmp != null) {
            System.out.print(tmp.val + " -> ");
            tmp = tmp.next;
        }
        System.out.println("null");
    }

}