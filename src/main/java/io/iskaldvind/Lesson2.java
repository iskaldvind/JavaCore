package io.iskaldvind;

import java.util.Arrays;

public class Lesson2 {

    public static void main(String[] args) {

        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = arr1[i] == 1 ? 0 : 1;
        }

        int[] arr2 = new int[8];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i * 3;
        }

        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) arr3[i] *= 2;
        }

        int[][] arr4 = new int[4][4];
        for (int i = 0; i < arr4.length; i++) {
            arr4[i][i] = 1;
            arr4[i][arr4.length - 1 - i] = 1;
        }

        int[] arr5 = {1, 6, 48, 0, 29, -3, -100, 30, 71};
        int min = arr5[0];
        int max = arr5[0];
        for (int val : arr5) {
            if (val < min) min = val;
            if (val > max) max = val;
        }
        System.out.println("Array: " + Arrays.toString(arr5) + ", min=" + min + ", max=" + max);

        int[] arr6 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] arr7 = {1, 2, 10};
        System.out.println("checkBalance for array " + Arrays.toString(arr6) + " is " + checkBalance(arr6));
        System.out.println("checkBalance for array " + Arrays.toString(arr7) + " is " + checkBalance(arr7));

        int[] arr8 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr9 = {};
        System.out.println("elementsShifter for array " + Arrays.toString(arr8) + " and n=3:");
        elementsShifter(arr8, 3);
        System.out.println("elementsShifter for array " + Arrays.toString(arr8) + " and n=-2:");
        elementsShifter(arr8, -2);
        System.out.println("elementsShifter for array " + Arrays.toString(arr9) + " and n=0:");
        elementsShifter(arr9, 0);
    }

    public static boolean checkBalance(int[] arr) {
        if (arr.length < 2) return false;
        if (arr.length == 2) return arr[0] == arr[1];
        int left;
        int right;
        for(int i = 1; i < arr.length; i++) {
            left = 0;
            right = 0;
            for(int j = 0; j < i; j++) {
                left += arr[j];
            }
            for(int j = i; j < arr.length; j++) {
                right += arr[j];
            }
            if (left == right) {
                return true;
            }
        }
        return false;
    }

    public static void elementsShifter(int[] arr, int n) {
        int buffer;
        if (n == 0 || arr.length < 2) {
            String arrayAsString = arr.length == 0 ? "[]" : Arrays.toString(arr);
            System.out.println("Result array is " + arrayAsString);
        } else if (n > 0) {
            buffer = arr[arr.length - 1];
            for(int i = arr.length - 2; i >= 0; i--) {
                arr[i+1] = arr[i];
            }
            arr[0] = buffer;
            elementsShifter(arr, n - 1);
        } else {
            buffer = arr[0];
            for(int i = 0; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[arr.length - 1] = buffer;
            elementsShifter(arr, n + 1);
        }
    }
}
