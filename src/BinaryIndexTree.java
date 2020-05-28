import java.util.Scanner;

public class BinaryIndexTree {
    int[] tree; // 差分数组
    int n, m;

    public static void main(String[] args) {
        BinaryIndexTree bit = new BinaryIndexTree();
    }

    public BinaryIndexTree() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        tree = new int[n+1]; // tree[x] = Ax+前lowBit的个数，如ax+ax-1+ax-2+..

        for (int i = 1; i <= n ; i++){
            int num = sc.nextInt();
            pointUpdate(i , num);
        }

        for (int i = 0; i < m ; i++) {
            int operator = sc.nextInt(), x = sc.nextInt(), y = sc.nextInt();
            if (operator == 1) pointUpdate(x, y); // 将x+y
            else if (operator == 2) System.out.println(rangeSum(x, y));
        }
    }

    private int rangeSum(int x, int y){
        return sum(y) - sum(x-1);
    }

    private void pointUpdate(int x, int k) {
        while (x <= n){
            tree[x] += k;
            x += low_bit(x);
        }
    }

    // 前x项的和
    private int sum(int x) {
        int ans = 0;
        while (x > 0){
            ans += tree[x];
            x -= low_bit(x);
        }
        return ans;
    }

    // 找到最低位一，所对应的十进制的值
    private int low_bit(int x){
        return x & -x;
    }


}
