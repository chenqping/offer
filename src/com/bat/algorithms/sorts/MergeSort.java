package bat.algorithms.sorts;

import java.util.Arrays;

public class MergeSort {
    public final static int MAXSIZE = 32;
    public static void main(String[] args){
        int[] a ={3, 2, 4, 1, 6, 5 };
        MergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void Merge(int[] SR, int[] TR, int i, int m, int n){
        int j, k;
        for(j = m + 1, k = i; i <= m && j <= n; ++k){
            if(SR[i] <= SR[j])
                TR[k] = SR[i++];
            else
                TR[k] = SR[j++];
        }
        while(i <= m)
            TR[k++] = SR[i++];
        while(j <= n)
            TR[k++] = SR[j++];
    }

    public static void MSort(int[] SR, int[] TR1, int s, int t){
        int[] TR2 = new int[MAXSIZE];
        if(s == t) TR1[s] = SR[s];
        else{
            int m = (s + t) / 2;
            MSort(SR, TR2, s, m);
            MSort(SR, TR2, m + 1, t);
            Merge(TR2, TR1, s, m ,t);
        }
    }

    public static void MergeSort(int[] input){
        MSort(input, input, 0, input.length-1);
    }
}
