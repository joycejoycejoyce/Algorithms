package Combinatorics;

public class WhichAppearsTwice {
    public static int findRepeat(int[] numbers){
        // if the arr contains less than 2 numbers, the input is illegible
        if (numbers.length < 2){
            throw new IllegalArgumentException("The input arr is less than 2, no duplicate");
        }

        int n = numbers.length - 1;
        int sumWithoutDuplicate = (n*n+n) / 2;

        int actualSum = 0;
        for (int number : numbers){
            actualSum += number;
        }

        return actualSum - sumWithoutDuplicate;
    }
}
