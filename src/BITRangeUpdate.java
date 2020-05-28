import java.util.Scanner;

public class BITRangeUpdate {
    int[] tree, A; // 差分数组, 原始值
    int n, m;

    public static void main(String[] args) {
        BITRangeUpdate bit = new BITRangeUpdate();
    }

    public BITRangeUpdate() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        A = new int[n + 1];
        tree = new int[n + 1]; // tree[x] = Ax+前lowBit的个数，如ax+ax-1+ax-2+..

        for (int i = 1; i <= n; i++) A[i] = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int operator = sc.nextInt();
            if (operator == 1) {
                int x = sc.nextInt(), y = sc.nextInt(), k = sc.nextInt();
                update(x, k);
                update(y+1, -k);
                // 将x+y
            } else if (operator == 2) {
                int x = sc.nextInt();
                System.out.println(sum(x)+A[x]);
            }
        }
    }

    private int sum(int x) {
        int ans = 0;
        while (x > 0){
            ans += tree[x];
            x -= low_bit(x);
        }
        return ans;
    }

    private void update(int x, int k) {
        while (x <= n){
            tree[x] += k;
            x += low_bit(x);
        }
    }

    // 找到最低位一，所对应的十进制的值
    private int low_bit(int x) {
        return x & -x;
    }


}
