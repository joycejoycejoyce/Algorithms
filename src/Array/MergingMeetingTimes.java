package Array;

import java.util.*;

public class MergingMeetingTimes {


    public static void print2DArray(int[][] inputArr){
        int rows = inputArr.length;
        for(int i=0; i<rows; i++){
            int innerArrLength = inputArr[i].length;
            System.out.print("[ ");
            for (int j=0; j<innerArrLength; j++){
                int num = inputArr[i][j];
                System.out.print(num+" ");
            }
            System.out.println("]");
        }
    }

    public static void print2DArray(List<int[]> inputArr){
        int rows = inputArr.size();
        for(int i=0; i<rows; i++){
            int innerArrLength = inputArr.get(i).length;
            System.out.print("[ ");
            for (int j=0; j<innerArrLength; j++){
                int num = inputArr.get(i)[j];
                System.out.print(num+" ");
            }
            System.out.println("]");
        }
    }



  //  输入: [[1,3],[2,6],[8,10],[15,18]]
  //  输出: [[1,6],[8,10],[15,18]]
    public static List<int[]> mergeRanges(int[][] inputArr){

        // 1. sort the input arr

        Arrays.sort(inputArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                return Integer.compare(first[0], second[0]);
            }
        });

        print2DArray(inputArr);

        // 2. create a arrayList to contain the merged range
        List<int[]> sortedMeetings = new ArrayList<>();
        int lastMeetingIdx = 0;
        sortedMeetings.add(inputArr[0]);

        for(int i=0; i<inputArr.length; i++){
            // get the current arr
            int[] currMeeting = inputArr[i];

            // get the prev meeting
            int[] prevMeeting = sortedMeetings.get(lastMeetingIdx);

            /* if the current arr start > prev end -> no overlapping
            * */
            if (prevMeeting[1] < currMeeting[0]){
                sortedMeetings.add(currMeeting);
                lastMeetingIdx+=1;
            }else{
                sortedMeetings.set(lastMeetingIdx, new int[]{prevMeeting[0], Math.max(prevMeeting[1], currMeeting[1])});
            }

        }

        return sortedMeetings;
    }

    public static void main(String[] args) {
        int[][] inputArr = {{1,3},{15,18}, {8,10},{2,6}};
        List outputList = mergeRanges(inputArr);
        print2DArray(outputList);
    }
}
