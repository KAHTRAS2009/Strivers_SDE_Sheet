// LEETCODE 56

/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping 
intervals that cover all the intervals in the input.
*/

// Input:
/*
4
1 3 
2 6 
8 10
15 18
*/

// Output:
/*
1 6 ,8 10 ,15 18
*/

//Approach:
/*
In this problem, we have to converge overlapping intervals. So, we first sort the intervals array on the basis of starti and thereafter, 
look for the endi and check if the starti of the curr interval is less than the endi of the previous pair stored in answer. And, if it is 
then check if the endi of curr interval is more than the endi of the previous value of the answer, if so, then update the endi of the previous
ans interval. 

Now, if the starti of curr interval is more than the previous ans interval, there is no common overlapping and thus add this interval to the 
answer. At the end, overlapping intervals will be merged.
*/

import java.util.*;

public class _08MergeOverlappingIntervals {
    
    public static class Pair implements Comparable<Pair> {
        int i;
        int j;
    
        Pair(int i,int j) {
            this.i = i;
            this.j = j;
        }
    
        
        public int compareTo(Pair p) {
            return this.i - p.i;
        }
    }

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            
            int n = scn.nextInt();
            int[][] intervals = new int[n][2];
            for(int i = 0;i<n;i++) {
                for(int j = 0;j<2;j++) {
                    intervals[i][j] = scn.nextInt();
                }
            }

            int[][] ans = merge(intervals);

            for(int[] arr:ans){
                for(int ele:arr) {
                    System.out.print(ele + " ");
                }
                System.out.print(",");
            }
        }
    }

    public static int[][] merge(int[][] intervals) {

        Pair[] arr = new Pair[intervals.length];

        for(int i = 0;i<arr.length;i++) {
            arr[i] = new Pair(intervals[i][0],intervals[i][1]);
        }
        
        Arrays.sort(arr);

        ArrayList<Pair> ans = new ArrayList<>();

        Pair p = new Pair(arr[0].i,arr[0].j);
        ans.add(p);

        for(int i = 1;i<arr.length;i++) {

            int last = ans.size()-1;
            if(arr[i].i <= ans.get(last).j) {
                if(arr[i].j > ans.get(last).j) {
                    int strt = ans.get(last).i;
                    ans.set(last,new Pair(strt,arr[i].j));
                }
            } else {
                ans.add(new Pair(arr[i].i,arr[i].j));
            }
        }

        int[][] answer = new int[ans.size()][2];

        for(int i = 0;i<ans.size();i++) {
            p = ans.get(i);
            answer[i][0] = p.i;
            answer[i][1] = p.j;
        }

        return answer;
    }
}