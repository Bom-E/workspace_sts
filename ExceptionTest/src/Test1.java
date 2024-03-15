import java.util.InputMismatchException;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("프로그램 시작");
        Scanner sc = new Scanner(System.in);

        // 예외가 발생 할 수 있는 코드
        try {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            System.out.println(num1 / num2);
            System.out.println(1111);

        // 예외 발생 시 실행 할 코드
        }catch (ArithmeticException e){ // 예외에대한 정보
            // ArithmeticException : 수학적(논리적) 예외
            System.out.println("모든 수는 0 으로 나눌 수 없습니다.");

            //System.out.println("예외 발생!");
            // 예외 발생 이유를 간략하게 알려줌
            //System.out.println(e.getMessage());
            //e.printStackTrace();
        }catch (InputMismatchException i){
            // InputMismatchException : 들어온 데이터의 타입이 다를 때
            System.out.println("숫자를 입력하세요.");

        // 예외 발생 유무와 상관 없이 무조건! 꼭! 실행 되어야 하는 코드
        }finally {
            //
        }

        System.out.println("프로그램 종료");
    }
}
