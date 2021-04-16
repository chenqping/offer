package bat.algorithms.interviews;

import java.util.Arrays;

/*
*  当年腾讯校招一面算法题，对数组中的元素进行移位操作，当时只想到了通过循环右往左移动元素来实现，这个是基本的解法，
*  如果能够想到将数组分成前后两部分，后半部分就是要移到前面的元素，那么可以分别对两部分进行逆序操作，然后再对整个
*  数组进行逆序就实现了对数组的循环右移。这个解法应该会使面试官眼前一亮。
* */

public class ArrayShift {
    public static void main(String[] args){
        int[] a = {4, 5, 6, 7, 8, 1, 2, 3};
        rshift(a, 5);
        System.out.println(Arrays.toString(a));
    }

    /*
    *  逆序数组中start索引开始，长度为length的片段
    * */
    public static void reverse(int[] array, int start, int length){
        for(int i = 0; i <  length / 2; i++){
            int temp = array[start + i];
            array[start + i] = array[start + length -1 - i];
            array[start + length - 1 -i] = temp;
        }
    }

    public static void rshift(int[] array, int num){
        reverse(array, 0, num);
        reverse(array, num , array.length - num);
        reverse(array, 0, array.length);
    }
}
