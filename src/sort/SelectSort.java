package sort;

public class SelectSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //直接选择排序
    //定义 bound 表示 "边界"
    //[0, bound) 已排序区间
    //[bound, size) 待排序区间
    //按照 "打擂台" 的方式, 每一轮都选出当前待排序区间中, 最小的元素, 放到 bound 位置上.
    public static void selectSort(int[] arr) {
        //此处需要让 bound 从 0 开始走.
        //第一轮是要把最小值放到 bound (0) 位置上.
        for (int bound = 0; bound < arr.length; bound++) {
            //此时 bound 位置就是 擂台 .
            //从 bound + 1 的位置, 依次让挑战者上场
            for (int cur = bound + 1; cur < arr.length; cur++) {
                if (arr[cur] < arr[bound]) {
                    //直接赋值不合适.
                    //原来 bound 位置的元素就没了
                    //arr[bound] = arr[cur];
                    //这里采取交换的方式.
                    swap(arr, bound, cur);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,2,9,3,7,6,1,8};
        selectSort(arr);
        for(int num : arr){
            System.out.print(num+" ");
        }
    }
}