package Array;

public class ImplementstrStr {
    // point: sliding window

    // method 1:
    public int needInteration(String haystack, String needle){
        int L = needle.length();
        int n = haystack.length();

        for (int start = 0; start < n-L+1; start++){
            if (haystack.substring(start, start+L).equals(needle)){
                return start;
            }
        }
        return -1;
    }

    // method 2: two pointers
    public static int twoPointerApproach(String string, String target){
        int strLength = string.length();
        int targetLength = target.length();

        // edge case: target.length = 0
        if (targetLength == 0){return 0;}
        for(int i=0; i<strLength-targetLength+1; i++){
            boolean same = true;
            for (int j=0; j<targetLength; j++){
                String strSub = string.substring(i+j, i+j+1);
                String targetSub = target.substring(j, j+1);
                if (strSub.equals(targetSub) == false ){
                    same = false;
                    break; // break the j loop
                }
            }
            if (same == true){return i;}
        }
        // case: if the target str not exist in the string
        return -1;
    }

    // use while loop
    public int twoPointerWhileApproach(String string, String target){
        int strPt = 0, targetPt = 0;
        while (strPt < string.length() && targetPt < target.length()){
            if (string.substring(strPt, strPt+1).equals(target.substring(targetPt, targetPt+1))){
                strPt+=1;
                targetPt+=1;
            }else{
                strPt = strPt - targetPt + 1;
                targetPt = 0;
            }
        }
        if (targetPt == target.length()){
            return strPt-targetPt;
        }else{
            return -1;
        }
    }






    public static void main(String[] args) {
       String string = "hello";
       String target = "ll";
       int result = twoPointerApproach(string, target);
        System.out.println(result);
    }


}
