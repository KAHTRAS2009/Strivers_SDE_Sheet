// LEETCODE 1

/*
Problem Type 1 :
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Problem Type 2:
Given an array of integers nums and an integer target, return the list of all pairs of elements such that they add up to target in a sorted 
manner.
*/

// Input:
/*
Problem Type 1 Input:
4
2 7 11 15
9

Problem Type 2 Input:
5
1 2 3 4 5
5
*/

// Output:
/*
Problem Type 1 Output:
0 1
Problem Type 2 Output:
1 4
2 3
*/

//Approach:
/*
Problem Type 1 Approach: 
There are two approaches - first one is two iterate two loops and check whether arr[i] + arr[j] == target. 
And, other method is to create a HashMap which store the element that are in array along with their key as the index and for each element, we
check whether target - arr[i] is present in the hashMap or not, if it is then return the indicies. 

Problem Type 2 Approach: 
In this problem, there are more than one pair that exist, so in this problem also we create a HashMap that store key:value pair as the element 
is the key and number of occurance is the value. So, we check if the target - arr[i] contains in HashMap, and if it is, then we create a array 
and add the two elements into it. After that, we add this array into the answer ArrayList, the number of times target - arr[i] has occured.
At the end repeated elements pair will also be there in the answer.

Consider array as : 1 4 1 4 and target as 5

Then, at first index 0, we check whether 4 is present in the hashmap or not, if it is, then add the current element and target - curr element 
into a array and then sort it and thereafter add this array into the list the number of times 4 is present in the hashmap. And, if 4 is not 
present in the hashmap, then simply add the current element to the hashmap with increasing its value by 1.

DRY RUN :
at index 0 --> 4(5-1) is not present in the hashmap, so add 1,1 into hashmap
at index 1 --> 1(5-4) is present in the hashmap,thus add [1,4] into the answer list 1 times(since 1 occur 1 times).Also, add (4,1) into hashmap.
at index 2 --> 4(5-1) is present in the hashmap,thus add [1,4] into the answer list 1 times(since 4 occur 1 times).Also, add (1,1+1) into hashmap.
at index 3 --> 1(5-4) is present in the hashmap,thus add [1,4] into the answer list 2 times(since,now 1 occur 2 times).Also, update (4,1) to 
(4,2) in the hashmap.

In this way, final answer will have [[1,4],[1,4],[1,4],[1,4]].


*/

import java.util.*;

public class _19TwoSumPair {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int [] arr = new int[n];

            for(int i = 0;i<n;i++) {
                arr[i] = scn.nextInt();
            }

            int target = scn.nextInt();
            int[] idx = twoSum(arr, target);

            System.out.println(idx[0] + " " + idx[1]);

            List<int[]> ans = pairSum(arr, target);
            for(int[] ar:ans) {
                for(int ele:ar) {
                    System.out.print(ele + " ");
                }
                System.out.println();
            }
        }
    }

    // PROBLEM TYPE 1
    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int i = 0;i<nums.length;i++) {
            if(hm.containsKey(target - nums[i]))
                return new int[]{i,hm.get(target-nums[i])};
            hm.put(nums[i],i);
        }

        return null;
    }

    // PROBLEM TYPE 2
    public static List<int[]> pairSum(int[] arr, int s) {

        List<int[]> ans = new ArrayList<>();
        HashMap<Integer,Integer> hm = new HashMap<>();
        
        for(int i = 0;i<arr.length;i++) {
            if(hm.containsKey(s-arr[i])) {
                int[] temp = new int[]{arr[i],s-arr[i]};
                Arrays.sort(temp);

                for(int j = 0;j<hm.get(s-arr[i]);j++) {
                    ans.add(temp);
                } 
            }
            hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
        }

        Collections.sort(ans,new SortByVal());
        return ans;
    }    
    
    static class SortByVal implements Comparator<int[]>{

        public int compare(int[] a,int[] b) {
            if(a[0] != b[0]) return a[0]-b[0];
            else {
                return a[1]-b[1];
            }
        }
    }
    

}
