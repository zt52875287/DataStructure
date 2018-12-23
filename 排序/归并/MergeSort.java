package 排序.归并;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 3, 8, 10, 6, 1, 4, 9};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] arr, int head, int tail) {
        int low = head;
        int high = tail;
        int mid;
        if (low < high) {
            mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }

    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] tmp = new int[high - low + 1];

        int i = low;
        int j = mid + 1;
        int count = 0;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                tmp[count++] = arr[i++];
            } else {
                tmp[count++] = arr[j++];
            }
        }

        while (i <= mid) tmp[count++] = arr[i++];
        while (j <= high) tmp[count++] = arr[j++];

        for (int k = low; k <= high; k++) {
            arr[k] = tmp[k - low];

        }

    }


}
