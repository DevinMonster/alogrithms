package last;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class UnitTest {
    static int[] height;
    static int ans;
    public static final String PATH = "C:/Users/Administrator/Desktop/test/";
    public static final String GG1 = "gg1"; // 4k
    public static final String GG2 = "gg2"; // 4w
    public static final String GG3 = "gg3"; // 40w
    public static final String GG4 = "gg4"; // 40w有序
    public static final String GG5 = "gg5"; // 80w有序
    public static final String GGRD = "ggrd"; // 40w随机

    /**
     * 获取高度的具体代码
     */
    private static void getHeight(String type) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(PATH + type + ".in"));
            int n = sc.nextInt(), i = 0;
            height = new int[n];
            while (sc.hasNext()) {
                height[i++] = sc.nextInt();
            }
            sc = new Scanner(new FileInputStream(PATH + type + ".out"));
            ans = sc.nextInt();
            System.out.println("给定的答案:" + ans);
            System.out.println("------------");
        } catch (Exception e) {
            DynamicProgramming dynamicProgramming = new DynamicProgramming();
            int ans = dynamicProgramming.largestArea(height);
            Utils.generateAnswer(ans, type + ".out");
            System.out.println("给定的答案:" + ans);
            System.out.println("------------");
        } finally {
            sc.close();
        }
    }

    /**
     * 用于测试:获取数据
     */
    @Test
    public void test() {
        getHeight(GG1);
//        getHeight(GG2);
//        getHeight(GG3);
//        getHeight(GG4);
//        getHeight(GG5);
//        getHeight(GGRD);
//        Utils.generateOrderedData(800000,"gg5.in");
//        Utils.generateRandomData(400000,"ggrd.in");
    }

    /**
     * 用于测试: 普通的暴力方法
     */
//    @Ignore
    @Test
    public void test0() {
        BruteForceNormal normal = new BruteForceNormal();
        System.out.println("普通的暴力方法:" + normal.largestArea(height));
        System.out.println("------------");
    }

    /**
     * 用于测试: 优化的暴力
     */
    @Ignore
    @Test
    public void test1() {
        BruteForceUpdate update = new BruteForceUpdate();
        System.out.println("优化的暴力:" + update.largestArea(height));
        System.out.println("------------");
    }

    /**
     * 用于测试: 普通的分治
     */
//    @Ignore
    @Test
    public void test2() {
        DivideConquerNormal normal = new DivideConquerNormal();
        System.out.println("普通的分治:" + normal.largestArea(height));
        System.out.println("------------");
    }

    /**
     * 用于测试:线段树
     */
    @Test
    public void test3() {
        DivideConSegTree segTree = new DivideConSegTree();
        System.out.println("线段树:" + segTree.largestArea(height));
        System.out.println("------------");
    }

    /**
     * 用于测试:DP解题
     */
    @Test
    public void test4() {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println("DP:" + dp.largestArea(height));
    }
}
