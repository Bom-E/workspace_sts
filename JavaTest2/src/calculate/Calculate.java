package calculate;

public class Calculate {

    private int num1;
    private int num2;

    void setNumber(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    int getSum(){
        int sum = 0;
        sum = num1 + num2;
        return sum;
    }

    int getMax(){
        int max;
        int min;
        if(num1 > num2){
            max = num1;
            min = num2;
        }
        else{
            max = num2;
            min = num1;
        }
        return max;
    }

    double getAvg(){
        int cnt = 0;
        double avg = 0;
        int sum = 0;
        for(int i = getSum()-getMax() + 1; i < getMax(); i++){
            sum += i;
            cnt++;
        }
        avg = (double) sum / cnt;
        return avg;
    }

}
