package algo;

import java.util.Scanner;

public class UnionFind {
    private int[] parent;

    public void buildSet(int n,int pair,Scanner sc){
        parent = new int[n+1];
        for (int i = 1; i <=n ; i++) parent[i] = i;//初始化树
        // 如果在同一集合
        for (int i = 0; i < pair ; i++) {
            int one = sc.nextInt(), two = sc.nextInt();
            if (!isSameSet(one, two)) union(one,two);
        }

    }


    private boolean isSameSet(int one, int two) {
        return findRoot(one) == findRoot(two);
    }

    private int findRoot(int num) {
        int i = num;
        while (parent[i] != i) i = parent[i];
        parent[num] = i;
        return parent[num];
    }

    private void union(int one, int two) {
        int root_one = findRoot(one);
        int root_two = findRoot(two);
        // 可以做深度的优化
        parent[root_one] = root_two; // 把one集合连到two上
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // n个人，pair个亲戚关系，询问query对亲戚关系。
        int n = sc.nextInt();
        int pair = sc.nextInt();
        int query = sc.nextInt();
        UnionFind uf = new UnionFind();
        uf.buildSet(n,pair,sc);
        for (int i = 0; i < query ; i++) {
            int x = sc.nextInt(),y = sc.nextInt();
            if (uf.isSameSet(x,y)) System.out.println("Yes");
            else System.out.println("No");
        }
    }

}
