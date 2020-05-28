package last;


public class DynamicProgramming {

    public int largestArea(int[] height){
        return largestArea(height, height.length);
    }

    private int largestArea(int[] height, int n) {
        int[]left = new int[n];
        int[]right = new int[n];
        left[0] = 0;
        right[n-1] = n-1;

        for (int i = 1; i < n; i++) {
            // 找到最左边的比它大的元素
            int last = i;
            while (last >= 1 && height[i] <= height[last-1]) last = left[last-1];
            left[i] = last;
        }

        for (int i = n-2; i >= 0; i--) {
            // 找到最右边的比它大的元素
            int last = i;
            while (last < n - 1 && height[i] <= height[last+1]) last = right[last+1];
            right[i] = last;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) ans = Math.max(ans,(right[i]-left[i]+1)*height[i]);

        return ans;
    }


    public static void main(String[] args) {
        DynamicProgramming test = new DynamicProgramming();
    }
}
