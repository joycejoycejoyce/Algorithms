package Recursion;

public class FindNFactorial {

    public int calcFactorial(int num){
        if (num ==1){
            return 1;
        }

        return num * calcFactorial(num-1);
    }

    public static void main(String[] args) {
        FindNFactorial findNFactorial = new FindNFactorial();
        int result  = findNFactorial.calcFactorial(4);
        System.out.println("factorial is "+result);

    }
}
