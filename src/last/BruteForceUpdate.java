package last;

public class BruteForceUpdate {
    public int largestArea(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int max = height[0];
        for (int i = 0; i < n; i++) {
            int min = height[i];
            for (int j = i; j < n; j++) {
                // 比较当前高度和上一个高度,求出两者中更小的
                min = Math.min(min, height[j]);
                // 算出当前的面积
                int area_now = min*(j - i + 1);
                // 比较当前面积和最大面积
                max = Math.max(area_now, max);
            }
        }
        return max;
    }
}

