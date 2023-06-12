// LEETCODE 21

/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.
*/

// Input:
/*
3
1 2 4
3 
1 3 4
*/

// Output:
/*
[1,1,2,3,4,4]
*/

// Approach: 
/*

*/

import java.util.*;

public class _27MergeTwoSortedListsAsLinkedList {
    
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

            int n2 = scn.nextInt();
            int[] nums2 = new int[n2];

            for(int i = 0;i<n2;i++) {
                nums2[i] = scn.nextInt();
            }

            ListNode list1 = construct(nums1);
            ListNode list2 = construct(nums2);

            ListNode merged = mergeTwoLists(list1,list2);
            display(merged);
            display(list1);
            display(list2);

            ListNode merged2 = mergeTwoListsInPlace(list1,list2);
            display(merged2);

            // In this method, the two lists get changed.
            display(list1);
            display(list2);
        }
    }

    // MAIN FUNCTION 1: 
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        
        ListNode tmp = head;

        while(list1 != null && list2 != null) {
            if(list1.val > list2.val) {
                ListNode nn = new ListNode(list2.val);
                head.next = nn;
                head = nn;
                list2 = list2.next;
            } else {
                ListNode nn = new ListNode(list1.val);
                head.next = nn;
                head = nn;
                list1 = list1.next; 
            }
        }

        while(list1 != null) {
                ListNode nn = new ListNode(list1.val);
                head.next = nn;
                head = nn;
                list1 = list1.next;
        }

        while(list2 != null) {
                ListNode nn = new ListNode(list2.val);
                head.next = nn;
                head = nn;
                list2 = list2.next;
        }
        return tmp.next;
    }

    // MAIN FUNCTION 2:
    public static ListNode mergeTwoListsInPlace(ListNode list1, ListNode list2) {
        
        ListNode pre = null;
        ListNode ans = list1;
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                pre = list1;
                list1 = list1.next;
            } else {
                ListNode nxt = list2.next;
                if (pre == null) {
                    ans = list2;
                    list2.next = list1;
                    pre = list2;
                    list2 = nxt;
                } else {
                    pre.next = list2;
                    list2.next = list1;
                    pre = list2;
                    list2 = nxt;
                }
            }
        }

        if(list1 == null) {
            if(pre == null) return list2;
            pre.next = list2;
        }

        return ans;
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