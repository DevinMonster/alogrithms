package last;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Utils {
    /**
     * 注入PATH
     */
    private static String PATH;

    static {
        if (UnitTest.PATH.endsWith("/")) {
            PATH = UnitTest.PATH;
        } else {
            PATH = UnitTest.PATH + "/";
        }
    }

    /**
     * 生成有序的数据
     * @param n 数量
     * @param fileName 路径下的文件名
     */
    public static void generateOrderedData(int n, String fileName) {
        File file = new File(Utils.PATH+fileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(n+"\n");
            for (int i = 0; i < n; i++) {
                fileWriter.write(i+" ");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成随机的数据
     * @param n 数量
     * @param fileName 文件名
     */
    public static void generateRandomData(int n, String fileName) {
        File file = new File(Utils.PATH+fileName);
        try {
            Random random = new Random();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(n+"\n");
            for (int i = 0; i < n; i++) {
                fileWriter.write(random.nextInt((int)1e9)+" ");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成答案
     * @param ans 答案
     * @param fileName 文件名
     */
    public static void generateAnswer(int ans, String fileName) {
        File file = new File(Utils.PATH+fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(ans+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
