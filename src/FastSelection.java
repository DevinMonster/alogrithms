import java.util.Scanner;

public class FastSelection {
    int n, k;
    int[] arr;

    public static void main(String[] args) {
        FastSelection fastSelection = new FastSelection();
    }

    public FastSelection() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n ; i++) arr[i] = sc.nextInt();

        System.out.println(fastSelection(0,n-1,k));
    }

    // 一趟快排
    int fastSelection(int start, int end, int k){
        int i = start + 1,j = end;
        while (true) {
            // arr[start]主元
            while (i <= j && arr[i] < arr[start]) i++;
            while (i <= j && arr[j] > arr[start]) j--;
            if (i < j) swap(i, j);
            else break;
        }
        swap(start, j);
        int last = end-j;// 后边的数
        if (k == last + 1) return arr[j];
        if (k < last + 1) return fastSelection(j + 1, end, k);
        return fastSelection(start, j-1,k-(last+1));
    }

    void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
