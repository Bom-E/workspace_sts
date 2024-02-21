package test;

import java.util.Arrays;
import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr1 = new int[3];
        int[] arr2 = new int[3];
        int cnt = 0;
        int stk = 0;
        int ball = 0;

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random() * 9) + 1;
            for (int j = 0; j < i; j++) {
                //중복 검사를 해야 하는데 0번째 데이터는 검사를 할 필요가 없음
                if (arr1[i] == arr1[j]) {
                    i--;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr1));
        System.out.println("숫자를 정했습니다. 게임을 시작합니다.");


        while (true){
            System.out.print(++cnt + " >> ");
            arr2[0] = sc.nextInt();
            arr2[1] = sc.nextInt();
            arr2[2] = sc.nextInt();

            for(int j = 0; j < arr1.length; j++){
                for(int i = 0; i < arr2.length; i++){
                    if(arr1[j] == arr2[i]){
                        if(i == j){
                            stk++;
                        }
                        else{
                            ball++;
                        }
                    }
                }
            }
            System.out.println(stk + " 스트라이크 " + ball + " 볼");
            if(stk == 3){
                System.out.println(cnt + "회 만에 숫자를 맞췄습니다.");
                break;
            }
        }
    }
}
