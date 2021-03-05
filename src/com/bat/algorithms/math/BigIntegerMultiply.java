package bat.algorithms.math;

/*
 *    问题说明：
 *    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *    算法说明：先要考虑大整数的表示方式，使用long,BigInteger不符要求，也会超出范围，考虑使用数组来表示，即从位
 *    到低位依次存入数组中，又两数的积位数不会超过两乘数位数之后，所以计算结果的空间就可以提前分配。通过分析两数
 *    相乘的计算过程，可知大致可以采用[A,B]*[C,D]=[AC,(AD+BC),BD]的方式先计算出中间结果，然后对结果进位处理，积
 *    的个位为BD%10，个位会向十位产生进位，进位的数值是BD/10，积的十位要加上个位的进位，所以结果就是(AD+BC+进位)%10，
 *    同样十位也会产生向百位的进位，依此类推得出进位处理后的就是积的数组表示，再去掉前导的0值转换为字符串即为积。
 *    边界条件：特殊用例，乘数为0的情况
 *    考察点:
 *    编程功底，Java语言熟练程度，本题中大数相乘问题的分析能力，如两个数相乘的积位数不会超过两个乘数位数之和，从计
 *    算过程中得出乘法计算的规律
 * */

public class BigIntegerMultiply {
    public static void main(String[] args) {
        System.out.println(multiply("123456", "7890"));
        System.out.println(multiply("123456", "0"));
        System.out.println(multiply("0", "0"));
    }

    public static String multiply(String num1, String num2) {
        char[] num1CharArr = num1.toCharArray();
        char[] num2CharArr = num2.toCharArray();
        int[] num1IntArr = new int[num1CharArr.length];
        int[] num2IntArr = new int[num2CharArr.length];
        int[] prodIntArr = new int[num1CharArr.length + num2CharArr.length];
        for (int i = 0; i < num1CharArr.length; i++) {
            num1IntArr[i] = num1CharArr[i] - 48;
        }
        for (int j = 0; j < num2CharArr.length; j++) {
            num2IntArr[j] = num2CharArr[j] - 48;
        }

        for (int i = num1IntArr.length - 1; i >= 0; i--) {
            for (int j = num2IntArr.length - 1; j >= 0; j--) {
                prodIntArr[i + j + 1] += (num2IntArr[j] * num1IntArr[i]);
            }
        }
//        System.out.println(Arrays.toString(prodIntArr));
        int carry = 0;
        for (int k = prodIntArr.length - 1; k >= 0; k--) {
            int value = prodIntArr[k];
            prodIntArr[k] = (value + carry) % 10;
            carry = (value + carry) / 10;
        }
//        System.out.println(Arrays.toString(prodIntArr));
        int leadingZeros = 0;
        for (int i = 0; i < prodIntArr.length; i++) {
            if (prodIntArr[i] != 0)
                break;
            leadingZeros++;
        }
        if(leadingZeros == prodIntArr.length)
            return "0";
        else{
            char[] prodCharArr = new char[prodIntArr.length - leadingZeros];
            for (int i = leadingZeros; i < prodIntArr.length; i++) {
                prodCharArr[i-leadingZeros] = (char) (prodIntArr[i] + 48);
            }
            return new String(prodCharArr);
        }
    }
}
