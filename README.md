# 七大排序算法（手写实现）

> 七种排序，从零手写，不调 `Arrays.sort`。**7 个文件、7 个类**，每一种都附复杂度分析和「什么时候用它」。

<p>
  <img alt="Java" src="https://img.shields.io/badge/Java-7%20种排序-ED8B00?style=flat-square&logo=openjdk&logoColor=white">
  <img alt="依赖" src="https://img.shields.io/badge/依赖-0-brightgreen?style=flat-square">
</p>

---

## 为什么手写排序

因为 `Arrays.sort` 太方便，方便到你永远不知道自己不懂排序。

面试考排序，考的不是「你会不会写快排」，而是：

- 你知不知道**快排在什么输入下退化到 O(n²)**，以及怎么避免；
- 你知不知道**归并为什么稳定、快排为什么不稳定**；
- 你知不知道**堆排序的建堆为什么是 O(n) 而不是 O(n log n)**。

这些问题的答案，只有自己一行一行写出来才会真的记住。

---

## 七种排序

| 文件 | 算法 | 最好 | 平均 | 最坏 | 空间 | 稳定 |
| --- | --- | --- | --- | --- | --- | --- |
| [BubbleSort.java](src/sort/BubbleSort.java) | 冒泡排序 | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| [SelectSort.java](src/sort/SelectSort.java) | 直接选择排序 | O(n²) | O(n²) | O(n²) | O(1) | ❌ |
| [InsertSort.java](src/sort/InsertSort.java) | 直接插入排序 | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| [ShellSort.java](src/sort/ShellSort.java) | 希尔排序 | O(n log n) | 取决于增量 | O(n²) | O(1) | ❌ |
| [QuickSort.java](src/sort/QuickSort.java) | 快速排序（递归） | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| [QuickSortByLoop.java](src/sort/QuickSortByLoop.java) | 快速排序（栈模拟） | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| [HeapSort.java](src/sort/HeapSort.java) | 堆排序 | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ |

**记忆锚点**：

- 只有冒泡和插入是「最好情况 O(n)」——因为它们在已经有序时会提前退出。
- 只有堆排序的最坏情况也是 O(n log n) ——这是它相对快排唯一的优势。
- 稳定的只有冒泡和插入，简单选择、希尔、快排、堆排都不稳定。

---

## 几个实现上的细节

### 冒泡用的是「已排序区间」写法

```java
for (int bound = 0; bound < arr.length; bound++) {
    for (int cur = arr.length - 1; cur > bound; cur--) {
        if (arr[cur - 1] > arr[cur]) swap(arr, cur - 1, cur);
    }
}
```

`bound` 是**已排序区间和待排序区间的边界**：`[0, bound)` 已排好、`[bound, size)` 待排。从后往前冒，每一轮把最小的顶到 `bound` 位置。

> 从后往前而不是从前往后，是刻意的：这样每一轮确定的是**第 bound 小的元素**，和「选择排序」的语义一致，两种算法对照着看很清楚。

### 快排的两个版本

`QuickSort.java` 是递归版，`QuickSortByLoop.java` 用显式 `Stack` 模拟递归。

**为什么要有非递归版**：递归深度最坏是 O(n)，在极端输入下会导致栈溢出。用栈模拟就把「系统调用栈」换成了「堆内存里的栈」，虽然本质上还是 O(n) 空间，但不受线程栈大小限制。

> 这是面试常问的「快排怎么改成非递归」的标准答法。自己写过一遍，就不会临场编。

### 堆排序的建堆是 O(n)

```java
// 升序排序 → 建大堆
createHeap(arr);
for (int i = 0; i < arr.length - 1; i++) {
    swap(arr, 0, heapSize - 1);  // 堆顶（最大值）换到末尾
    heapSize--;
    shiftDown(arr, heapSize, 0); // 重新下沉
}
```

建堆看起来要对 n/2 个节点做下沉、每次 O(log n)，应该是 O(n log n)——但**实际上大部分节点都在底层，下沉深度很小**，求和之后是 O(n)。

> 这个结论如果只是背下来，面试官追问「为什么」就露馅了。建议自己数一遍：高度为 h 的节点有 n/2^(h+1) 个，每个最多下沉 h 层。

---

## 目录结构

```
src/sort/
├── BubbleSort.java        冒泡排序
├── SelectSort.java        直接选择排序
├── InsertSort.java        直接插入排序
├── ShellSort.java         希尔排序（分组插入）
├── QuickSort.java         快速排序（递归）
├── QuickSortByLoop.java   快速排序（栈模拟）
└── HeapSort.java          堆排序
```

---

## 怎么跑

每个类都是独立可运行的（`main` 里带随机数组验证）：

```bash
cd src
javac sort/*.java
java sort.QuickSort
```

---

## 学排序的正确顺序

不要按「难度」排，按**依赖关系**排：

```
1. 冒泡 / 选择 / 插入     ← 三个 O(n²)，理解「有序区间」这个概念
        ↓
2. 希尔排序               ← 插入排序的改进，理解「增量」的作用
        ↓
3. 快速排序               ← 分治，理解 partition
        ↓
4. 堆排序                 ← 需要先会「堆」这个数据结构
```

**快排和堆排序不要连着学**。它们背后是两套完全不同的思维（分治 vs 完全二叉树），连着学会混。

---

## 还缺什么

这个仓库只有排序。如果要补齐算法基础，接下来该写的是：

- **归并排序**（分治的另一面，稳定排序的代表）
- **计数 / 基数排序**（非比较排序，理解「O(n) 排序为什么可能」）
- **二分查找的各种变体**（找左边界、找右边界、找第一个大于等于）

---

## License

个人学习存档，代码可自由参考。
