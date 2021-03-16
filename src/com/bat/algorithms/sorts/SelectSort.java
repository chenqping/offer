package bat.algorithms.sorts;

/*
 *   问题说明： 使用选择排序算法对数组进行排序
 *   考察点： 编程功底，对选择排序的掌握
 * */

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args){
        int[] a = {3, 2, 1, 5, 4, 6};
        int[] b = {3, 2, 3, 5, 4, 4};
        selectSort(a);
        selectSort(b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    public static void selectSort(int[] array){
        for(int i = 0; i < array.length; i++){
            int index = i;
            int min = array[i];
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < min){
                    min = array[j];
                    index = j;
                }
            }
            array[index] = array[i];
            array[i] = min;
        }
    }
}
