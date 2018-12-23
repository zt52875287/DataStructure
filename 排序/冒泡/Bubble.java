package 排序.冒泡;

import java.io.IOException;
import java.util.Arrays;

public class Bubble {

    public static void main(String[] args) throws IOException {

        int[] a = new int[]{2, 5, 3, 8, 6, 1, 10, 9};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void bubbleSort(int[] arr) {
        boolean changed;

        for (int i = 0; i < arr.length; i++) {

            changed = false;

            for (int j = 0; j < arr.length-1; j++) {
                if (arr[j] >arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }
    }

}
