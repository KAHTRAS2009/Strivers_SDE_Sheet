// LEETCODE 138

/*
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value
of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that 
the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in 
the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the 
copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
-> val: an integer representing Node.val
-> random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.
*/

// Input:
/*
[[7,null],[13,0],[11,4],[10,2],[1,0]]
*/

// Output:
/*
[[7,null],[13,0],[11,4],[10,2],[1,0]]
*/

// Approach: 
/*
There are two approaches to solve this problem:
Approach 1: Using a HashMap of nodes and nodes, where original node will be mapped to the newly created node. So, we create a new node of the 
original node first and then, assign it's next as per the original list and then add it to the hashMap and thereafter, we again loop through 
the original list, so that the random node can be linked and for that we mapped the curr random node to the hashMap node. 

Have a DRY RUN.

Approach 2: 
This is a unique approach and involves a sequence of steps:

Step I: Insert a newly created note between the existing one. Say, if 1 -> 2 -> 3 is the original list, then 1 copy after 1 original, 2 copy 
after 2 original and so on [1o -> 1c -> 2o -> 2c -> 3o -> 3c] {o-original, c-copy = only for depiction}.

Step II: Link the random node of the original list. Now, consider that if the random node of 1o is at 3o, then 1c random node must also point 
to 3c. If we observe that in the modified list -> 1o next is 1c and 3o next is 3c, thus we can set the 1c random to 3c by 
1o.next.random = 1c.random.next. And, then move the 1o pointer to 2c and so on.

Step III: Delink the copied list from the original list. Consider modified list as : [1o -> 1c -> 2o -> 2c -> 3o -> 3c]. So, initial create a
dummy note and point it to the new node. Now, take 2 pointer 1 at 1o and other at 2o. Now, assign dummy next to the 1o next and move the dummy to 
1c and then point the 1o next to the 2o and move the 2o pointer by 2 to place it at 3o.

Have a DRY RUN.
*/

import java.util.*;

public class _38CopyListWithRandomPointer {
    
    public static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // MAIN FUNCTION 1:
    public static Node copyRandomList1(Node head) {

        if(head == null) return null;
        
        // Creating a new copy inside the existing linkedlist.
        Node curr = head;
        while(curr != null) {
            Node nxt = curr.next;
            Node copy = new Node(curr.val);
            curr.next = copy;
            copy.next = nxt;
            curr = nxt;
        }

        // Linking Random in the new copy.
        Node itr = head;
        while(itr != null) {
            if(itr.random == null) itr.next.random = null;
            else itr.next.random = itr.random.next;
            itr = itr.next.next;
        }

        // Delinking the original and copy linked list.
        Node ans = new Node(0);
        Node tmp = ans;

        curr = head;
        itr = head.next.next;

        while(curr != null) {
            tmp.next = curr.next;
            tmp = tmp.next;

            curr.next = itr;
            curr = itr;
            itr = (curr != null) ? curr.next.next:null;
        }

        return ans.next;
    }

    // MAIN FUNCTION 2:
    public static Node copyRandomList2(Node head) {

        if(head == null) return null;

        Node copyHead = new Node(head.val);
        
        Node tmp = copyHead;

        HashMap<Node,Node> hm = new HashMap<>();

        hm.put(head,copyHead);
        Node curr = head.next;

        while(curr != null) {
            Node nn = new Node(curr.val);
            tmp.next = nn;
            tmp = tmp.next;
            hm.put(curr,tmp);
            curr = curr.next;
        }

        curr = head;
        tmp = copyHead;
        while(curr != null) {
            Node rdm = curr.random;
            if(rdm == null) {
                tmp.random = null;
            } else {
                tmp.random = hm.get(rdm);
            }

            tmp = tmp.next;
            curr = curr.next;
        }

        return copyHead;
        
    }

}