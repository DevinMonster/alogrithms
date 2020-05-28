package last;

public class BruteForceNormal {

    public int largestArea(int[] height){
        int max = 0, n = height.length;
        for (int i = 0; i < n; i++) {
            int min_height = height[i];
            for (int j = i; j < n; j++) {
                // 找到当前区间最低高度
                for (int k = i+1; k <= j; k++) if (min_height > height[k]) min_height = height[k];

                int space = (j-i+1)*min_height;
                max = Math.max(space,max);
            }
        }
        return max;
    }
}
