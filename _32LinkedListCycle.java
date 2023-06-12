// LEETCODE 141

/*
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
*/

// Input:
/*
4
3 2 0 -4
1
*/

// Output:
/*
true
*/

import java.util.*;

public class _32LinkedListCycle{

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
            
            int k = scn.nextInt();                  // To form a cycle at this index at the kth index, -1 for no cycle
            ListNode node = getKthNode(head,k);

            ListNode cycle = cycleList(head,node);  // will connect the end of list to the node
            
            boolean flag = hasCycle(cycle);
            System.out.println(flag);
        }
    }

    // MAIN FUNCTION: 
    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head,fast = head.next;

        while(fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return (slow == fast);
    }

    public static ListNode cycleList(ListNode head, ListNode node) {

        ListNode tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
        }

        tmp.next = node;
        return head;
    }
    public static ListNode getKthNode(ListNode head,int k) {
        if(k == -1) return null;

        while(k>0) {
            head = head.next;
            k--;
        }

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

        ListNode tmp = head;
        while(tmp != null) {
            System.out.print(tmp.val + " -> ");
            tmp = tmp.next;
        }
        System.out.println("null");
    }

}