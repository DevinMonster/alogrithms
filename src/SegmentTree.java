import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class SegmentTree {

    int n, m;
    int[] data;

    class Node {
        private int left, right;
        private int sum, max;
        private Node left_child, right_child;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        SegmentTree p = new SegmentTree();
    }

    public SegmentTree() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        data = new int[n + 1];
        for (int i = 1; i <= n; i++) data[i] = sc.nextInt();
        Node root = build(1, n);

        for (int i = 0; i < m; i++) {
            int operator = sc.nextInt(), x = sc.nextInt(), y = sc.nextInt();// 3种查询
            if (operator == 1) {//U
                updateOne(root, x, y);
            } else if (operator == 2) {//
                System.out.println(querySum(root, x, y));
            } else {
                System.out.println(queryMax(root, x, y));
            }
        }
    }

    public void updateOne(@NotNull Node node, int index, int value) {
        // 叶子节点
        if (node.right_child == null && node.left_child == null) {
            node.sum = value;
            node.max = value;
            return;
        }
        // 两种状态，无非是在左子树要不就是在右子树
        int mid = (node.left + node.right) / 2;
        if (index<=mid) updateOne(node.left_child, index, value);
        else updateOne(node.right_child, index, value);

        // 更新统计量
        node.sum = node.left_child.sum + node.right_child.sum;
        node.max = Math.max(node.left_child.max, node.right_child.max);
    }

    private @NotNull Node build(int l, int r) { // 构造线段树
        Node current = new Node(l, r); // 当前节点代表l-r区间

        if (l == r) { // 叶子节点时
            current.max = data[l];
            current.sum = data[l];
        } else {
            int mid = (l + r) / 2; // 分界线
            Node left = build(l, mid); // 左子树
            Node right = build(mid + 1, r); // 右子树
            // 连接左右子树
            current.left_child = left;
            current.right_child = right;
            // 计算统计量
            current.sum = left.sum + right.sum;
            current.max = Math.max(left.max, right.max);
        }

        return current;
    }

    public int queryMax(@NotNull Node current, int left_bound, int right_bound) {
        if (current.left == left_bound && current.right == right_bound) return current.max; //如果是当前区间之接返回值

        int node_mid = (current.left + current.right) / 2;// 取出中点

        if (right_bound <= node_mid) return queryMax(current.left_child, left_bound, right_bound); //完全在左
        if (left_bound > node_mid) return queryMax(current.right_child, left_bound, right_bound); // 完全在右
        // 既有左又有右
        return Math.max(queryMax(current.left_child, left_bound, node_mid), queryMax(current.right_child, node_mid + 1, right_bound));
    }

    // 相似于上边
    public int querySum(@NotNull Node current, int left_bound, int right_bound) { // [left,right]
        if (current.left == left_bound && current.right == right_bound) return current.sum;

        int node_mid = (current.left + current.right) / 2;

        if (right_bound <= node_mid) return querySum(current.left_child, left_bound, right_bound);
        if (left_bound > node_mid) return querySum(current.right_child, left_bound, right_bound);

        return querySum(current.left_child, left_bound, node_mid) + querySum(current.right_child, node_mid + 1, right_bound);
    }


}
