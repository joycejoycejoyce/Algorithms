package DynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StringPermutations {

    public Set<String> recurPermutations(String inputString){
        // in case the input is not valid
        if (inputString.length() < 1){
            return null;
        }
        Set<String> outputSet = new HashSet<>();

        if (inputString.length() == 1){
            outputSet.add(inputString);
            return outputSet;
        }

        // if the inputString length > 1
        // get the first char
        String insertChar = inputString.substring(0,1);
        // get permutations of the rest of the string
        Set<String> restPermutationSet = recurPermutations( inputString.substring(1) );

        // perform permutation
        for (String str : restPermutationSet ){
            for(int index = 0; index <= str.length(); index++){
                String newStr = str.substring(0, index) + insertChar + str.substring(index);
                System.out.println("newStr "+newStr);
                outputSet.add(newStr);
            }
        }

        return outputSet;
    }

    public static void main(String[] args) {
        StringPermutations stringPermutations = new StringPermutations();

        String inputStr = "cat";
        Set<String> output =  stringPermutations.recurPermutations(inputStr);
        System.out.println(output);
        /*
        * cat
        * cta
        * tca
        * tac
        * atc
        * act
        *
        *
        * */

    }
}
