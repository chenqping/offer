package bat.algorithms.search;

/*
 * 二分查找
 * */

public class BinSearch {

    public static void main(String[] args) {
        int array[] = {1, 3, 5, 7, 8, 12};
        System.out.println(binSearch(array, 5));
        System.out.println(binSearch(array, 9));
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
