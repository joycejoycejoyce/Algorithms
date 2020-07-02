package Array;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> getThreeSum(int[] inputArr) {
        // sort the arr
        Arrays.sort(inputArr);

        // have a set of list of integers to store three sum cobos
        Set<List<Integer>> threeSumSet = new HashSet<>();
        /*
            Go through each item i.

            do a 2 way sweep. while j and k doesn't meet, at each step calculate sum = (nums[i]+nums[j]+ nums[k])

                start sweep from next item to prevent duplicate combos because of indexes like

         */
        for (int i = 0; i < inputArr.length - 2; i++) {

            // have two pointers: one pointing to the next item (j)
            // another one pointing to the end of the array (k)
            int j = i + 1;
            int k = inputArr.length - 1;

            // do a 2-way-sweep. While j and k doesn't meet, at each step calculate

            while (j < k) {
                int sum = inputArr[i] + inputArr[j] + inputArr[k];

                if (sum == 0) {
                    threeSumSet.add(Arrays.asList(inputArr[i], inputArr[j], inputArr[k]));
                    j++;
                    k--;
                } else if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                }
            }
        }
        return new ArrayList<>(threeSumSet);
    }
}
