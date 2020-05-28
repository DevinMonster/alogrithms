import java.util.Scanner;

public class RangeUpdate {
    int[] tree;
    Node root;

    class Node{
        int start, end;
        Node left, right;
        int tag, sum;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        RangeUpdate p = new RangeUpdate();
    }

    public RangeUpdate() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        tree = new int[n+1];
        for (int i = 1; i <= n ; i++) tree[i] = sc.nextInt();

        root = buildTree(1,n); // 建造线段树

        for (int i = 0; i < m ; i++) {
            int operator = sc.nextInt(), x = sc.nextInt(), y = sc.nextInt();
            if (operator == 1){
                int k = sc.nextInt();
                rangeUpdate(root, x, y, k);
            } else if (operator == 2) System.out.println(rangeSum(root, x, y));
        }
    }

    private Node buildTree(int start, int end) {
        Node current = new Node(start, end);
        // 左右区间位置相同，代表为叶子节点
        if (start == end) current.sum = tree[start]; // 单个的和既是它本身。
        else {
            int mid = (start + end) >> 1; // 找到中间位置
            // 递归的去建树
            Node left = buildTree(start, mid), right = buildTree(mid + 1, end);
            current.left = left;
            current.right = right; // 连接左右子树
            current.sum = current.left.sum + current.right.sum; // 求出最后的区间和
        }
        return current;
    }

    private int rangeSum(Node current, int range_start, int range_end) {
        // 如果是恰好覆盖区间， 直接返回区间值即可
        if (range_start == current.start && range_end == current.end) return current.sum;
        // 如果当前节点被懒标记过，向下调整到左右孩子的标签上
        if (current.tag != 0) push_down(current);
        // 找出中点值，判断区间所处的位置
        int mid = (current.start + current.end) >> 1;
        // 区间都在左子树, 返回从range_start到range_end的区间和
        if (range_end <= mid) return rangeSum(current.left, range_start, range_end);
        // 右边同理
        if (range_start > mid) return rangeSum(current.right, range_start, range_end);
        // 否则就在两边都有, 分别分配到两个区间中求和然后在加起来返回
        return rangeSum(current.left, range_start, mid) + rangeSum(current.right, mid + 1, range_end);
    }

    private void rangeUpdate(Node current, int range_start, int range_end, int value) {
        // 如果执行更新的区间恰好被覆盖
        if (current.start == range_start && current.end == range_end){
            current.tag += value; // 更新一下懒标记的值
            current.sum += value * (range_end - range_start + 1); // 更新一下后边代表区间长度，也就是叶子节点的个数
        }else {
            // 如果执行更新时,我们的tag有值，代表前边更新过，那就要向下调整
            if (current.tag != 0) push_down(current);

            int mid = (current.start + current.end) >> 1;
            // 否则我们的区间肯定可分
            if (range_end <= mid) rangeUpdate(current.left, range_start, range_end, value);
            else if (range_start > mid) rangeUpdate(current.right, range_start, range_end, value);
            else {
                rangeUpdate(current.left, range_start, mid, value) ;
                rangeUpdate(current.right, mid + 1, range_end, value);
            }
            // 最后算出sum
            current.sum = current.left.sum + current.right.sum;
        }

    }

    private void push_down(Node current) {
        // 更新左右孩子的tag
        current.left.tag += current.tag;
        current.right.tag += current.tag;
        // 更新左右孩子的sum值
        current.left.sum += current.tag*(current.left.end - current.left.start + 1);
        current.right.sum += current.tag*(current.right.end - current.right.start + 1);
        // 清空标记
        current.tag = 0;
    }

}
