package bat.algorithms.math;

/*
* 快手面试的一道算法题，大数相加
* */
public class BigIntegerAdd {
    public static void main(String[] args){

        String result = add("1", "6");
        System.out.println(result);
        result = add("2", "999");
        System.out.println(result);
        result = add("3", "");
        System.out.println(result);
    }

    public static String add(String num1, String num2){
        if(num1 == null || num1.length() == 0){
            return num2;
        }
        if(num2 == null || num2.length() == 0){
            return num1;
        }
        int[] num1Array = parseInt(num1);
        int[] num2Array = parseInt(num2);
        int length = num1Array.length >= num2Array.length ? num1Array.length + 1 : num2Array.length + 1;
        int[] numArray = new int[length];
        int i = num1Array.length - 1;
        int j = num2Array.length - 1;
        int k = length - 1;
        int carry = 0;
        while(k >= 0){
            int sum = carry;
            if (i >= 0){
                sum += num1Array[i];
                i--;
            }
            if(j >= 0){
                sum += num2Array[j];
                j--;
            }
            numArray[k] = sum % 10;
            carry = sum / 10;
            k--;
        }
        char[] charArray;
        if(numArray[0] != 0){
             charArray = new char[numArray.length];
            for (i = 0; i < numArray.length; i++) {
                charArray[i] = (char)(numArray[i] + 48);
            }
        }else{
            charArray = new char[numArray.length - 1];
            for (i = 1; i < numArray.length; i++) {
                charArray[i-1] = (char)(numArray[i] + 48);
            }
        }
        return new String(charArray);
    }

    public static int[] parseInt(String num){
        char[] charArray = num.toCharArray();
        int[] numArray = new int[charArray.length];
        for(int i=0; i< charArray.length; i++){
            numArray[i] = charArray[i] - 48;
        }
        return numArray;
    }

}
