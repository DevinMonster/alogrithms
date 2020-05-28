package last;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Utils {
    public static void generateOrderedData(int n, String fileName){
        File file = new File(UnitTest.PATH+fileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(n+"\n");
            for (int i = 0; i < n; i++) fileWriter.write(i+" ");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void generateRandomData(int n, String fileName){
        File file = new File(UnitTest.PATH+fileName);
        try {
            Random random = new Random();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(n+"\n");
            for (int i = 0; i < n; i++) fileWriter.write(random.nextInt((int)1e9)+" ");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
