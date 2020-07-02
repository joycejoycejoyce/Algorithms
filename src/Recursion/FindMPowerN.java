package Recursion;

/*
* */

public class FindMPowerN {
    /*
    *
    *
    * */
    public int findValue(int M, int N){
        if (N ==0){
            return 1;
        }
        if (N == 1){
            return M;
        }

        return M * findValue(M, N-1);
    }

    /*
    *
    *
    * */
    public int findValueBinary(int M, int N){
        return 0;
    }
    public static void main(String[] args) {
        FindMPowerN findMPowerN = new FindMPowerN();

        int result = findMPowerN.findValue(3,0); // 27
        System.out.println("result is: "+result);
    }

}
