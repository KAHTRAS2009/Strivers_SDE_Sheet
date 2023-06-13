// LEETCODE 142

/*
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

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
2
*/

// Approach: 
/*
In this problem, we first use the concept to check whether there exist a cycle in a linked list or not that is using slow and fast pointer
approach, where we initialize the slow and fast pointer with the head and then move the slow pointer by 1 and fast pointer by 2 until fast
reaches null or both the pointers become equal.


BEG -> n -> n -> M -> n -> n -> n -> I -> n -> M

Now, consider a general case of this cycle, where initial node will be BEG, M is the begining of cycle and I is the meeting point of the 
two pointers. Now, clearly, fast pointer moves a distance twice that was covered by slow pointer. So, if ->
F => distance b/w BEG and M
a => distance b/w M and I
C => length of cycle.

Distance covered by slow pointer = F + a
Distance covered by fast pointer = F + nC + a, where n is revolutions(laps) made by fast pointer

Thus, 2(F+a) = F+nC+a 
==> F+a = nC

Now, if we place the slow pointer at begining i.e., at arr[0]. Then, it will take F distance to reach at M.
Also, fast pointer was at I only, and it is C - a distance away from the meeting point. Also, C - a is equal to F only. So, we shift both the
fast and slow pointer by 1 and they will reach at M only, and it is ultimately the begining of cycle and the duplicate number in the array. 

Approach 2: We can also use a hashSet of node type and if this set already contains a node, then it the begining of cycle.
*/

import java.util.*;

public class _35LinkedListCycle2 {
    
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

            ListNode begin = detectCycle(cycle);
            System.out.println(begin.val);
        }
    }

    // MAIN FUNCTION:
    public static ListNode detectCycle(ListNode head) {

        if(head == null || head.next == null) return null;

        ListNode slow = head.next,fast = head.next.next;

        if(fast == null) return null;

        while(fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if(slow != fast) return null;

        slow = head;

        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        } 

        return slow;
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
}
