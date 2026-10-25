import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField t1, t2, result;
    JButton add, sub, mul, div;

    Calculator() {
        setTitle("Simple Calculator");
        setLayout(new GridLayout(5, 2, 5, 5));

        t1 = new JTextField();
        t2 = new JTextField();
        result = new JTextField();
        result.setEditable(false);

        add(new JLabel("First Number:"));
        add(t1);
        add(new JLabel("Second Number:"));
        add(t2);
        add(new JLabel("Result:"));
        add(result);

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");

        add(add);
        add(sub);
        add(mul);
        add(div);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        double a = Double.parseDouble(t1.getText());
        double b = Double.parseDouble(t2.getText());
        double r = 0;

        if (e.getSource() == add) r = a + b;
        if (e.getSource() == sub) r = a - b;
        if (e.getSource() == mul) r = a * b;
        if (e.getSource() == div) {
            if (b == 0) {
                result.setText("Cannot divide by zero");
                return;
            }
            r = a / b;
        }

        result.setText(String.valueOf(r));
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
