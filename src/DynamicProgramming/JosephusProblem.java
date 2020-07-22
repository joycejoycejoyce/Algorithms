package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class JosephusProblem {

    /* Array implementation
        1) can return the index of the last survivor
        2) return the sequence of exited numbers
    * */
    static int arrayApproach(int arrSize, int dieNumber){
        // create an array based on the arr size
        int[] arr = new int[arrSize];

        // place the number inside the index
        for(int i=0; i<arrSize; i++){
            arr[i] = i+1;
        };

        // set var
        int flag = 1; // count the walked number
        int arrBound = arrSize - 1; // set the bound for the arr
        int currentIdx = 0; // get the current index


        while(arrBound >= 0){
            if (flag >= dieNumber){

                // find the number need to be removed
                int removedNum = arr[currentIdx];

                // move all remaining nodes 1 step forward
                for(int j=currentIdx; j<arrSize-1; j++){
                    arr[j] = arr[j+1];
                }

                arr[arrSize - 1] = removedNum;

                // reset the flag
                flag = 1;
                // keep currentIndex as is
                // bound one step back
                arrBound-=1;
                currentIdx-=1;
            }
            // if the current index is not the flag index
            else {
                flag += 1;  // move flag 1 more

            }
            // currentIdx = (currentIdx >= (arrSize - 1 ) ) ? 0 : currentIdx++; // if the current index reach end
            // bound, return to the front of the list
            // otherwise, go one more step.

            if (currentIdx >= (arrBound) ){
                currentIdx = 0;
            }else{
                currentIdx+=1;
            }

        }

        System.out.println(Arrays.toString(arr));
        return arr[arrSize - 1];
    }

    /*  Complexity:
         Time complexity: O(n^2)
         Space complexity:O(n)
     */

    public static int arrayListApproach(int size, int dieNumber){
        // build an arraylist
        ArrayList<Integer> arr = new ArrayList<>(size);

        for(int i=0; i<size; i++){
            arr.add(i);
        }

        // keep remove arr until the arr.size == 1
        // but bcz we can't operate on arr + get arr.size at the same time
        // need a var to record the removed times

        int remainedNum = size;
        int currentIndex = 0;

        while (remainedNum > 1){
                currentIndex = (currentIndex + dieNumber - 1 ) % remainedNum;
                arr.remove(currentIndex);
                remainedNum -=1;
        }

        return arr.get(0);
    }
    /* Complexity:
            Time complexity: O(n^2) <= O(n) + O(n*n)
                    for loop: O(N)
                    while loop: O(N-1)
                        arr.remove: O(N)

            Space complexity: O(n)
                     arr of size n

        Summary:
            ArrayList is faster than Linked List. Because array list is stored in
            consecutive sequence. So even though both of them have time complexity as O(n^2)
            As result, ArrayList is FASTER than LL.
     */


    public static int recursiveApproach(int size, int dieNum){
        if (size == 1){
            return 0;
        }
        return (recursiveApproach(size - 1, dieNum) + dieNum) % size;
    }

    /* Complexity:
            Time complexity: O(N) 从 n -> (n-1) -> (n-2) -> ... -> 3 -> 2 -> 1
            Space complexity: O(N) recursion occupies n func in stack
     */

    public static int iterativeApproach(int size, int dieNum){

        int startSize = 1;
        int position = 0;

        for(int currSize=2; currSize<=size; currSize++){
            position = (position + dieNum ) % currSize;
        }

        return position;
    }

    /* Complexity:
        Time complexity: O(N) size - 2 + 1 = O(N-1)
        Space complexity:O(1)
     */

    public static void main(String[] args) {
        int output;
        output = iterativeApproach(5, 3);
        System.out.println(output);
    }
}
