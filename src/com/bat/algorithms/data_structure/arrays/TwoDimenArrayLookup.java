package bat.algorithms.data_structure.arrays;

/*
    剑指offer 2.3.1节二维数组查找练习
 */

public class TwoDimenArrayLookup {
    public static void main(String[] args){
        int[][] tdarr = {{1,2,3},{4,5,6},{7,8,9},{11,12,13}};
//        for(int i = 0; i <tdarr.length; i++){
//            for(int j = 0; j<tdarr[i].length; j++){
//                System.out.println(tdarr[i][j]);
//            }
//        }
        int[][] narr = null;
        System.out.println(find(tdarr, 1));
        System.out.println(find(tdarr, 12));
        System.out.println(find(tdarr, 10));
        System.out.println(find(tdarr, 15));
        System.out.println(find(narr, 15));
    }
    public static boolean find(int[][] arr, int target){
        if(arr == null)
            return false;
        for(int i=0; i < arr.length; i++){
            for(int j=arr[i].length-1; j>=0; j--){
                if(arr[i][j] == target)
                    return true;
                if(arr[i][j] < target){
                    break;
                }
            }
        }
        return false;
    }
}
