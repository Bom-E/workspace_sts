import java.util.Scanner;

public class JavaTest01 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();
        int max = 0;
        int mid = 0;
        int min = 0;

        if (num1 > num2) {
            max = num1;
            if (num2 > num3) {
                mid = num2;
                min = num3;
            }
            else {
                mid = num3;
                min = num2;
            }
        }

        if (num2 > num1) {
            max = num2;
            if(num1 > num3){
                mid = num1;
                min = num3;
            }
            else {
                mid = num3;
                min = num1;
            }
        }

        if (num3 > num2) {
            max = num3;
            if(num2 > num1){
                mid = num2;
                min = num1;
            }
            else{
                mid = num1;
                min = num2;
            }
        }

        System.out.println(max);
        System.out.println(mid);
        System.out.println(min);
    }
}
