import javax.swing.JOptionPane;

public class Bai226OneVariableLinearEquation {
    public static void main(String[] args) {
        try {
            String Coef_a = JOptionPane.showInputDialog(null, "Enter the coefficient for x: ", "Enter a", JOptionPane.QUESTION_MESSAGE);
            if (Coef_a == null) {
                JOptionPane.showMessageDialog(null, "Exited", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String Free_coef = JOptionPane.showInputDialog(null, "Enter the free coefficient: ", "Enter b", JOptionPane.QUESTION_MESSAGE);
            if (Free_coef == null) {
                JOptionPane.showMessageDialog(null, "Exited", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            double a_value = Double.parseDouble(Coef_a);
            double b_value = Double.parseDouble(Free_coef);

            String result = "";

            if (a_value != 0) {
                double x_value = -(b_value) / a_value;
                result += "The solution for the equation is x = " + x_value;
            } else {
                if(b_value == 0){
                    result += "Infinitely many solutions";
                }else{
                    result += "No solution found";
                }
            }

            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a double value", "Error !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
