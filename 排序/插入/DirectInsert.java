package 排序.插入;

/*
直接插入排序
稳定
时间复杂度 O(n^2)
空间复杂度 O(1)
 */

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class DirectInsert {

    public static void main(String[] args) {

        int arr[] = new int[]{2,7,2,5,1,8,6,12,9,4,10};
        directInsert(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void directInsert(int[] arr) {
        int low, mid, high,tmp = 0;

        for (int i = 1; i < arr.length; i++) {

            low = 0;
            high = i - 1;

            //折半查找
            while (low <=high) {
                mid = (low + high) / 2;
                //注意，当 arr[i] = arr[mid] 时，应使low=mid+1 确保相等元素的相对位置不变，否则相对位置全反
                if (arr[i] < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }

            //暂存a[i]
            tmp = arr[i];

            //比a[i]大的数据集体向后移一位
            for (int j = i; j >high+1; j--) {
                arr[j ] = arr[j-1];
            }

            arr[high+1] = tmp;

        }

    }
}
