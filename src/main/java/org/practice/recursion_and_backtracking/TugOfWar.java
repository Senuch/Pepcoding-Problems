package org.practice.recursion_and_backtracking;

import java.util.*;

public class TugOfWar {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = new int[scn.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        solve(arr, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
        System.out.println(ans);
    }

    static int mindiff = Integer.MAX_VALUE;
    static String ans = "";

    public static void solve(int[] arr, int vidx, ArrayList<Integer> set1, ArrayList<Integer> set2, int soset1, int soset2) {
        if (vidx == arr.length) {
            int diff = Math.abs(soset1 - soset2);
            if (diff < mindiff) {
                mindiff = diff;
                ans = set1.toString() + set2.toString();
            }
            return;
        }

        int currentValue = arr[vidx];

        if (set1.size() < (arr.length + 1) / 2) {
            set1.add(currentValue);
            solve(arr, vidx + 1, set1, set2, soset1 + currentValue, soset2);
            set1.remove(set1.size() - 1);
        }

        if (set2.size() < (arr.length + 1) / 2) {
            set2.add(currentValue);
            solve(arr, vidx + 1, set1, set2, soset1, soset2 + currentValue);
            set2.remove(set2.size() - 1);
        }

    }
}
