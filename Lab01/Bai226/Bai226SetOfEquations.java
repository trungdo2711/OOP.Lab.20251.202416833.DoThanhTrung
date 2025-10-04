import javax.swing.JOptionPane;

public class Bai226SetOfEquations {
    public static void main(String[] args) {
        try {
            String s_a11 = JOptionPane.showInputDialog(null, "Enter a11:");
            if (s_a11 == null) { JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE); return; }
            String s_a12 = JOptionPane.showInputDialog(null, "Enter a12:");
            if (s_a12 == null) { JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE); return; }
            String s_b1 = JOptionPane.showInputDialog(null, "Enter b1:");
            if (s_b1 == null) { JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE); return; }
            String s_a21 = JOptionPane.showInputDialog(null, "Enter a21:");
            if (s_a21 == null) { JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE); return; }
            String s_a22 = JOptionPane.showInputDialog(null, "Enter a22:");
            if (s_a22 == null) { JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE); return; }
            String s_b2 = JOptionPane.showInputDialog(null, "Enter b2:");
            if (s_b2 == null) { JOptionPane.showMessageDialog(null, "Exited!", "Exit", JOptionPane.INFORMATION_MESSAGE); return; }

            double a11 = Double.parseDouble(s_a11);
            double a12 = Double.parseDouble(s_a12);
            double b1 = Double.parseDouble(s_b1);
            double a21 = Double.parseDouble(s_a21);
            double a22 = Double.parseDouble(s_a22);
            double b2 = Double.parseDouble(s_b2);

            double D = a11 * a22 - a12 * a21;
            double Dx = b1 * a22 - a12 * b2;
            double Dy = a11 * b2 - b1 * a21;

            String result = "";

            if (D != 0) {
                double x = Dx / D;
                double y = Dy / D;
                result += "The solutions for the set of equations are x = " + x + " and y = " + y;
            } else {
                if (Dx == 0 && Dy == 0) {
                    result += "Infinitely many solutions";
                } else {
                    result += "No solution found";
                }
            }

            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a double value", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
