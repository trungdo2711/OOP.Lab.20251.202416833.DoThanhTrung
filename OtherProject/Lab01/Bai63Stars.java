import java.util.Scanner;

public class Bai63Stars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of lines: ");
        int line = scanner.nextInt();

        for (int i = 1; i <= line; i++) {
            for (int j = 1; j <= line - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}
