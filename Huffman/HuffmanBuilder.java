package Huffman;

import org.junit.Test;

import java.util.*;

/**
 * @Author: zt52875287@gmail.com
 * @Date: 2019/1/2
 * @Description:
 */
public class HuffmanBuilder {
    @Test
    public void test() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("a", 450);
        treeMap.put("b", 350);
        treeMap.put("c", 90);
        treeMap.put("d", 60);
        treeMap.put("e", 30);
        treeMap.put("f", 20);

        //求解huffman编码
        Map<String, String> result = buildHuffman(treeMap);

        //打印编码
        Iterator<Map.Entry<String, String>> it = result.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            System.out.println(next.getKey() + " : " + next.getValue());
        }

    }

    /**
     * @Description:
     * @Param: map<字符 , 频率>
     * @Return: map<字符 , huffman码>
     * @Auther: zt52875287@gmail.com
     * @Date: 2019/1/2 18:33
     */
    public Map<String, String> buildHuffman(Map<String, Integer> map) {

        // 优先队列构建小根堆
        PriorityQueue<TreeNode> queue = new PriorityQueue<>(Comparator.comparingInt(TreeNode::getVal));

        //将目标map中的数据存到小根堆中
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            queue.add(new TreeNode(next.getKey(), next.getValue()));
        }

        //每次删除val最小的两个节点，合并为一个新节点存入堆中；最终堆中只有一个节点，它是整个树的root
        while (queue.size() > 1) {
            //删除val最小的两个
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            //合并为一个新节点存入堆中
            TreeNode parent = new TreeNode("", node1.val + node2.val);
            parent.left = node1;
            parent.right = node2;
            queue.add(parent);
        }

        //存储结果 map<字符 , huffman码>
        HashMap<String, String> result = new HashMap<>(map.size());

        StringBuilder sb = new StringBuilder();

        //递归
        buildCode(queue.peek(), result, sb);

        return result;
    }

    private void buildCode(TreeNode peek, HashMap<String, String> result, StringBuilder sb) {

        if (peek == null) {
            return;
        }

        if (peek.right == null && peek.left == null) {
            result.put(peek.key, sb.toString());
        }

        if (peek.left != null) {
            sb.append('0');
            buildCode(peek.left, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if ((peek.right != null)) {
            sb.append('1');
            buildCode(peek.right, result, sb);
            sb.deleteCharAt(sb.length() - 1);

        }
    }

    private class TreeNode {
        String key;
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public int getVal() {
            return val;
        }

        TreeNode(String key, int val) {
            this.key = key;
            this.val = val;

        }
    }

}
