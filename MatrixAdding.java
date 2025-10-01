import java.util.Scanner;

public class MatrixAdding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter rows of first matrix: ");
        int row1 = scanner.nextInt();
        System.out.print("Enter collumns of first matrix: ");
        int col1 = scanner.nextInt();
        int[][] matrix1 = new int[row1][col1];

        System.out.print("Enter rows of second matrix: ");
        int row2 = scanner.nextInt();
        System.out.print("Enter collumns of first matrix: ");
        int col2 = scanner.nextInt();
        int[][] matrix2 = new int[row2][col2];

        System.out.println("Enter values for first matrix:");
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                System.out.print("Enter the value for matrix1[" + i + "][" + j + "]: ");
                matrix1[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter values for second matrix:");
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                System.out.print("Enter the value for matrix1[" + i + "][" + j + "]: ");
                matrix2[i][j] = scanner.nextInt();
            }
        }

        int[][] result = addingMatrix(matrix1, matrix2);

        if (result != null) {
            System.out.println("Result matrix:");
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        }

        scanner.close();
    }

    public static int[][] addingMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length == 0 || matrix2.length == 0) {
            System.out.println("Matrix is empty");
            return null;
        }

        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;

        if (row1 != row2 || col1 != col2) {
            System.out.println("Different matrix size");
            return null;
        }

        int[][] result = new int[row1][col1];

        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }
}
