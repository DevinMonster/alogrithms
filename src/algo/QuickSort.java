package algo;

public class QuickSort {


    public static void randomQuickSort(int[] arr){
        randomQuickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    private static void randomQuickSort(int[] arr, int start, int end){
        if (start < end){
            int j = ran_partition(start, end, arr);
            randomQuickSort(arr, start, j-1);
            randomQuickSort(arr, j+1, end);
        }
    }

    private static void quickSort(int[] arr, int start, int end){
        if (start < end){
            int j = partition(start, end, arr);
            quickSort(arr, start, j-1);
            quickSort(arr, j+1, end);
        }
    }

    public static int ran_partition(int start, int end, int[] arr){
        int rd = (int) (Math.random()*(end-start+1))+start;
        swap(rd,start,arr);
        return partition(start, end, arr);
    }

    // 一趟快排
    public static int partition(int start, int end, int[] arr){
        int pivot = arr[start], i = start + 1, j = end;
        for(;i <= j; i++){
            if (arr[i] > pivot){
                for (;j >= i;j--) if (arr[j] < pivot) break;
                if(i < j) swap(i,j,arr);
                else break;
            }
        }
        swap(start, j, arr);
        return j;
    }

    public static void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
