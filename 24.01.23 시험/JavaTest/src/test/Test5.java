package test;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr1 = new int[3];
        int[] arr2 = new int[3];
        int cnt = 0;

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random() * 9) + 1;
            for (int j = 0; j < i; j++) {
                if (arr1[i] == arr1[j]) {
                    i--;
                    break;
                }
            }
            System.out.println(arr1[i]);
        }
        System.out.println("숫자를 정했습니다. 게임을 시작합니다.");


        boolean isGot = false;
        while (!isGot){
            for(int i = 0; i < arr1.length; i++){
                System.out.print(i + 1 + " >> ");
                arr2[i] = sc.nextInt();
                
            }
        }
    }
}
