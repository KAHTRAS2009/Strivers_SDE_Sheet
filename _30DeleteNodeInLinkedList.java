// LEETCODE 237

/*
There is a singly-linked list head and we want to delete a node node in it.

You are given the node to be deleted node. You will not be given access to the first node of head.

All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.

Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

The value of the given node should not exist in the linked list.
The number of nodes in the linked list should decrease by one.
All the values before node should be in the same order.
All the values after node should be in the same order.
*/

// Input:
/*
4
4 5 1 9
1
*/

// Output:
/*
[4,1,9]
*/

import java.util.*;

public class _30DeleteNodeInLinkedList {
    
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

            // To determine the kth node from beginning (0-indexed) that has to be delete (k<list.size()-1) ==> not a last element. 
            int k = scn.nextInt();

            ListNode head = construct(nums);
            display(head);

            ListNode node = getKthNode(head,k);         // get the kth node of the list.

            deleteNode(node);
            display(head);
        }
    }

    // MAIN FUNCTION: 
    public static void deleteNode(ListNode node) {

        node.val = node.next.val;
        node.next = node.next.next;
        
    }

    public static ListNode getKthNode(ListNode head,int k) {

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
