import model.Paciente;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Reto3GUI extends JFrame{
    private JPanel mainPanel;
    private JButton addBtn;
    private JButton processBtn;
    private JTextField nombreTxt;
    private JTextField ciudadTxt;
    private JTextField edadTxt;
    private JTextField epsTxt;
    private JTextField cedulaTxt;
    private JScrollPane scroll;
    private JList userList;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel btnPanel;
    private JList resultsList;
    private JComboBox illnessCmb;
    private JPanel formPanel;
    private JLabel formLbl;
    private JPanel formDataPanel;
    private JPanel formDataLeftPanel;
    private JPanel formDataRightPanel;
    private JLabel resultsLbl;

    public Reto3GUI(String title) {
        super(title); //calls super constructor,

        //global variables
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Paciente> paciente = new ArrayList<Paciente>();
        Map<String, Integer> ciudadesMap = new LinkedHashMap<String, Integer>();


        //misc for UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //event listeners
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Paciente persona = new Paciente(nombreTxt.getText(), Long.parseLong(cedulaTxt.getText()), Integer.parseInt(edadTxt.getText()), ciudadTxt.getText(), epsTxt.getText(), (String) illnessCmb.getSelectedItem());
                paciente.add(persona);
                for (int i = 0; i < 50; i++) {
                    listModel.addElement(persona.toString('-'));
                }
                nombreTxt.setText("");
                userList.setModel(listModel);
                resultsList.setModel(listModel);

                //verificar si la ciudad hace parte del Map de ciudades.
                if (ciudadesMap.containsKey(persona.getCiudad())) {
                    int counter = ciudadesMap.get(persona.getCiudad()) + 1;
                    ciudadesMap.replace(persona.getCiudad(), counter); //reemplaza valor de contador
                } else {
                    ciudadesMap.put(persona.getCiudad(), 1); //indexa nueva ciudad con contador en 1
                }

            }
        });
        nombreTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
//                JOptionPane.showMessageDialog(null, "test");
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new Reto3GUI("Reto3 Alejandro Urzola");
        frame.setSize(1200, 650);
        frame.setVisible(true);
    }
}
