import model.Paciente;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

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
    private JPanel leftPanel;
    private JPanel rightPanel;
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
    private JButton randomUserBtn;
    private JLabel counterLbl;
    private JButton resetBtn;

    //global variables
    DefaultListModel listDataUsersModel = new DefaultListModel();
    DefaultListModel listResultModel = new DefaultListModel();
    ArrayList<Paciente> paciente = new ArrayList<Paciente>();
    Map<String, Integer> ciudadesMap = new LinkedHashMap<String, Integer>();

    //dataset for user randomizer
    String[] randomNames = new String[] {
            "Cornelia", "Peggy", "Christopher", "Peter", "Gertrude", "Francis",
            "Richard", "Lawrence", "Lionel", "Arnold", "Julio", "Helen"}; //length = 12
    String[] randomLastNames = new String[] {
            "Coughlin", "Short", "Lewis", "Sanchez", "Martinez", "Lund",
            "Blunt", "Leab", "Williams", "Burns", "Hacker", "Carraway"}; //length = 12
    String[] randomEps = new String[] {
            "SURA", "Sanitas", "Comfenalco", "Cafesalud", "Coomeva", "Compensar",
            "SALUDCOLOMBIA", "Salud Total", "Solsalud", "Saludcoop", "Cruz Blanca", "Salud Colmena"}; //length = 12
    String[] randomCities = new String[] {
            "Inirida", "Rioacha", "Mocoa", "Armenia", "Medellin", "Barranquilla",
            "Sincelejo", "Villavicencio", "Buracamanga", "Cartagena", "Ibague", "Pereira"}; //length = 12
    String[] randomIllness = new String[] {"Cancer", "Cardiovasculares", "Respiratorias",
            "Cerebrovasculares", "Hipertension", "Diabetes"}; //length 6


    public Reto3GUI(String title) {
        super(title); //calls super constructor

        //misc for UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        userList.setModel(listDataUsersModel);
        resultsList.setModel(listResultModel);

        //event listeners
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
            }
        });
        randomUserBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                formRandomizer();
            }
        });
        processBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listResultModel.clear();

                //imprime pacientes por ciudades
                listResultModel.addElement("1. CANTIDAD DE PACIENTES POR CIUDAD:");
                for (String key : ciudadesMap.keySet()) {
                    listResultModel.addElement(key + " " + ciudadesMap.get(key));
                }

                //imprime primera ciudad con menor cantidad de pacientes
                listResultModel.addElement(" ");
                listResultModel.addElement("2. CIUDAD CON MENOR CANTIDAD DE PACIENTES:");
                int min = Collections.min(ciudadesMap.values());
                for(String key : ciudadesMap.keySet()){
                    if (ciudadesMap.get(key) == min) {
                        listResultModel.addElement(key);
                        break;
                    }
                }

                //imprime pacientes de tercera edad
                listResultModel.addElement(" ");
                listResultModel.addElement("3. PACIENTES REGISTRADOS DE TERCERA EDAD:");
                for (int i = 0; i < paciente.size(); i++) {
                    if (paciente.get(i).clasificarEdad().equalsIgnoreCase("Tercera edad")) {
                        listResultModel.addElement(paciente.get(i).getNombre() + " " + paciente.get(i).getCedula());
                    }
                }
                if (listResultModel.lastElement() == "3. PACIENTES REGISTRADOS DE TERCERA EDAD:") {
                    listResultModel.addElement("No hay pacientes de tercera edad registrados");
                }
                processBtn.setEnabled(false);
            }
        });
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
                resetGlobalVariables();
                processBtn.setEnabled(false);
                counterLbl.setText("0");
            }
        });
        nombreTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                formNeedsData();
            }
        });
        cedulaTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                formNeedsData();
            }
        });
        edadTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                formNeedsData();
            }
        });
        ciudadTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                formNeedsData();
            }
        });
        epsTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                formNeedsData();
            }
        });
        illnessCmb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formNeedsData();
            }
        });
        edadTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                allowOnlyNumbers(e);
            }
        });

        cedulaTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                allowOnlyNumbers(e);
            }
        });
    }

    private void resetGlobalVariables() {
        listDataUsersModel.clear();
        listResultModel.clear();
        paciente.clear();
        ciudadesMap.clear();
    }

    private void formRandomizer() {
        Random rand = new Random();
        //random fore name and last name
        nombreTxt.setText(randomNames[rand.nextInt(12)] + " " + randomLastNames[rand.nextInt(12)]);
        //random ID number
        long random_int = (int)Math.floor(Math.random() * (1200000000 - 1100000000 + 1) + 1100000000);
        cedulaTxt.setText(String.valueOf(random_int));
        //random age
        edadTxt.setText(String.valueOf(21 + rand.nextInt(80)));
        //random city
        ciudadTxt.setText(randomCities[rand.nextInt(12)]);
        //random Health Promotion Agency
        epsTxt.setText(randomEps[rand.nextInt(12)]);
        //random illness
        illnessCmb.setSelectedItem(randomIllness[rand.nextInt(5)]);
        //enable addBtn
        addBtn.setEnabled(true);
    }

    private void addEntry() {
        Paciente persona = new Paciente(nombreTxt.getText(), Long.parseLong(cedulaTxt.getText()),
                Integer.parseInt(edadTxt.getText()), ciudadTxt.getText(), epsTxt.getText(),
                (String) illnessCmb.getSelectedItem());
        paciente.add(persona);
        listDataUsersModel.addElement(persona.toString('-'));

        //verificar si la ciudad hace parte del Map de ciudades.
        if (ciudadesMap.containsKey(persona.getCiudad())) {
            int counter = ciudadesMap.get(persona.getCiudad()) + 1;
            ciudadesMap.replace(persona.getCiudad(), counter); //reemplaza valor de contador
        } else {
            ciudadesMap.put(persona.getCiudad(), 1); //indexa nueva ciudad con contador en 1
        }
        resetForm();
        counterLbl.setText(String.valueOf(listDataUsersModel.size()));
    }

    private void resetForm() {
        nombreTxt.setText("");
        cedulaTxt.setText("");
        edadTxt.setText("");
        ciudadTxt.setText("");
        epsTxt.setText("");
        illnessCmb.setSelectedItem("Seleccionar");
        addBtn.setEnabled(false);
        resetBtn.setEnabled(true);
        processBtn.setEnabled(true);
    }

    public void formNeedsData() {
        if (nombreTxt.getText().isEmpty() || cedulaTxt.getText().isEmpty() || edadTxt.getText().isEmpty() ||
                ciudadTxt.getText().isEmpty() || epsTxt.getText().isEmpty() ||
                illnessCmb.getSelectedItem().equals("Seleccionar")) {
            addBtn.setEnabled(false); // at least one data is still not filled nor selected
        } else {
            addBtn.setEnabled(true); //all form data filled
        }
    }

    public void allowOnlyNumbers(KeyEvent e) {
        char car = e.getKeyChar();
        if(Character.isDigit(car)){

        }else{
            e.consume();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new Reto3GUI("Reto3 Alejandro Urzola");
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }
}
