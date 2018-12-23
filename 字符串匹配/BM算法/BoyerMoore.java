package 字符串匹配.BM算法;

/**
 * @Author: zt52875287@gmail.com
 * @Date: 2018/12/19
 */
public class BoyerMoore {


    public boolean match(char[] main, char[] pattern, int characterSetSize) {

        int patternLen = pattern.length;
        int mainLen = main.length;

        //bad character
        int[] bc = getBadCharacterMap(pattern, characterSetSize);

        //good suffix
        int[] suffix = new int[patternLen];
        boolean[] prefix = new boolean[patternLen];
        fillGoodSuffixMap(pattern, suffix, prefix);

        //匹配
        for (int i = patternLen - 1; i < mainLen; ) {

            int badIndex = i;
            while (badIndex >= 0 && pattern[badIndex] == main[badIndex]) {
                badIndex--;
            }
            if (i - badIndex >= patternLen) {
                return true;
            }

            int x = patternLen - bc[(int) main[badIndex]] - (i - badIndex) - 1;
            int y = 0;
            //有好后缀
            if (i - badIndex > 0) {
                int suffixLen = i - badIndex;
                int childLen = i - badIndex - 1;
                if (suffix[suffixLen] != -1) {
                    //中间对齐到后缀
                    y = patternLen - suffixLen;
                } else {
                    boolean flag = false;
                    for (int j = childLen; j >= 0; j--) {
                        if (prefix[childLen] == true) {
                            //头移对齐到子后缀
                            flag = true;
                            y = patternLen - childLen;
                        }
                    }
                    if (flag == false) {
                        //表示所有子串都不匹配 头串，全部后移动
                        y = patternLen;
                    }
                }

            }

            i = i + Math.max(x, y);
        }


        return false;
    }


    private int[] getBadCharacterMap(char[] arr, int characterSetSize) {
        int[] bc = new int[characterSetSize];

        for (int i = 0; i < characterSetSize; i++) {
            bc[i] = -1;
        }
        //填充数组，表示字符集中每一种字符，在pattern中的index（出现多次的话，靠后的有效）
        for (int i = 0; i < arr.length; i++) {
            int ascll = (int) arr[i];
            bc[ascll] = i;
        }
        return bc;
    }

    private void fillGoodSuffixMap(char[] pattern, int[] suffix, boolean[] prefix) {

        int len = pattern.length;
        for (int i = 0; i < len; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < len; i++) {
            int j = i;
            int k = 0;

            //suffix[k]表示长度为k的尾子串是否在前面出现过，如果出现，suffix[k]=startIndex
            while (j >= 0 && pattern[j] == pattern[len - 1 - k]) {
                j--;
                k++;
                suffix[k] = j + 1;
            }

            //表示尾子串和头子串匹配，例如：abc...abc
            if (j == -1) {
                prefix[k] = true;
            }

        }


    }
}
