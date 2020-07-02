package Array;

import java.util.ArrayList;
import java.util.List;

public class LongestSortedInt {
    public int getLongestSortedIntegerList(int[] inputArr){
        // create an arraylist to contain the numbers
        int longest = 1;
        int currVal = inputArr[0];
        int currMax = 1;
        for (int i=1; i<inputArr.length; i++){
            if ( currVal <= inputArr[i]){
                currMax +=1;
            }else{
                longest = Math.max(longest, currMax);
                currMax = 1;
            }
            currVal = inputArr[i];
        }
        return longest;
    }


    public static void main(String[] args) {

        int output;

        LongestSortedInt longestSortedInt = new LongestSortedInt();

        int[] inputArr = new int[]{2,5,3,-1,0,2,5,8,4,2};


        output = longestSortedInt.getLongestSortedIntegerList(inputArr);

        System.out.println(output);
    }

}
