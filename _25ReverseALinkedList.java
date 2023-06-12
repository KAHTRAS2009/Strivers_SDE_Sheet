// LEETCODE 206

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.
*/

// Input:
/*
5
1 2 3 4 5
*/

// Output:
/*
[5,4,3,2,1]
*/

// Approach: 
/*

*/

import java.util.*;

public class _25ReverseALinkedList {
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

            ListNode head = construct(nums);

            ListNode rev = reverseList(head);

            display(rev);
        }
    }

    // MAIN FUNCTION: 
    public static ListNode reverseList(ListNode head) {
        
        ListNode pre = null;
        while(head != null) {
            ListNode nxt = head.next;
            head.next = pre;
            pre = head;
            head = nxt;
        }

        return pre;
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