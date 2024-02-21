package test;

public class Test3 {
    public static void main(String[] args) {
        int[] test = {5, 11, 20, 40, 30};
        int max = 0;
        int min = 40;

        for(int i = 0; i < test.length; i++){
            if(max < test[i]){
                max = test[i];
            }
            if(min > test[i]){
                min = test[i];
            }
        }


        System.out.println(max);
        System.out.println(min);
    }
}
