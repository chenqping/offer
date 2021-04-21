package bat.algorithms.games;
import java.util.Scanner;

/*
*  TPC2021比赛第一季第二场A题
*  只能进行ai <-> a(i+2) mod n操作，问一个序列能否通过这种操作实现有序
*  输入：
*  3
*  2
*  1 2
*  2
*  3 2
*  5
*  3 2 1 4 5
*  输出:
*  Yes
*  No
*  Yes
* */
public class WeirdSort {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 0; i < T; i++){
            int n = in.nextInt();
            int[] array = new int[n];
            for(int j = 0; j < n; j++){
                array[j] = in.nextInt();
            }
            if( array.length % 2 != 0){
                System.out.println("Yes");
            }else{
                for(int j = array.length-1; j >= 0; j = j -2){
                    for(int k = 0; k+2 <= j; k++){
                        if(array[k] > array[k+2]){
                            int temp = array[k];
                            array[k] = array[k+2];
                            array[k+2] = temp;
                        }
                    }
                }
                boolean isSorted = true;
                for(int j = 0; j < array.length-1; j++){
                    if(array[j] > array[j+1]){
                        isSorted = false;
                    }
                }
                if(isSorted){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }
        }
        in.close();
    }
}
