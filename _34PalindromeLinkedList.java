// LEETCODE 234

/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
*/

// Input:
/*
4
1 2 2 1
*/

// Output:
/*
true
*/

// Approach: 
/*
There are two approaches to solve this problem. One is to create a global variable left and initializing it to head. And, then recursively, call
a helper function which just find the last node and then, we check if the right node value is equal to the left. If it is, then shift the left 
node to it's next and if at some point the value are not equal, then return false. TIME COMPLEXITY: O(n) and SPACE COMPLEXITY: O(n).

Other approach: Steps ==>
==> Find Middle Node.
==> reverse the nodes after the middle Node.
==> Check for the nodes of beginning node and the next node to the middle node. And, if they are equal, then shift both the nodes to their next.
And, continue till the middle pointing node reaches end(null).
*/

import java.util.*;

public class _34PalindromeLinkedList {

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
            
            System.out.println(isPalindrome1(head));
            System.out.println(isPalindrome2(head));
        }
    }
    
    // MAIN FUNCTION 1:
    static ListNode left;
    public static boolean isPalindrome1(ListNode head) {
        left = head;
        return help(head);
    }
    
    private static boolean help(ListNode right)
    {
        if(right.next == null)
        {
            return left.val == right.val;
        }

        boolean ans = help(right.next);    
        if(ans == false) return false;
        
        left = left.next;

        return left.val == right.val;
        
    }
    
    // MAIN FUNCTION 2: 
    public static boolean isPalindrome2(ListNode head) {
        if(head == null || head.next == null) return true;
        
        ListNode middle = middleNode(head);
        reverse(middle);

        ListNode curr = head;
        middle = middle.next;

        while(middle != null && curr.val == middle.val) {
            middle = middle.next;
            curr = curr.next;
        }

        return middle == null;
    }

    private static ListNode middleNode(ListNode head) {
        ListNode slow = head,fast = head;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    } 

    private static void reverse(ListNode node) {
        ListNode pre = null;
        ListNode curr = node.next;

        while(curr != null) {
            ListNode nxt = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nxt;
        }

        node.next = pre;
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

}