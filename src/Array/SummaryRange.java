package Array;

import java.util.ArrayList;
import java.util.Arrays;

public class SummaryRange {


    // ["0->2", "4->5", "7", "10->11", "13->15"]
    public static String[] intuitiveApproach(int[] inputArr){
        // an arrayList to contain the strings
        ArrayList<String> list = new ArrayList<>();

        // sort arr
        Arrays.sort(inputArr);

        System.out.println(Arrays.toString(inputArr));

        int startPt = 0;
        for(int i=0; i<inputArr.length; i++){
            if ( i + 1 >= inputArr.length || (inputArr[i] != inputArr[i+1]  && inputArr[i]+1 != inputArr[i+1]) ){
                if (startPt == i){
                    list.add( Integer.toString(inputArr[startPt]) );
                }else if (startPt < i){
                    // starting value
                    int startVal = inputArr[startPt];
                    list.add(""+ startVal + "->"+ inputArr[i]);
                }
                startPt = i+1;
            }
        }


        String[] outputArr = new String[list.size()];
        list.toArray(outputArr);

        return outputArr;
    }

    /*Complexity:
            Time: O(N)
                  actually its O(2N). Bcz we access the original arr two times in every round
                  But, big O doesn't care abt constant.
    *
    *       Space: O(N)
                  acutally its also O(2k). k is the length of the string list. k <=n.
                  But we ignore the constant. In worst case, there is no consecutive numbers in the list,
                  we have O(2n) => O(N)
    * */

    public static void main(String[] args) {

        int[] list =  {7,2,11,2,0,1,2,4,5,10,13,14,15};


        String[] output = intuitiveApproach(list);

        System.out.println(Arrays.toString(output));
    }
}
