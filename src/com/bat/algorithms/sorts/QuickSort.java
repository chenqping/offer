package bat.algorithms.sorts;


import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args){
        int[] input = {1,7,4,3,5,6,2};
        QuickSort(input);
        System.out.println(Arrays.toString(input));
    }

    public static void QuickSort(int[] array){
        QSort(array, 0, array.length-1);
    }

    public static void QSort(int[] array, int low, int high){
        if(low < high){
            int pivotLoc = Partition(array, low, high);
            QSort(array, low, pivotLoc-1);
            QSort(array, pivotLoc+1, high);
        }
    }

    /*
    *  Partition要完成两个任务：
    *  1.将pivot移到合适的位置上，即左边的元素比其小，右边的元素比其大；
    *  2.返回pivot的位置索引，方便下次Partition时作为分界线。
    * */
    public static int Partition(int[] array, int low, int high){
        int temp, pivot = array[low];
        while(low < high){
            while(low < high && array[high] >= pivot) high--;
            temp = array[low];
            array[low] = array[high];
            array[high] = temp;
            while(low < high && array[low] <= pivot) low ++;
            temp = array[low];
            array[low] = array[high];
            array[high] = temp;
        }
        return low;
    }
}
