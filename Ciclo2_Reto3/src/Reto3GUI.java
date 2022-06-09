import javax.swing.*;

public class Reto3GUI extends JFrame{
    private JPanel mainPanel;
    private JButton button;
    private JButton procesarButton;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;

    public Reto3GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Reto3GUI("Reto3 Alejandro Urzola");
        frame.setVisible(true);
    }

}
