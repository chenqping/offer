package bat.algorithms.data_structure.arrays;

/*
    剑指offer 2.3.1节二维数组查找练习
    题目说明：
    二维数组中每行从左到右递增，从上到下递增，给出一个整数查找数组中是否存在该整数。
    算法说明：
    算法采用的是每次比较数组右上角的数字和目标的大小，如果等于目标则查找成功，如果大于目标，则说明要目标
    不可能在最后一列，因为右上角已经是该列最小的数了，所以排除；如果小于目标，则说明不可能在第一行，因为
    右上角已经是该行的最大数了，排除；经过排除后再从剩下的数组中使用上面的方法进行查找，直到找到目标或者
    查完整个数组依然找不到。
    考察点：
    编程功底，Java语言熟练程度，二维数组查找中分析和解决问题的能力，例如通过右上角缩小来搜索范围，而不是
    简单使用两层循环遍历查找。
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
