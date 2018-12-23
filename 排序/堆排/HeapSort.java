package 排序.堆排;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 堆排序
 * <p>
 * 1. 建立大根堆
 * <p>
 * 2. 排序：每次将堆顶的元素放到最后，然后adjust剩下的部分，调整完后就是由小到大的顺序。
 */

public class HeapSort {
    @Test
    public  void test() {
        int[] a = new int[]{2, 5, 3, 8, 10, 6, 1, 4, 9};
        heapsort(a);
        System.out.println(Arrays.toString(a));
    }

    private void heapsort(int[] arr) {
        buildMaxHeap(arr, arr.length);
        for (int i = arr.length - 1; i > 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            adjust(arr, 0, i-1);
        }
    }

    private void adjust(int[] arr, int head, int tail) {

        int rootVal = arr[head];
        for (int i = head * 2 + 1; i <= tail; i=i*2+1) {
            if (i < tail && arr[i] < arr[i + 1]) {
                i++;
            }
            if (rootVal >= arr[i]) {
                break;
            }
            arr[head] = arr[i];
            head = i;
        }
        arr[head] = rootVal;
    }

    private void buildMaxHeap(int[] arr, int length) {
        for (int i = length / 2 - 1; i >=0; i--) {
            adjust(arr,i,arr.length-1);
        }
    }

}
