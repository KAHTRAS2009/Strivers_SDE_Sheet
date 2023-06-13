// GFG LINK:
/*
https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
*/

/*
Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order.
*/

// Input: In this problem, there is no main function, only the function is added.
/*
4 
4 2 3 4                  
5 7 8 30 10 20 19 22 50 28 35 40 45

5 -> 10 -> 19 -> 28
|     |     |     | 
7     20    22   35
|           |     | 
8          50    40
|                 | 
30               45
*/

// Output: 
/*
5 7 8 10 19 20 22 28 30 35 40 45 50
*/

/*
Explanation:
The resultant linked lists has every node in a single level.
(Note: | represents the bottom pointer.)
*/

// Approach: 
/*
HAVE A DRY RUN.
*/

// Driver Code Starts

public class _36FlatteningALinkedList {

    public static class Node
    {
        int data;
        Node next;
        Node bottom;
        
        Node(int d)
        {
            data = d;
            next = null;
            bottom = null;
        }
    }

    public static Node flatten(Node head)
    {
        Node nn = head;

        while(head.next != null) {
            Node child = head.bottom;
            Node nxt = head.next;

            Node pre = head;

            while(child != null && child.data <= nxt.data) {
                pre = child;
                child = child.bottom;
            }

            pre.bottom = nxt;

            Node combineChild = overAllChild(child,nxt.bottom);
            nxt.bottom = combineChild;
            head.next = null;
            head = nxt;
        }

        return nn;

    }

    private static Node overAllChild(Node c1,Node c2) {

        Node node = new Node(0);
        Node ans = node;

        while(c1 != null && c2 != null) {
            if(c1.data <= c2.data) {
                Node nn = new Node(c1.data);
                node.bottom = nn;
                node = nn;
                c1 = c1.bottom;
            } else {
                Node nn = new Node(c2.data);
                node.bottom = nn;
                node = nn;
                c2 = c2.bottom;
            }
        }

        if(c1 != null) {
            node.bottom = c1;
        }

        if(c2 != null) {
            node.bottom = c2;
        }

        return ans.bottom;
    }

}


// } Driver Code Ends
    
    
    /*Node class  used in the program
    class Node
    {
        int data;
        Node next;
        Node bottom;
        
        Node(int d)
        {
            data = d;
            next = null;
            bottom = null;
        }
    }
    */
    /*  Function which returns the  root of 
        the flattened linked list. */