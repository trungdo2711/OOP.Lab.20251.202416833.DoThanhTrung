import javax.swing.JOptionPane;

public class Bai226OneVariableQuadraticEquation {
    public static void main(String[] args) {
        try {
            String Coef_a = JOptionPane.showInputDialog(null, "Enter the coefficient for x^2: ", "Enter a", JOptionPane.QUESTION_MESSAGE);
            if (Coef_a == null) {
                JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String Coef_b = JOptionPane.showInputDialog(null, "Enter the coefficient for x: ", "Enter b", JOptionPane.QUESTION_MESSAGE);
            if (Coef_b == null) {
                JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String Free_coef = JOptionPane.showInputDialog(null, "Enter the free coefficient: ", "Enter c", JOptionPane.QUESTION_MESSAGE);
            if (Free_coef == null) {
                JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            double a_value = Double.parseDouble(Coef_a);
            double b_value = Double.parseDouble(Coef_b);
            double c_value = Double.parseDouble(Free_coef);

            String result = "Result:\n";

            if (a_value == 0) {
                if (b_value == 0) {
                    if (c_value == 0) {
                        result += "Infinitely many solutions";
                    } else {
                        result += "No solution found";
                    }
                } else {
                    double x = -c_value / b_value;
                    result += "The solution for the equation is x = " + x;
                }
            } else {
                double delta = b_value * b_value - 4 * a_value * c_value;

                if (delta > 0) {
                    double x1 = (-b_value + Math.sqrt(delta)) / (2 * a_value);
                    double x2 = (-b_value - Math.sqrt(delta)) / (2 * a_value);
                    result += "The solutions for the equations are x1 = " + x1 + " and x2 = " + x2;
                } else if (delta == 0) {
                    double x = -b_value / (2 * a_value);
                    result += "The solution for the equation is x = " + x;
                } else {
                    result += "No real solution found";
                }
            }

            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a double value", "Error !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
