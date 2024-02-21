package mathutil;

public class MathUtilImpl implements MathUtil{

    @Override
    public boolean isEven(int a, int b, int c) {
        int sum = 0;
        sum = a + b + c;
        boolean result = false;
        if(sum % 2 == 0 && sum % 5 == 0){
            result = true;
        }
        return result;
    }

    @Override
    public double getSumFromOne(int num) {
        int sum = 0;
        for(int i = 1; i < num + 1; i++){
            sum += i;
        }
        return sum;
    }

    @Override
    public int getArraySum(int[] num1) {
        int sum = 0;
        for(int i = 0; i < num1.length; i++){
            sum += num1[i];
        }
        return sum;
    }
}
