package 字符串匹配.AC自动机;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: zt52875287@gmail.com
 * @Date: 2018/12/23
 */
public class AcFactory {

    private AcNode root;

    public AcFactory(String[] dict) {

        root = new AcNode('/');
        root.fail = null;
        //根据词典构建trie树
        initAcAutomation(root, dict);

        // 深度优先遍历print trie树，打印树中的路径
        //DFScheck(root);

        //根据trie树，构建ac自动机
        buildFailPointer(root);

    }

    /**
     * 填充fail指针，完成ac自动机
     *
     * @param root
     */
    private void buildFailPointer(AcNode root) {

        LinkedList<AcNode> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            AcNode fNode = queue.removeFirst();

            //遍历所有的子节点
            for (int i = 0; i < fNode.children.length; i++) {
                if (fNode.children[i] == null) {
                    continue;
                }

                AcNode cNode = fNode.children[i];

                //如果父节点是root节点，那么它的fail指向root
                if (fNode == root) {
                    cNode.fail = root;
                } else {//如果父节点不是root节点
                    //找到父节点的fail节点
                    AcNode fFail = fNode.fail;
                    // 如果节点 fFail 中没有子节点的字符等于节点 cNode 包含的字符，
                    // 则令 fFail = fFail.fail
                    // 继续查找，直到 q 是 root 为止(每次都不相等)，
                    // 如果某一次相等了，则说明找到 cNode.fail 了，跳出循环;
                    // 如果最终都找不到相同字符的子节点，就让 cNode.fail 指向 root。
                    while (fFail != null) {
                        AcNode fFailChild = fFail.children[cNode.data - 'a'];
                        if (fFailChild != null) {
                            cNode.fail = fFailChild;
                            break;
                        } else {
                            fFail = fFail.fail;
                        }
                    }
                    if (fFail == null) {
                        cNode.fail = root;
                    }
                }
                queue.add(cNode);
            }
        }
    }

    /**
     * 敏感词匹配
     */
    public boolean compare(String input) {
        char[] text = input.toCharArray();
        AcNode node = this.root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            while (node.children[index] == null && node!=root) {
                node = node.fail;
            }

            node = node.children[index];
            if (node == null) {
                node = root;
            }

            AcNode tmp = node;
            while (tmp != root) {
                if (tmp.isEndingChar) {
                    int curse = i-tmp.patternLen+1;
                    System.out.println("匹配成功，下标" + curse +"长度"+ tmp.patternLen) ;
                }
                tmp = tmp.fail;
            }


        }


        return false;
    }

    /**
     * 初始化，从字典构建trie树
     */
    private void initAcAutomation(AcNode root, String[] dict) {
        for (int i = 0; i < dict.length; i++) {
            char[] word = dict[i].toCharArray();
            insert(word);
        }
    }

    /**
     * 插入一个词到树（字典）中
     */
    public void insert(char[] word) {
        //表示当前匹配到的节点
        AcNode node = root;

        int index = -1;

        // 从头开始匹配word中的字符，如果某个字符匹配失败，则新建一个节点，在继续向下匹配
        for (int i = 0; i < word.length; i++) {
            index = word[i] - 'a';
            if (node.children[index] == null) {
                //word 中下标为i的字符匹配失败，trie树需要新加一个节点
                AcNode newAcNode = new AcNode(word[i]);
                node.children[index] = newAcNode;
            }

            if (i == word.length - 1) {
                node.children[index].isEndingChar = true;
                node.children[index].patternLen = word.length;
            }

            node = node.children[index];
        }


    }


    /**
     * 深度优先搜索，检测trie树是否构建成功
     */
    private void DFScheck(AcNode root) {
        ArrayList<String> result = new ArrayList<>();
        System.out.println("深度优先遍历trie树，得到字典中所有项目：");
        DfsPrint(root, "", result);
        System.out.println("深度优先遍历trie树，列出字典中所有从root到叶子节点的路径：");
        for (String s : result) {
            System.out.println(s);
        }
    }

    private void DfsPrint(AcNode root, String prefix, ArrayList<String> result) {

        if (root != null) {
            prefix = prefix + root.data;

            if (root.isEndingChar) {
                System.out.println(prefix);
            }

            int childCount = 0;
            for (int i = 0; i < root.children.length; i++) {
                if (root.children[i] != null) {
                    childCount++;
                    DfsPrint(root.children[i], prefix, result);
                }
            }
            if (childCount == 0) {
                result.add(prefix);
            }
        }

    }
}
