package sort;

/**
 * 直接插入排序
 */
public class InsertSort {
    /**
     * 直接插入排序
     * @param arr 待排序的int数组
     */
    public static void insertSort(int[] arr) {
        // bound：边界下标，bound位置是待排序区间的第一个元素；
        // bound左边[0,bound‑1]是已排序区间，bound及右边是待排序区间。
        // bound从1开始，下标0默认属于已排序部分。循环直到遍历完整个数组。
        for (int bound = 1; bound < arr.length; bound++) {
            // 取出本次要向前插入的元素bound位置的值，存到value变量。
            // 必须提前保存，后续前面元素往后搬运会覆盖arr[bound]，不保存值就丢失。
            int value = arr[bound];
            // cur指向已排序区间的最后一个元素，也就是bound的前一个位置，从此处开始向前比较。
            int cur = bound - 1;
            // for循环，cur不断向前走，cur >= 0 防止下标越界，走到数组头部就停止。
            for (; cur >= 0; cur--) {
                // 如果已排序区间当前位置元素 大于 待插入的value
                if (arr[cur] > value) {
                    // 触发搬运：把cur位置的元素向后挪一格，给value腾出插入空位。
                    arr[cur + 1] = arr[cur];
                } else {
                    // 找到插入位置，直接跳出内层循环，不再继续向前比较。
                    break;
                }
            }
            // cur+1 就是value正确的插入位置，把保存好的value放入该位置。
            arr[cur + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,2,4,6,1,3};
        insertSort(arr);
        for(int num : arr){
            System.out.print(num + " ");
        }
    }
}