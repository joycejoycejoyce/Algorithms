package Stack;

import java.util.*;

public class BracketValidator {

    // keep two hash tables
        // h1(left, right)
        // h2(right, left)


    public boolean intuitiveSolution(String code){
        // O(1) space complexity & time complexity: not change according to input size
        Map<Character, Character> leftChar = new HashMap<>();
         Map<Character, Character> rightChar = new HashMap<>();

         leftChar.put('{', '}');
        leftChar.put('[', ']');
        leftChar.put('(', ')');

        rightChar.put('}', '{');
        rightChar.put(']', '[');
        rightChar.put(')', '(');

        // create a stack  O(N) space
        Deque<Character> stack = new ArrayDeque<>();

        // O(N)
        for(int i=0; i<code.length(); i++){
            char currChar = code.charAt(i);
            // if it is a left char
            if (leftChar.containsKey(currChar)){
                stack.push(currChar);
            } // if it is a right char
            else if (rightChar.containsKey(currChar)){
                if (stack.size() == 0 || stack.peek() != rightChar.get(currChar)){
                    return false;
                }else {
                    stack.pop();
                }
            }
        }

        return stack.size() > 0 ? false : true;

    }


    public boolean optimizedSolution(String code){
        Map<Character, Character> openersToClosers = new HashMap<>();
        openersToClosers.put('(', ')');
        openersToClosers.put('[', ']');
        openersToClosers.put('{', '}');

        Set<Character> openers = openersToClosers.keySet();
        Set<Character> closers = new HashSet<>(openersToClosers.values());

        Deque<Character> openersStack = new ArrayDeque<>();

        for(int i=0; i<code.length(); i++){
            char c = code.charAt(i);
            // if the char is an opener
            if (openers.contains(c)){
                openersStack.push(c);
            }// if the char is a closer
            else if (closers.contains(c)){
                if (openersStack.isEmpty()){
                    return false;
                }else {
                    char lastUnClosedOpener = openersStack.pop();

                    if (openersToClosers.get(lastUnClosedOpener) != c){
                        return false;
                    }
                }
            }
        }
        return openersStack.isEmpty();
    }

    /* Complexity:
        Time complexity: O(N)
                    worst case we need to walk through the string
                            1) it is true, a valid string "{[]}"
                            2) it is all openers. "{[(("

        Space complexity: O(N)
                    openersStack <- is the data structure we need to observe
                    worst case we need to store all chars in the string
                            1) it is all openers. "{[(("
     */

    public static void main(String[] args) {
        // testcase 1
        BracketValidator test1 = new BracketValidator();
        boolean test1Result = test1.intuitiveSolution("()");
        System.out.println(test1Result); // true

        // testcase 2
        BracketValidator test2 = new BracketValidator();
        boolean test2Result = test2.intuitiveSolution("([]{[]})[]{{}()}");
        System.out.println(test2Result); // true

        // testcase 3
        BracketValidator test3 = new BracketValidator();
        boolean test3Result = test3.intuitiveSolution("([][]}");
        System.out.println(test3Result); // false

        // testcase 4
        BracketValidator test4 = new BracketValidator();
        boolean test4Result = test4.intuitiveSolution("([)]");
        System.out.println(test4Result); // false

        // testcase 5
        BracketValidator test5 = new BracketValidator();
        boolean test5Result = test5.intuitiveSolution("[[]()");
        System.out.println(test5Result); // false


        // testcase 6
        BracketValidator test6 = new BracketValidator();
        boolean test6Result = test6.intuitiveSolution("[[]]())");
        System.out.println(test6Result); // false

        // testcase 7
        BracketValidator test7 = new BracketValidator();
        boolean test7Result = test7.intuitiveSolution("");
        System.out.println(test7Result); // true

    }

}
