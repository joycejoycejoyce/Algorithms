package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChangeCoin {

    public static int changePossibilitiesTopDown(int amount, int[] denominations){
        return changePossibilitiesTopDown(amount, denominations, 0);
    }


    public static int changePossibilitiesTopDown(int amountLeft, int[] denominations, int currentIndex){
        // base cases:
        // we hit the amount spot on
        if (amountLeft == 0){
            return 1;
        }

        // we overshot the amount left
        if (amountLeft <0){
            return 0;
        }

        // we're out of denominations
        if (currentIndex == denominations.length){
            return 0;
        }

        System.out.printf("Checking ways to make %d with %s\n",
                amountLeft,
                Arrays.toString(Arrays.copyOfRange(denominations, currentIndex, denominations.length)));

        // choose a current coin
        int currentCoin = denominations[currentIndex];

        // see how many possibilities we can get
        // for each number of times to use currentCoin
        int numPossibilities = 0;
        while (amountLeft >=0){
            numPossibilities += changePossibilitiesTopDown(amountLeft, denominations, currentIndex+1);
            amountLeft -=currentCoin;
        }

        return numPossibilities;
    }

    private Map<String, Integer> memo = new HashMap<>();



    public int changeWithMemorization(int amount, int[] denominations){
        return changeWithMemorization(amount, denominations, 0);
    }

    public int changeWithMemorization(int amountLeft, int[] denominations, int currentIndex){

        // check our memo and short-circuit if we've already solved this one
        String memoKey = amountLeft+", "+currentIndex;
        if (memo.containsKey(memoKey)){
            System.out.println("grabbing memo ["+memoKey + "]");
            return memo.get(memoKey);
        }

        // base cases:
        // we hit the amt
        if (amountLeft == 0) return 1;

        // if we overshot the amount left
        if (amountLeft < 0) return 0;

        // we're out of denominations
        if (currentIndex == denominations.length) return 0;

        System.out.printf("checking ways to make %d with %s\n",
                amountLeft, Arrays.toString(Arrays.copyOfRange(denominations,
                        currentIndex, denominations.length)));


        // choose a current coin
        int currentCoin = denominations[currentIndex];

        // see how many possibilities we can get
        // for each number of times to use currentCoin
        int numPossibilites = 0;
        while (amountLeft >= 0){
            numPossibilites +=changeWithMemorization(amountLeft, denominations, currentIndex+1);
            amountLeft-=currentCoin;
        }

        // save the answer in our memo
        memo.put(memoKey, numPossibilites);
        return numPossibilites;
    }


    public static void main(String[] args) {
      //  ChangeCoin.changePossibilitiesTopDown(4, new int[]{1,2,3});

        ChangeCoin changeCoin = new ChangeCoin();
        int amount = 4;
        int[] denominations = new int[]{1,2,3};

       // changeCoin.changeWithMemorization(amount, denominations);
        int[] sorted =new int[]{1,2,5,3,8,9,9,2,3,8,4,2,3,5,7,9,9};
         Arrays.sort(sorted );
        System.out.println(Arrays.toString(sorted));
    }


}
