package algo;

import java.util.Arrays;

/**
 * @author Devin
 */
public class PriorityQueue {
    private final int[] arr;
    private int size;
    public static final int MAX_HEAP = 0;
    public static final int MIN_HEAP = 1;

    public PriorityQueue(int[] give,int type) {
        arr = Arrays.copyOf(give,give.length);
        if (type == MAX_HEAP) {
            createMaxHeap(arr);
        }else if (type == MIN_HEAP){

        }
    }

    private void createMaxHeap(int[] give){
        for (int i = getParent(give.length - 1); i >= 0 ; i--) {
            shiftDownMax(i);
        }
    }

    private void createMinHeap(int[] give){
        for (int i = getParent(give.length - 1); i >= 0 ; i--) {
            shiftDownMax(i);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == arr.length - 1;
    }

    private int getLeftChild(int pos){
        return 2*pos;
    }

    private int getRightChild(int pos){
        return 2*pos+1;
    }

    private int getParent(int pos) {
        if (pos <= 0) throw new IllegalArgumentException("pos cant <= 0");
        return pos >> 1;
    }

    private void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int findMax(){
        if (isEmpty()) throw new IllegalArgumentException("size = 0");
        return arr[0];
    }

    public int extractMax(){
        int ret = findMax();
        swap(0,size-1);
        // 移走要ret的元素
        arr[size-1] = 0;
        shiftDownMax(0);
        return ret;
    }

    public void add(int n){
        if (isFull()) throw new IllegalArgumentException("is full");
        arr[size] = n;
        shiftUp(size++);
    }

    private void shiftUp(int i) {
        // i不能是根节点，并且i的值要比父节点的值大
        while (i > 0 && arr[getParent(i)] < arr[i]) {
            swap(i,getParent(i));
            i = getParent(i);
        }
    }

    private void shiftDownMax(int i) {
        // 当左孩子存在
        while (getLeftChild(i) < size){
            int j = getLeftChild(i);
            // 右孩子存在且大于左孩子的值 j + 1 为右孩子,j为左孩子
            if (j + 1 < size && arr[j+1] > arr[j] ) j = getRightChild(i);
            // 若arr[j] 是左孩子或右孩子中最大的
            if (arr[i] >= arr[j]) break;
            swap(i,j);
            i = j;
        }
    }

    private void shiftDownMin(int i) {
        // 当左孩子存在
        while (getLeftChild(i) < size){
            int j = getLeftChild(i);
            // 右孩子存在且大于左孩子的值 j + 1 为右孩子,j为左孩子
            if (j + 1 < size && arr[j+1] > arr[j] ) {
                j = getRightChild(i);
            }
            // 若arr[j] 是左孩子或右孩子中最大的
            if (arr[i] >= arr[j]) {
                break;
            }
            swap(i,j);
            i = j;
        }
    }
}
