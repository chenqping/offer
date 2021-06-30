package bat.algorithms.search;

/*
 * 二分查找
 * */

public class BinSearch {

    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 7, 8, 12};
        int[] array2 = {1};
        System.out.println(binSearch(array1, 3));
        System.out.println(binSearch(array1, 9));
        System.out.println(binSearch(array1, 12));
        System.out.println(binSearch(array2, 1));
        System.out.println(binSearch(array2, 2));
    }

    public static int binSearch(int[] array, int key) {
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < array[mid]) {
                high = mid - 1;
            } else if (key > array[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
