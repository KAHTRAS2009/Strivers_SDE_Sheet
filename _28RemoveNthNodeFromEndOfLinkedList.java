// LEETCODE 19

/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.
*/

// Input:
/*
5
1 2 3 4 5
2
*/

// Output:
/*
[1,2,3,5]
*/

import java.util.*;

public class _28RemoveNthNodeFromEndOfLinkedList {
    
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

            int end = scn.nextInt();        // Nth index from end.

            ListNode head = construct(nums);

            ListNode remNode = removeNthFromEnd(head,end);
            display(remNode);
        }
    }

    // MAIN FUNCTION: 
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode slow = head,fast = head;

        for(int i = 0;i<n;i++) {
            fast = fast.next;
        }

        if(fast == null) return head.next;

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
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

        while(head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

}