// LEETCODE 61

/*
Given the head of a linked list, rotate the list to the right by k places.
*/

// Input:
/*
5 
1 2 3 4 5
2
*/

// Output:
/*
[4,5,1,2,3]
*/

// Approach: 
/*
Consider the list as 1 2 3 4 5. 
k = 1 ==> 5 1 2 3 4
k = 2 ==> 4 5 1 2 3
k = 3 ==> 3 4 5 1 2
k = 4 ==> 2 3 4 5 1
k = 5 ==> 1 2 3 4 5
k = 6 ==> 5 1 2 3 4 (which is same as k = 1).
Thus, it can be said that in actual for any value of k, the rotated list will be same as k%size (6%5 = 1).

Also, it can seen that for say k = 3, last 3 elements are picked up and added to the beginning of the list.

1 2 3 4 5 and k = 3 
take 3 4 5 and now head of the rotated list will be 3 and move the 5's next to the head of the original list and make the 2's next to null.

So, it can be seen that we require the position of 2,3, 5 and 1.Now, 2 node position can be obtained by shifting the initial node 1 times
(size - k - 1) and then 3 position can be obtained by the 2's next. Thereafter, 1's position is already known and 5's position can be
obtained by shifting the 3's positon until it's next reaches null.
And, after knowing all the node, perform the desired operations.
*/

import java.util.*;

public class _37RotateList {
    
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
            
            ListNode rotated = rotateRight(head,k);
            display(rotated);
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        
        if(head == null || head.next == null || k == 0) return head;

        int size = size(head);
        ListNode curr = head;

        int shift = size - k%size;
        if(shift == size) {
            return head;
        }

        while(shift > 1) {
            curr = curr.next;
            shift--;
        }   

        ListNode ans = curr.next;
        curr.next = null;
        ListNode tmp = ans;

        while(tmp.next != null) {
            tmp = tmp.next;
        }

        tmp.next = head;
        return ans;        
    }

    private static int size(ListNode node) {
        ListNode curr = node;
        int cnt = 0;

        while(curr != null) {
            curr = curr.next;
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
