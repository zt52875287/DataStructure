package 字符串匹配.AC自动机;

/**
 * @Author: zt52875287@gmail.com
 * @Date: 2018/12/23
 */
public class AcNode {

    //数据
    public char data;
    //子节点
    public AcNode[] children = new AcNode[26];
    //是否是结尾节点
    public boolean isEndingChar = false;
    //是结尾节点时，记录当前长度。
    public int patternLen = -1;
    //失败指针
    public AcNode fail;

    public AcNode(char data) {
        this.data = data;
    }

}
