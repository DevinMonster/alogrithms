package last;


public class DivideConSegTree {

    class Node {
        private int left, right; // 闭区间 左 右 端点
        private int mini;// 当前区间的最小值的下标
        private Node left_child, right_child;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private Node build(int l, int r, int[] height) { // 构造线段树
        if (l > r) return null;
        Node current = new Node(l, r); // 当前节点代表l-r区间

        if (l == r) { // 叶子节点时
            current.mini = l;
        } else {
            int mid = (l + r) >> 1; // 分界线
            Node left = build(l, mid, height); // 左子树
            Node right = build(mid + 1, r, height); // 右子树
            // 连接左右子树
            current.left_child = left;
            current.right_child = right;
            // 找出最小楼房下标
            if (height[left.mini] < height[right.mini]) current.mini = current.left_child.mini;
            else current.mini = right.mini;
        }

        return current;
    }

    public int largestArea(int[] height) {
        // 边界条件
        if(height == null) return 0;
        int n = height.length;
        if (n == 0) return 0;

        Node root = build(0, n - 1, height);
        return calculateMaxArea(height, root, 0, n - 1);
    }

    public int calculateMaxArea(int[] height, Node root, int left_bound, int right_bound) {
        if (left_bound > right_bound) return 0;
        // 如果查到了当前的高度直接返回面积
        if (left_bound == right_bound) return height[left_bound];
        // 找到最矮的下标
        int mini = queryMinIndex(root, left_bound, right_bound, height);
        // 计算左边的最大面积
        int leftMaxArea = calculateMaxArea(height, root, left_bound, mini - 1);
        // 计算右边的最大面积
        int rightMaxArea = calculateMaxArea(height, root, mini + 1, right_bound);
        // 计算包括最矮的面积
        int minMaxArea = height[mini] * (right_bound - left_bound + 1);
        // 找出三个面积中最大的
        return Math.max(Math.max(minMaxArea, leftMaxArea), rightMaxArea);
    }

    public int queryMinIndex(Node current, int left_bound, int right_bound, int[] height) {
        // 边界情况
        if (current == null || right_bound < current.left || left_bound > current.right) return -1;
        if (current.left >= left_bound && current.right <= right_bound) return current.mini; // 如果是当前区间之接返回值

        // 找到左右两个最矮的建筑的下标
        int left_mini = queryMinIndex(current.left_child, left_bound, right_bound, height);
        int right_mini = queryMinIndex(current.right_child, left_bound, right_bound, height);
        // 左边没有建筑了
        if (left_mini == -1) return right_mini;
        // 右边同理
        if (right_mini == -1) return left_mini;
        // 否则
        if (height[left_mini] < height[right_mini]) return left_mini;
        else return right_mini;
    }

}
