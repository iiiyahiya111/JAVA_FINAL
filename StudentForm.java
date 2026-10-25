import javax.swing.*;
import java.awt.*;

public class StudentForm {
    public static void main(String[] args) {
        JFrame f = new JFrame("Student Form");
        f.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel l1 = new JLabel("Name:");
        JTextField name = new JTextField();

        JLabel l2 = new JLabel("Student ID:");
        JTextField id = new JTextField();

        JLabel l3 = new JLabel("Department:");
        String[] dept = {"ICE", "CSE", "EEE"};
        JComboBox<String> department = new JComboBox<>(dept);

        JLabel l4 = new JLabel("Gender:");
        JPanel p = new JPanel();
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        p.add(male);
        p.add(female);

        JButton btn = new JButton("Submit");

        f.add(l1);
        f.add(name);
        f.add(l2);
        f.add(id);
        f.add(l3);
        f.add(department);
        f.add(l4);
        f.add(p);
        f.add(new JLabel());
        f.add(btn);

        btn.addActionListener(e -> {
            String gender = male.isSelected() ? "Male" :
                            female.isSelected() ? "Female" : "Not selected";

            JOptionPane.showMessageDialog(f,
                "Name: " + name.getText() +
                "\nStudent ID: " + id.getText() +
                "\nDepartment: " + department.getSelectedItem() +
                "\nGender: " + gender);
        });

        f.setSize(400, 250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
