package LinkedList;

public class FindRepeat {


    public static int optimizedApproach(int[] intArray){
        // find a number that appears more than once ... in O(n) time

        // loop 的第一个数字 的位置就是 被重复的数字的position

        /*
          1. 进入 loop 并且计算长度
          2. 知道 loop 的长度
          3. 找到loop 的第一个数字，用 stick 包裹的方式



        */

        // 1. 进入 loop 并且计算长度

        int totalSteps = intArray.length - 1;

        int currentVal = intArray[intArray.length - 1];

        for (int i=0; i < totalSteps; i++){
            currentVal = intArray[currentVal - 1];
        }

        // 2. 知道 loop 的长度
        int walkedVal = intArray[currentVal - 1];
        int loopLength = 1;

        while (currentVal != walkedVal){
            loopLength+=1;
            walkedVal = intArray[walkedVal - 1];
        }

        // 3. 找到loop 的第一个数字，用 stick 包裹的方式
        // 3.1 set a stick with the same length as the loop


        /*
         * ->* -> * ->*

         */

        int tail = intArray[intArray.length - 1];
        int head = intArray[intArray.length - 1];

        for(int j=0; j<loopLength; j++){
            head = intArray[head - 1];
        }

        while(tail != head ){
            head = intArray[head - 1];
            tail = intArray[tail - 1];
        }



        return head;
    }


    public static void main(String[] args) {

        // test case 1
        final int[] numbers1 = {1, 1};
        final int output1 = optimizedApproach(numbers1);

        System.out.println(output1); // expected = 1;


        // test case 2
        final int[] numbers2 = {1, 2, 3, 2};
        final int output2 = optimizedApproach(numbers2);
        System.out.println(output2); //expected = 2;


        // test case 3
        final int[] numbers3 = {1, 2, 5, 5, 5, 5};
        final int output3 = optimizedApproach(numbers3);

        System.out.println(output3); //expected = 5;


        // test case 4
        final int[] numbers4 = {4, 1, 4, 8, 3, 2, 7, 6, 5};
        final int output4 = optimizedApproach(numbers4);
        System.out.println(output4); //expected = 4;


        // test case 5
        final int[] numbers5 = {3, 1, 2, 2};
        final int output5 = optimizedApproach(numbers5);
        System.out.println(output5); // expected = 2
    }
}
