package sort;

import java.util.Stack;

public class QuickSortByLoop{

    public static void quickSortByLoop(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        // 把根节点入栈
        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.empty()) {
            // 取出要处理的区间
            int right = stack.pop();
            int left = stack.pop();
            if (left >= right) {
                // 此区间是 空区间 或者 只有一个元素
                continue;
            }
            int index = partition(arr, left, right);
            // 把右侧区间入栈
            stack.push(index + 1);
            stack.push(right);
            // 把左侧区间入栈
            stack.push(left);
            stack.push(index - 1);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int value = arr[right];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && arr[l] < value) {
                l++;
            }
            while (l < r && arr[r] >= value) {
                r--;
            }
            swap(arr, l, r);
        }
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
        quickSortByLoop(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}