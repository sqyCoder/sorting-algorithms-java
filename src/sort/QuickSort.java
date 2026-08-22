package sort;

public class QuickSort{

    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    // 需要借助辅助函数递归. 递归是需要每次递归使参数更接近结束条件
    // [left, right] 描述了这个数组上要处理的区间范围.
    private static void quickSortHelper(int[] arr, int left, int right) {
        // 给出递归结束条件
        if (left >= right) {
            // 如果给定区间中, 只有 0 个或者 1 个元素时, 就认为是不必处理了.
            return;
        }
        // 对当前区间进行整理. 返回的 index 就是最后基准值的位置 (left 和 right 重合的位置)
        // 返回基准值的下标, 就进一步的知道了左右两个半区间是怎样的范围了.
        int index = partition(arr, left, right);
        quickSortHelper(arr, left, index - 1);
        quickSortHelper(arr, index + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        // 获取基准值. 取最右侧元素作为基准值即可.
        int value = arr[right];
        int l = left;
        int r = right;
        while (l < r) {
            // 从左往右, 找到第一个大于等于基准值的数字
            while (l < r && arr[l] < value) {
                l++;
            }
            // 循环结束之后, 要么是 l 和 r 重合了, 要么是 l 指向的元素已经 >= 基准值了.

            // 从右往左找, 找到第一个小于基准值的数字.
            while (l < r && arr[r] >= value) {
                r--;
            }
            // 循环结束之后, 要么是 l 和 r 重合了, 要么是 r 指向的元素, 已经 < 基准值.
            // 交换 l 和 r 位置的元素
            swap(arr, l, r);
        }
        // 最终需要再 l 和 r 重合的时候, 把重合位置的元素和最后一个元素 (基准值) 进行交换
        // 这个交换, 确保基准值被放到中间的边界上.
        swap(arr, l, right);
        return l;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {6, 2, 7, 3, 9, 1, 5};
        quickSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}