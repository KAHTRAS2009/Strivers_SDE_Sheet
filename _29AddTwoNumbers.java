// LEETCODE 2

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their 
nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
*/

// Input:
/*
3
2 4 3
3 
5 6 4
*/

// Output:
/*
[7,0,8]
*/

import java.util.*;

public class _29AddTwoNumbers {
    
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] arg) {
        try(Scanner scn = new Scanner(System.in)) {
            
            int n1 = scn.nextInt();
            int[] nums1 = new int[n1];

            for(int i = 0;i<n1;i++) {
                nums1[i] = scn.nextInt();
            }

            ListNode head1 = construct(nums1);

            int n2 = scn.nextInt();
            int[] nums2 = new int[n2];

            for(int i = 0;i<n2;i++) {
                nums2[i] = scn.nextInt();
            }

            ListNode head2 = construct(nums2);

            ListNode sum = addTwoNumbers(head1, head2);
            
            display(sum);
        }
    }

    // MAIN FUNCTION: 
    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        // Write your code here.
        
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        
        int car = 0;
        while(head1 != null || head2 != null || car != 0) 
        {
            int sum = 0;
            
            if(head1 != null) {
                sum += head1.val;
                head1 = head1.next;
            } 
            if(head2 != null) {
                sum += head2.val;
                head2 = head2.next;
            }
            
            sum += car;
            car = sum/10;
            
            ListNode nn = new ListNode(sum%10);
            head.next = nn;
            head = head.next;
        }
        
        return tmp.next;
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