import javax.swing.JOptionPane;

public class Bai225ArithmeticOperations {
    public static void main(String[] args) {
        try {
            String strNum1 = JOptionPane.showInputDialog(null, "Enter number 1:", "First number", JOptionPane.QUESTION_MESSAGE);
            if (strNum1 == null) {
                JOptionPane.showMessageDialog(null, "Exited", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String strNum2 = JOptionPane.showInputDialog(null, "Enter number 2:", "Second number", JOptionPane.QUESTION_MESSAGE);
            if (strNum2 == null) {
                JOptionPane.showMessageDialog(null, "Exited", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

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
                result += "Division by zero is not allowed!";
            }

            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a double value", "Error !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
