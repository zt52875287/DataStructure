package 字符串匹配.AC自动机;

/**
 * @Author: zt52875287@gmail.com
 * @Date: 2018/12/23
 */
public class AcTest {
    public static void main(String[] args) {
        String dict[] = {"opqabc","c","ac","b","dabcd","opqabd","opqaxx","a","qrtt","ert","opq","o"};

        AcFactory acFactory = new AcFactory(dict);

        String input = "anyqrtutmvjkac";

        acFactory.compare(input);


    }
}
