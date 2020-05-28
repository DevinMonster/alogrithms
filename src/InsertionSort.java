public class InsertionSort {
    public static void insertionSort(int[] arr){
        for (int i = 0; i < arr.length ; i++) {
            int temp = arr[i], j = i;
            while (j>0 && temp < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
