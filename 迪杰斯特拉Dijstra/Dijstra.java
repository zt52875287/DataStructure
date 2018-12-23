package 迪杰斯特拉Dijstra;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*

1. 总共执行n-1次（出发点除外）松弛操作，直到所有的点加入flag[]
2. 每次选择未添加的节点中dis[]距离最小的一个进行松弛
3. 当 dis[latest] + edges[][] < dis[new] 时，刷新dis[new]，将new节点加入flag[]

 */

public class Dijstra {


    public static void main(String[] args) {

        //初始化边，到自身长度为0，不可达节点距离为-1
        int[][] edges = {
                {0, 1, 12, -1, -1, -1},
                {-1, 0, 9, 3, -1, -1},
                {-1, -1, 0, -1, 5, -1},
                {-1, -1, 4, 0, 13, 15},
                {-1, -1, -1, -1, 0, 4},
                {-1, -1, -1, -1, -1, 0}};

         int[][] map = {
                { 0, 8, 1, 2, -1 },
                { 8, 0, -1, 3, -1 },
                { 1, -1, 0, 2, 3 },
                { 2, 3, 2, 0, -1 },
                { -1, -1, 3, -1, 0 }
        };


        int[] distance = getDijstra(edges, 1);
        System.out.println("=== " +Arrays.toString(distance));

        System.out.println("=================================");

        int[] distance1 = getDijstra(map, 1);
        System.out.println("=== " +Arrays.toString(distance1));


    }

    public static int[] getDijstra(int[][] edges, int firstNode) {

        int nodeNum = edges.length;

        /**
        用来存储每次加入的最短节点，最后遍历该数组，即可得通过的路径
         */
        int[] path = new int[nodeNum];
        /**
         * 初始节点的前驱是他自己
         */
        path[firstNode-1] = firstNode;

        //初始化标记数组，标记初始节点：
        int[] flags = new int[nodeNum];
        flags[firstNode - 1] = 1;

        //初始化距离数组，表示初始节点到他们的距离：
        int dis[] = edges[firstNode-1];

        //记录最新添加标记的节点，即最新的 flags[latestNode] = 1 的节点
        int latestNode = firstNode - 1;

        //n-1次循环，每次取 没有被标记的节点中，dis值中最小的那一个进行操作
        for (int i = 1; i < nodeNum; i++) {

            System.out.println(Arrays.toString(dis));

            int minDis = -1;
            int minNode = 0;


            //遍历标记数组，在没有添加标记的，可达的节点中，寻找距离最短的一个
            for (int j = 0; j < nodeNum; j++) {

                if (flags[j] == 0 && edges[latestNode][j] > 0) {

                    if (minDis < 0) {
                        minDis = edges[latestNode][j];
                        minNode = j;
                    } else if (minDis > 0 && edges[latestNode][j] < minDis) {
                        minDis = edges[latestNode][j];
                        minNode = j;
                    }

                }
            }

            /**
             *
             * 由于使用edges[firstNode-1] 初始化dis[]数组，相当于已经松弛了一次，因此，第一次循环时
             * 把dis中距离最短的节点前驱设为firstnode
             */
            if(i==1){
                path[minNode] = firstNode;
            };

            //对刚找到的节点 minNode的边进行relaxation
            for (int j = 0; j < nodeNum; j++) {

                //如果边j可达
                if (edges[minNode][j] > 0 ){

                    // 如果代价小于当前的dis[j]
                    // 或者
                    // dis[j]之前是不可达的
                    // 则 ：relaxation成功
                    if(edges[minNode][j] + dis[minNode] < dis[j]  ||
                            dis[j] == -1) {

                        dis[j] = edges[minNode][j] + dis[minNode];

                        path[j]=minNode+1;
                    }
                }
            }

            //松弛完毕，将该点加入flag
            flags[minNode] = 1;
        }

        System.out.println("节点序号：" + "[1, 2, 3, 4, 5, 6]");
        System.out.println("路径数组：" + Arrays.toString(path));
        return dis;


    }

}
