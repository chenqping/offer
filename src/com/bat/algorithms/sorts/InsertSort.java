package bat.algorithms.sorts;

import java.util.Arrays;

/*
 *   问题说明： 使用插入排序算法对数组进行排序
 *   考察点： 编程功底，对插入排序的掌握
 * */

public class InsertSort {
    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 4, 6};
        int[] b = {3, 2, 3, 5, 4, 4};
        insertSort(a);
        insertSort(b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            for (; j >= 0 && array[j] > temp; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
    }
}
