/*
add even number 2+4+...+100
 */

public class AddEvenNum {
    public int addEvenNumTillHundred(){
        int sum = 0;
        for (int i=2; i<=100; i+=2){
            sum+=i;
        }

        return sum;
    }

    public static void main(String[] args) {
        AddEvenNum addEvenNum = new AddEvenNum();
        int result = addEvenNum.addEvenNumTillHundred();
        System.out.println(result);
    }
}
