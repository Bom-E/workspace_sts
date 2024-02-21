package mathutil;

import member.Member;

public class MathUtilTest {
    public static void main(String[] args) {
        MathUtilImpl mathUtil = new MathUtilImpl();

        System.out.println(mathUtil.isEven(5, 5, 10));

        System.out.println(mathUtil.getSumFromOne(15));

        int[] num1 = {1, 5, 10};
        System.out.println(mathUtil.getArraySum(num1));
    }
}
