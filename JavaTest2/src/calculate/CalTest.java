package calculate;

public class CalTest {
    public static void main(String[] args) {
    Calculate c1 = new Calculate();

        c1.setNumber(1, 6);

        System.out.println(c1.getSum());
        System.out.println();
        System.out.println(c1.getMax());
        System.out.println();
        System.out.println(c1.getAvg());

    }
}
