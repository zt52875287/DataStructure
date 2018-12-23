package 排序.简单选择排序;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 3, 8, 6, 1, 10, 9};
        selectSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            int min = i;
            for (int j = i; j <arr.length; j++) {
                if(arr[min]>arr[j]){
                    min = j;
                }
            }

            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }






    }
}
