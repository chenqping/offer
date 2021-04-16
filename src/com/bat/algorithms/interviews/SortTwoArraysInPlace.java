package bat.algorithms.interviews;

/*
*   字节一面算法题：原地对两个有序数组进行排序，使得它们全局有序，空间复杂度要求不超过O(1)，时间复杂度不限
*   使用选择排序较为简单，只需要转换下数组下标即可
* */

import java.util.Arrays;

public class SortTwoArraysInPlace {
    public static void main(String[] args){
        int[] a = {1, 5, 9, 10, 15, 20};
        int[] b = {2, 3, 8, 13};
        SortTwoArrays(a , b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    public static void SortTwoArrays(int[] a, int[] b){
        for(int i = 0; i < a.length + b.length; i++){
            int index = i, min;
            if(i < a.length){
                min = a[i];
                for(int j = i + 1; j < a.length + b.length; j++){
                    if(j < a.length){
                        if(a[j] < min){
                            min = a[j];
                            index = j;
                        }
                    }else{
                        if(b[j-a.length] < min){
                            min = b[j-a.length];
                            index = j;
                        }
                    }
                }
                if(index < a.length){
                    a[index] = a[i];
                }else{
                    b[index-a.length] = a[i];
                }
                a[i] = min;
            }else{
                min = b[i-a.length];
                for(int j = i + 1; j < a.length + b.length; j++){
                    if(b[j - a.length] < min ){
                        min = b[j-a.length];
                        index = j;
                    }
                }
                b[index-a.length] = b[i-a.length];
                b[i-a.length] = min;
            }
        }
    }
}
