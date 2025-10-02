import javax.swing.JOptionPane;

public class Bai225ArithmeticOperations {
    public static void main(String[] args) {
        String strNum1 = JOptionPane.showInputDialog(
                null, "Enter number 1:", "First number", JOptionPane.QUESTION_MESSAGE);
        String strNum2 = JOptionPane.showInputDialog(
                null, "Enter number 2:", "Second number", JOptionPane.QUESTION_MESSAGE);

        double firstNum = Double.parseDouble(strNum1);
        double secondNum = Double.parseDouble(strNum2);

        double sum = firstNum + secondNum;
        double difference = Math.abs(firstNum - secondNum);
        double product = firstNum * secondNum;

        String result = "Results:\n"
                + "Sum = " + sum + "\n"
                + "Difference = " + difference + "\n"
                + "Product = " + product + "\n";

        if (secondNum != 0) {
            double quotient = firstNum / secondNum;
            result += "Quotient = " + quotient;
        } else {
            result += "Quotient = Division by zero is not allowed!";
        }

        JOptionPane.showMessageDialog(
                null, result, "Arithmetic Operations", JOptionPane.INFORMATION_MESSAGE);
    }
}
