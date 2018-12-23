package 排序.Utils;

import java.util.Random;

public class RandomArrayFactory {
    static Random random=new Random();

    public static int[] getRandomArray(int length,int bound){
        if(length<=0){
            throw new IllegalArgumentException("array length must be a positive number");
        }
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }
}
