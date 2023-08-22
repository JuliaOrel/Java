package org.example.entities;

import java.util.Arrays;

public class ArraySorter {
    public static void sortAscending(int[] array) {
        Arrays.sort(array);
    }

    public static void sortDescending(int[] array) {
        Arrays.sort(array);
        reverseArray(array);
    }

    private static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
