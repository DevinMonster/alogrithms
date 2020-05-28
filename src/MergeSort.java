public class MergeSort {

    private static void merge(int[] a, int[] b, int l, int mid, int r){
        int i = l, j = mid+1, t = l;
        while (i <= mid && j <= r){
            if (a[i] < a[j]) b[t++] = a[i++];
            else b[t++] = a[j++];
        }
        while (i<=mid) b[t++] = a[i++];
        while (j<=r) b[t++] = a[j++];
        for (int k = l; k <= r ; k++) a[k] = b[k];
    }

    private static void mergeSort(int[] a, int[] b, int l, int r){
        if (l < r){
            int mid = (l+r) >> 1;
            mergeSort(a, b, l, mid);
            mergeSort(a, b, mid+1, r);
            merge(a, b, l, mid, r);
        }
    }

    public static void mergeSort(int[] a){
        int[] b = new int[a.length];
        mergeSort(a,b, 0, a.length-1);
    }


}
