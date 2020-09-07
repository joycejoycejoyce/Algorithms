package Array;

public class printMatrix {

    public static void spiralOrderPrint(int[][] matrix){
        int leftRow = 0, leftCol = 0;
        int rightRow = matrix.length - 1;
        int rightCol = matrix[0].length - 1;

        while(leftRow <= rightRow && leftCol <= rightCol){
            printEdge(matrix, leftRow++, leftCol++, rightRow--, rightCol--);
        }
    }
    /* Complexity:
            Time: O(N)
            Space: O(1)

     */

    public static void printEdge(int[][] matrix, int leftRow, int leftCol, int rightRow, int rightCol) {
        if (leftRow == rightRow){
            for (int i= leftCol; i <= rightCol; i++){
                System.out.print(matrix[leftRow][i] + " ");
            }
        }else if (leftCol == rightCol){
            for (int i=rightRow; i<= rightRow; i++){
                System.out.print(matrix[i][leftCol] + " ");
            }
        }else{
            int currentCol = leftCol;
            int currentRow = leftRow;

            while (currentCol != rightCol) {
                System.out.print(matrix[leftRow][currentCol] + " ");
                currentCol++;
            }
            while (currentRow != rightRow) {
                System.out.print(matrix[currentRow][rightCol] + " ");
                currentRow++;
            }
            while (currentCol != leftCol) {
                System.out.print(matrix[rightRow][currentCol] + " ");
                currentCol--;
            }
            while (currentRow != leftRow) {
                System.out.print(matrix[currentRow][leftCol] + " ");
                currentRow--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3, 4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        spiralOrderPrint(matrix);
    }
}
