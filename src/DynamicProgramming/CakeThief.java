package DynamicProgramming;

public class CakeThief {


    public static class CakeType {

        final int weight;
        final int value;

        public CakeType(int weight, int value) {
            this.weight = weight;
            this.value  = value;
        }
    }

    public static class InfinityException extends RuntimeException {
        public InfinityException(){
            super("Max value is infinity");
        }
    }

    /*
    *
    *
    * */
    // use long because we're looking for a max value
    public static long maxDuffelBagWithCapacity1(CakeType[] cakeTypes, int capacity){

        long[] outputArr = new long[capacity+1];

        for(int currentCapacity =1; currentCapacity<outputArr.length; currentCapacity++){
            long currMaxValue = 0;
            for (CakeType cake : cakeTypes){

                // if a cake weighs 0 and has a positive value the value of our deffel bag is infinite
                if (cake.weight == 0 && cake.value != 0){
                    throw  new InfinityException();
                }

                // if the current cake weighs as much or less than the current weight capacity
                // it's possible taking the cake would get a better value
                if (currentCapacity - cake.weight >= 0){
                    currMaxValue = Math.max(currMaxValue, cake.value + outputArr[currentCapacity - cake.weight]);
                }
            }

            outputArr[currentCapacity] = currMaxValue;
        }

        return outputArr[capacity];

    }


    /*  Complexity:
                time complexity: O(capacity * cakeTypes.size) = O(n * k)
     *                          loop through each cake for every capacity
     *          space complexity: O(capacity+1) => O(n)
     * */


    public static void main(String[] args) {
        final CakeType[] cakeTypes = {new CakeType(3, 4), new CakeType(4, 5)};
        long output = CakeThief.maxDuffelBagWithCapacity1(cakeTypes,7);  // 9
        System.out.println(output);
    }
}
