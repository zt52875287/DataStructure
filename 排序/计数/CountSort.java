package 排序.计数;

import java.util.Arrays;

public class CountSort {

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        countSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void countSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] counter = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            counter[arr[i]]++;
        }


        //保证稳定性,反着累加
        for (int i = counter.length-2; i >=0; i--) {
            counter[i] += counter[i + 1];
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            //保证稳定性
            result[result.length-counter[arr[i]]] = arr[i];
            counter[arr[i]]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }
}
