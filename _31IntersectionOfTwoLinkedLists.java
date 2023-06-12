// LEETCODE 160

/*
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have
no intersection at all, return null.
*/

// Input: First two lines contains the size and items of list1, then the third line will indicate the intersection node(-1 for no intersection) 
// and the fouth and fivth line will contain the number and element of list2 after the intersection.
/*
5
4 1 8 4 5
2
3 
5 6 1
*/

// Output:
/*
[8,4,5]
*/

import java.util.*;

public class _31IntersectionOfTwoLinkedLists {
    
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

            int k = scn.nextInt();
            ListNode node = getKthNode(head1,k);

            int n2 = scn.nextInt();
            int[] nums2 = new int[n2];

            for(int i = 0;i<n2;i++) {
                nums2[i] = scn.nextInt();
            }

            ListNode head2 = construct(nums2,node);

            ListNode intersect = getIntersectionNode(head1,head2);
            display(intersect);
        }
    }
    
    // MAIN FUNCTION: 
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode tmpA = headA,tmpB = headB;

        while(tmpA != tmpB) {

            tmpA = (tmpA == null) ? headB : tmpA.next;      // if tmpA is null, then at that instant the tmpB will be at gap of number of element
                                                            // difference between the two list. Similarly, for tmpB as well.
            tmpB = (tmpB == null) ? headA : tmpB.next;
        }

        return tmpA;
    }

    public static ListNode getKthNode(ListNode head,int k) {

        if(k == -1) {
            return null;
        }

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

    public static ListNode construct(int[] nums,ListNode node) {

        ListNode head = new ListNode(0);
        ListNode tmp = head;

        for(int num:nums) {
            ListNode nn = new ListNode(num);
            head.next = nn;
            head = nn;
        }

        head.next = node;
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