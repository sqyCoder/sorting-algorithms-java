package sort;

public class ShellSort {
    // 分组插入排序，供shellSort调用
    public static void insertSortForGap(int[] arr, int gap) {
        for (int bound = gap; bound < arr.length; bound++) {
            int value = arr[bound];
            int cur = bound - gap;
            for (; cur >= 0; cur -= gap) {
                if (arr[cur] > value) {
                    arr[cur + gap] = arr[cur];
                } else {
                    break;
                }
            }
            arr[cur + gap] = value;
        }
    }

    // 希尔排序
    public static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        while (gap > 1) {
            insertSortForGap(arr, gap);
            gap /= 2;
        }
        insertSortForGap(arr, 1);
    }

    public static void main(String[] args) {
        int[] arr = {9,5,2,7,3,6,8,1};
        shellSort(arr);
        for(int x : arr){
            System.out.print(x + " ");
        }
    }
}