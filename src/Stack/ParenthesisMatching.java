package Stack;

public class ParenthesisMatching {

    public int getCorrespondingParenthesisPosition(String sentence, int openingParenIndex){
        if (sentence.length() <=1){
            throw new IllegalArgumentException("The sentence is not complete");
        }

        // record the number of left parenthesis
        int leftParenthesis = 1;

        // record currentPos
        int currentPos = openingParenIndex + 1;

        while (leftParenthesis > 0 && currentPos < sentence.length() ){
            char currentChar = sentence.charAt(currentPos);
            if ( currentChar  == ')'){
                leftParenthesis -=1;
            }else if ( currentChar == '(' ){
                leftParenthesis +=1;
            }
            // go to the next position
            currentPos += 1;
        }

        if (leftParenthesis > 0){
            throw new IllegalArgumentException("No corresponding parenthesis exists");
        }

        return currentPos - 1;
    }

    public int answerSolution(String sentence, int openingParenIndex){
        /*
        * 题目观察其中夹杂的parentheses 数量，我观察了所有的parentheses 的数量。
        * 没有好坏
        * */
        int openNestedParens = 0;


        /*
        我觉得区别在这里。我用了while. 如果去看我的代码。可读性要稍微低一点。
        可是用for loop 可读性比较高
         */
        for(int position = openingParenIndex+1; position< sentence.length(); position++){
            char currentChar = sentence.charAt(position);

            if (currentChar == '('){
                openNestedParens += 1;
            }else if(currentChar == ')'){
                if(openNestedParens == 0){
                    return position;
                }else{
                    openNestedParens -=1;
                }
            }
        }

        throw new IllegalArgumentException("No corresponding parenthesis");
    }

    /* Complexity:
        Time complexity:  O(n)
                            specifically it is O(m): m is the distance between the two parenthesises.
                            For the worst case,
                             1) the corresponding is the last one in the string
                             2) Or the corresponding does not exist
                             m = n-1; => O(n)
        Space complexity: O(1)
                            我们把space complexity 从 O（n） 减到了 O（1）。
                            For the worst case, 如果我们要一直向右走，并且所有的char 都是'('。
                            那么 stack 会一直添加elements inside, until exhaust the string.
                            O(n);

                            但我们用 int var 来记录并且更新 leftParentheses 的数量，这使得它在memory
                            中占用的空间是constant. O(1)
     */

    public static void main(String[] args) {
        // test case 1
        ParenthesisMatching test1 = new ParenthesisMatching();
        int test1Result = test1.getCorrespondingParenthesisPosition("((((()))))", 2);
        System.out.println(test1Result); //7


        // test case 2
        ParenthesisMatching test2 = new ParenthesisMatching();
        int test2Result = test2.getCorrespondingParenthesisPosition("()()((()()))", 5);
        System.out.println(test2Result); // 10

        // test case 3

        ParenthesisMatching test3 = new ParenthesisMatching();
        int test3Result = test3.getCorrespondingParenthesisPosition("()(()", 2);
        System.out.println(test3Result); // 10



    }
}
