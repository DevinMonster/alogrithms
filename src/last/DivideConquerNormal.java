package last;

public class DivideConquerNormal {

    public int largestArea(int[] height) {
        return largestArea(0, height.length - 1, height);
    }

    private int largestArea(int start, int end, int[] height) {
        // 处理边界条件
        if (start > end) return 0;
        if (start == end) return height[start];
        // 计算出长度
        int len = end - start + 1;
        // 取得最小的位置
        int index = getMinIndex(start, end, height);
        // 先算包括最矮的的面积
        int area = height[index] * len;
        // 算出最小左边的面积比较
        if (index > start) area = Math.max(area, largestArea(start, index - 1, height));
        // 算出最小的右边的面积比较
        if (index < end) area = Math.max(area, largestArea(index + 1, end, height));
        return area;
    }

    private int getMinIndex(int start, int end, int[] height) {
        int min = height[start], mini = start;
        for (int i = start + 1; i <= end; i++) {
            if (min > height[i]) {
                min = height[i];
                mini = i;
            }
        }
        return mini;
    }


    public static void main(String[] args) {
        DivideConquerNormal test = new DivideConquerNormal();
    }
}
