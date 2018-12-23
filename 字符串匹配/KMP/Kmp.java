package 字符串匹配.KMP;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zt52875287@gmail.com
 * @Date: 2018/12/21
 */
public class Kmp {
    @Test
    public void test(){
        char[] a = "abecdabeacf".toCharArray();
        char[] b = "becdabeac".toCharArray();
        System.out.println(Arrays.toString(getNext(b)));
        System.out.println(match(a, b));
        System.out.println(kmp(a,b));
    }
    public int match(char[] arr, char[] pattern) {

        int next[] = getNext(pattern);

        for (int i = 0; i <= arr.length - pattern.length; ) {

            int k = 0;
            while (k < pattern.length && pattern[k] == arr[i + k]) {
                k++;
            }
            //匹配成功
            if (k == pattern.length) {
                return i;
            }

            if (next[k] == -1) {
                i = i + k + 1;
            } else {
                i = i + k - next[k];
            }

        }
        //匹配失败
        return -1;
    }

    private int[] getNext(char[] pattern) {

        int len = pattern.length;

        int[] next = new int[len];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < len; ++i) {
            while (k != -1 && pattern[k + 1] != pattern[i]) {
                k = next[k];
            }
            if (pattern[k + 1] == pattern[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }


    // a, b 分别是主串和模式串；n, m 分别是主串和模式串的长度。
    public int kmp(char[] a, char[] b) {
        int n = a.length;
        int m = b.length;
        int[] next = getNext(b);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到 a[i] 和 b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }

}
