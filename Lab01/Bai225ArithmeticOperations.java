import java.util.Scanner;

public class Bai225ArithmeticOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("1st number: ");
        double num1 = scanner.nextDouble();

        System.out.print("2nd number: ");
        double num2 = scanner.nextDouble();

        double sum = num1 + num2;
        double difference = Math.abs(num1 - num2); // cleaner way
        double product = num1 * num2;

        System.out.println("The sum is " + sum);
        System.out.println("The difference is " + difference);
        System.out.println("The product is " + product);

        if (num2 != 0) {
            double quotient = num1 / num2;
            System.out.println("The quotient is " + quotient);
        } else {
            System.out.println("Division by 0 is not allowed.");
        }

        scanner.close();
    }
}

