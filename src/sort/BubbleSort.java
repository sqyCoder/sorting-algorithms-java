package sort;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        // 定义 bound, 作为 已排序区间和待排序区间的边界
        // [0, bound) 已排序区间
        // [bound, size) 待排序区间
        for (int bound = 0; bound < arr.length; bound++) {
            for (int cur = arr.length - 1; cur > bound; cur--) {
                if (arr[cur - 1] > arr[cur]) {
                    swap(arr, cur - 1, cur);
                }
            }
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,4,2,7,5};
        bubbleSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}