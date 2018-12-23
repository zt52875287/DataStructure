package 排序.快排;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {

        int[] a = new int[]{2, 5, 3, 8, 6, 1, 10, 9};
//        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 三步：
     * 1. 分割
     * 2. 确定标志节点最终位置
     * 3. 对标志节点两边分别进行快排
     */

    public static void quickSort(int[] a, int head, int tail) {

        int low = head;
        int high = tail;
        int pivot = a[low];
        if (low < high) {

            while (low<high) {
                while (low < high && pivot <= a[high]) high--;
                a[low] = a[high];
                while (low < high && pivot >= a[low]) low++;
                a[high]=a[low];
            }
            a[low] = pivot;

            if(low>head+1) quickSort(a,head,low-1);
            if(high<tail-1) quickSort(a,high+1,tail);
        }

    }
}
