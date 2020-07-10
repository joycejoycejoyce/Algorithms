package GeneralProgramming;

public class TemperatureTracker {
    public class TempTracker {
        int maxTemp = Integer.MIN_VALUE;
        int minTemp = Integer.MAX_VALUE;

        int tempSum = 0;
        int insertNum = 0;

        int[] counts = new int[110+1];
        int freqTemp;
        int freqTempOccurrence = 0;

        public void insert(int newTemp){
            // max
            if (newTemp > maxTemp){
                maxTemp = newTemp;
            }
            // min
            if (newTemp < minTemp){
                minTemp = newTemp;
            }

            // mean
            tempSum +=newTemp;
            insertNum+=1;

            // mode
            counts[newTemp]+=1;

            if (counts[newTemp] > freqTempOccurrence){
                freqTemp = newTemp;
                freqTempOccurrence = counts[newTemp];
            }
        }

        public int getMax(){
            return maxTemp;
        }

        public int getMin(){
            return minTemp;
        }

        public double getMean(){
            return (double)tempSum / insertNum;
        }

        public int getMode(){
            return freqTemp;
        }
    }
}

/* Complexity:
    Time complexity: O(1) for each method
    Space complexity: O(1) related to input.
                      The counts array's size is bounded by the range of possible temps.

 */
