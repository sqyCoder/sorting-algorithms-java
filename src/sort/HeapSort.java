package sort;

public class HeapSort {

    // 升序排序, 建立大堆.
    public static void heapSort(int[] arr) {
        // 1. 建立堆
        createHeap(arr);
        // 2. 循环的删除堆顶元素. (删除其实是把堆顶和堆的最后一个元素交换, 并 size--)
        int heapSize = arr.length;
        for (int i = 0; i < arr.length - 1; i++) {
            swap(arr, 0, heapSize - 1);
            heapSize--;
            // 从 0 号位置进行向下调整
            shiftDown(arr, heapSize, 0);
        }
    }

    // 为了进行堆排序, 需要先实现建堆
    private static void createHeap(int[] arr) {
        for (int i = (arr.length - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(arr, arr.length, i);
        }
    }

    private static void shiftDown(int[] arr, int size, int index) {
        int parent = index;
        int child = 2 * parent + 1;
        while (child < size) {
            // 找左右孩子中更大的那一个
            if (child + 1 < size && arr[child + 1] > arr[child]) {
                child = child + 1;
            }
            if (arr[child] > arr[parent]) {
                // 不符合大堆规则，交换父子
                swap(arr, child, parent);
            } else {
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        }
    }

    // 交换数组两个下标位置元素
    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 9, 2, 7, 3, 6, 1};
        heapSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}