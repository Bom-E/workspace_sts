package test;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int num1 = i / 100;
        int num2 = i % 10;
        int num3 = i % 10;
        int cnt = 0;

        if(num1 == 3 || num1 == 6 || num1 == 9){
            cnt += 1;
        }
        if(num2 == 3 || num2 == 6 || num2 == 9){
            cnt = cnt + 1;
        }
        if(num3 == 3 || num3 == 6 || num3 == 9) {
            cnt++;
        }
        switch(cnt){
            case 0 :
                System.out.println("박수 0번");
                break;
            case 1 :
                System.out.println("박수 1번");
                break;
            case 2 :
                System.out.println("박수 2번");
                break;
            case 3 :
                System.out.println("박수 3번");
        }
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
    }
}
