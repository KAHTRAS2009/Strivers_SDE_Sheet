// LEETCODE 876

/*
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.
*/

// Input:
/*
5
1 2 3 4 5
*/

// Output:
/*
[3,4,5]
*/

import java.util.*;

public class _26MiddleOfLinkedList {
    
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

            ListNode middleNode = middleNode(head);

            display(middleNode);
        }
    }   

    // MAIN FUNCTION: 
    public static ListNode middleNode(ListNode head) {
            ListNode slow = head,fast = head;
    
            while(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
    
            return slow;
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