
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RestaurantBill extends JFrame implements ActionListener {
    JComboBox<String> food;
    JTextField qty;
    JTextArea bill;
    JLabel totalLabel;
    JButton btn;
    int total = 0;

    RestaurantBill() {
        setTitle("Restaurant Bill");
        setLayout(new FlowLayout());

        food = new JComboBox<>(new String[]{
            "Pizza - 300", "Burger - 150",
            "Pasta - 200", "Coffee - 80"
        });

        qty = new JTextField("1", 5);
        bill = new JTextArea(8, 20);
        totalLabel = new JLabel("Total Price: Tk 0");
        btn = new JButton("Add Item");

        add(new JLabel("Food:"));
        add(food);
        add(new JLabel("Quantity:"));
        add(qty);
        add(btn);
        add(new JScrollPane(bill));
        add(totalLabel);

        btn.addActionListener(this);
        setSize(350, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int i = food.getSelectedIndex();
        int[] price = {300, 150, 200, 80};
        int q = Integer.parseInt(qty.getText());
        int cost = price[i] * q;

        total += cost;
        bill.append(food.getSelectedItem() + " x " + q
                    + " = Tk " + cost + "\n");
        totalLabel.setText("Total Price: Tk " + total);
    }

    public static void main(String[] args) {
        new RestaurantBill();
    }
}

