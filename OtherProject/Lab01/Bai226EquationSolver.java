import javax.swing.JOptionPane;

public class Bai226EquationSolver {
    public static void main(String[] args) {
        while (true) {
            String[] options = {"Linear Equation (ax + b = 0)", "Quadratic Equation (ax² + bx + c = 0)", "System of 2 Equations", "Exit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Select the type of equation to solve:",
                    "Equation Solver",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == 3 || choice == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            switch (choice) {
                case 0:
                    solveLinearEquation();
                    break;
                case 1:
                    solveQuadraticEquation();
                    break;
                case 2:
                    solveSystemOfEquations();
                    break;
            }
        }
    }

    public static void solveLinearEquation() {
        try {
            String Coef_a = JOptionPane.showInputDialog(null, "Enter the coefficient for x:", "Enter a", JOptionPane.QUESTION_MESSAGE);
            if (Coef_a == null) return;
            String Free_coef = JOptionPane.showInputDialog(null, "Enter the free coefficient:", "Enter b", JOptionPane.QUESTION_MESSAGE);
            if (Free_coef == null) return;

            double a_value = Double.parseDouble(Coef_a);
            double b_value = Double.parseDouble(Free_coef);
            String result = "";

            if (a_value != 0) {
                double x_value = -b_value / a_value;
                result = "The solution for the equation is x = " + x_value;
            } else {
                if (b_value == 0)
                    result = "Infinitely many solutions";
                else
                    result = "No solution found";
            }

            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a double value", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void solveQuadraticEquation() {
        try {
            String Coef_a = JOptionPane.showInputDialog(null, "Enter the coefficient for x²:", "Enter a", JOptionPane.QUESTION_MESSAGE);
            if (Coef_a == null) return;
            String Coef_b = JOptionPane.showInputDialog(null, "Enter the coefficient for x:", "Enter b", JOptionPane.QUESTION_MESSAGE);
            if (Coef_b == null) return;
            String Free_coef = JOptionPane.showInputDialog(null, "Enter the free coefficient:", "Enter c", JOptionPane.QUESTION_MESSAGE);
            if (Free_coef == null) return;

            double a_value = Double.parseDouble(Coef_a);
            double b_value = Double.parseDouble(Coef_b);
            double c_value = Double.parseDouble(Free_coef);

            String result = "Result:\n";

            if (a_value == 0) {
                if (b_value == 0) {
                    if (c_value == 0) result += "Infinitely many solutions";
                    else result += "No solution found";
                } else {
                    double x = -c_value / b_value;
                    result += "The solution for the equation is x = " + x;
                }
            } else {
                double delta = b_value * b_value - 4 * a_value * c_value;

                if (delta > 0) {
                    double x1 = (-b_value + Math.sqrt(delta)) / (2 * a_value);
                    double x2 = (-b_value - Math.sqrt(delta)) / (2 * a_value);
                    result += "The solutions for the equation are:\n";
                    result += "x1 = " + x1 + "\n";
                    result += "x2 = " + x2;
                } else if (delta == 0) {
                    double x = -b_value / (2 * a_value);
                    result += "The equation has one double root: x = " + x;
                } else {
                    result += "No real solution found";
                }
            }

            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a double value", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void solveSystemOfEquations() {
        try {
            String s_a11 = JOptionPane.showInputDialog(null, "Enter a11:");
            if (s_a11 == null) return;
            String s_a12 = JOptionPane.showInputDialog(null, "Enter a12:");
            if (s_a12 == null) return;
            String s_b1 = JOptionPane.showInputDialog(null, "Enter b1:");
            if (s_b1 == null) return;
            String s_a21 = JOptionPane.showInputDialog(null, "Enter a21:");
            if (s_a21 == null) return;
            String s_a22 = JOptionPane.showInputDialog(null, "Enter a22:");
            if (s_a22 == null) return;
            String s_b2 = JOptionPane.showInputDialog(null, "Enter b2:");
            if (s_b2 == null) return;

            double a11 = Double.parseDouble(s_a11);
            double a12 = Double.parseDouble(s_a12);
            double b1 = Double.parseDouble(s_b1);
            double a21 = Double.parseDouble(s_a21);
            double a22 = Double.parseDouble(s_a22);
            double b2 = Double.parseDouble(s_b2);

            double D = a11 * a22 - a12 * a21;
            double Dx = b1 * a22 - a12 * b2;
            double Dy = a11 * b2 - b1 * a21;

            String result;

            if (D != 0) {
                double x = Dx / D;
                double y = Dy / D;
                result = "The solutions for the set of equations are:\n" +
                        "x = " + x + "\n" +
                        "y = " + y;
            } else {
                if (Dx == 0 && Dy == 0)
                    result = "Infinitely many solutions";
                else
                    result = "No solution found";
            }

            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a double value", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
