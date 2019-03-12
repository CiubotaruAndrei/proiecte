package GUI;

import Coada.Coada;
import Manager.GeneratorClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JPanel {

    private JTextArea c1;
    private JTextArea c2;
    private JTextArea c3;
    private JTextArea c4;
    private JTextArea c5;
    private JTextArea c6;
    private JTextArea c7;
    private JTextArea logText;


    public Gui() {
        JFrame frame = new JFrame("Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        JPanel panel1 = new JPanel();
        JLabel l1 = new JLabel("Min_sosire");
        JTextField min_sos = new JTextField();
        min_sos.setPreferredSize(new Dimension(50,30));
        JLabel l2 = new JLabel("Max_sosire");
        JTextField max_sos = new JTextField();
        max_sos.setPreferredSize(new Dimension(50,30));
        JLabel l3 = new JLabel("Min_servire");
        JTextField min_ser = new JTextField();
        min_ser.setPreferredSize(new Dimension(50,30));
        JLabel l4 = new JLabel("Max_servire");
        JTextField max_ser = new JTextField();
        max_ser.setPreferredSize(new Dimension(50,30));
        panel1.add(l1);
        panel1.add(min_sos);
        panel1.add(l2);
        panel1.add(max_sos);
        panel1.add(l3);
        panel1.add(min_ser);
        panel1.add(l4);
        panel1.add(max_ser);
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        JButton start = new JButton("Start");
        panel2.add(start);
        panel2.setLayout(new FlowLayout());

        JPanel panel4 = new JPanel();
        JLabel l6 = new JLabel("Nr_Cozi");
        JTextField nr_cozi = new JTextField();
        nr_cozi.setPreferredSize(new Dimension(50,30));
        JLabel l7 = new JLabel("Simulare");
        JTextField sim = new JTextField();
        sim.setPreferredSize(new Dimension(50,30));
        panel4.add(l6);
        panel4.add(nr_cozi);
        panel4.add(l7);
        panel4.add(sim);
        JLabel l8 = new JLabel("Strategie");
        JComboBox strategie= new JComboBox(new String[]{"Random", "Min_Clienti", "Min_Servire"});
        panel4.add(l8);
        panel4.add(strategie);
        panel4.setLayout(new FlowLayout());

        JPanel cozi_area = new JPanel();

        JPanel coada1 = new JPanel();
        JLabel label1 = new JLabel("Coada 1");
        c1 = new JTextArea();
        c1.setPreferredSize(new Dimension(70,200));
        c1.setEditable(false);
        coada1.add(label1);
        coada1.add(c1);
        coada1.setLayout(new BoxLayout(coada1, BoxLayout.Y_AXIS));

        JPanel coada2 = new JPanel();
        JLabel label2 = new JLabel("Coada 2");
        c2 = new JTextArea();
        c2.setPreferredSize(new Dimension(70,200));
        c2.setEditable(false);
        coada2.add(label2);
        coada2.add(c2);
        coada2.setLayout(new BoxLayout(coada2, BoxLayout.Y_AXIS));

        JPanel coada3 = new JPanel();
        JLabel label3 = new JLabel("Coada 3");
        c3 = new JTextArea();
        c3.setPreferredSize(new Dimension(70,200));
        c3.setEditable(false);
        coada3.add(label3);
        coada3.add(c3);
        coada3.setLayout(new BoxLayout(coada3, BoxLayout.Y_AXIS));

        JPanel coada4 = new JPanel();
        JLabel label4 = new JLabel("Coada 4");
        c4 = new JTextArea();
        c4.setPreferredSize(new Dimension(70,200));
        c4.setEditable(false);
        coada4.add(label4);
        coada4.add(c4);
        coada4.setLayout(new BoxLayout(coada4, BoxLayout.Y_AXIS));

        JPanel coada5 = new JPanel();
        JLabel label5 = new JLabel("Coada 5");
        c5 = new JTextArea();
        c5.setPreferredSize(new Dimension(70,200));
        c5.setEditable(false);
        coada5.add(label5);
        coada5.add(c5);
        coada5.setLayout(new BoxLayout(coada5, BoxLayout.Y_AXIS));

        JPanel coada6 = new JPanel();
        JLabel label6 = new JLabel("Coada 6");
        c6 = new JTextArea();
        c6.setPreferredSize(new Dimension(70,200));
        c6.setEditable(false);
        coada6.add(label6);
        coada6.add(c6);
        coada6.setLayout(new BoxLayout(coada6, BoxLayout.Y_AXIS));

        JPanel coada7 = new JPanel();
        JLabel label7 = new JLabel("Coada 7");
        c7 = new JTextArea();
        c7.setPreferredSize(new Dimension(70,200));
        c7.setEditable(false);
        coada7.add(label7);
        coada7.add(c7);
        coada7.setLayout(new BoxLayout(coada7, BoxLayout.Y_AXIS));

        JPanel panel3 = new JPanel();
        JLabel l5 = new JLabel("Log Text");
        logText = new JTextArea(17,53);
        logText.setLineWrap(true);
        logText.setWrapStyleWord(true);
        logText.setEditable(false);
        JScrollPane scroll = new JScrollPane(logText);
        scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel3.add(l5);
        panel3.add(scroll);
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));


        cozi_area.add(coada1);
        cozi_area.add(coada2);
        cozi_area.add(coada3);
        cozi_area.add(coada4);
        cozi_area.add(coada5);
        cozi_area.add(coada6);
        cozi_area.add(coada7);
        cozi_area.setLayout(new FlowLayout());


        JPanel p = new JPanel();
        p.add(panel4);
        p.add(panel1);
        p.add(panel2);
        p.add(cozi_area);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JPanel finalPanael = new JPanel();
        finalPanael.add(p);
        finalPanael.add(panel3);
        finalPanael.setLayout(new FlowLayout());

        frame.setContentPane(finalPanael);
        frame.setVisible(true);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minSos=Integer.parseInt(min_sos.getText());
                int maxSos=Integer.parseInt(max_sos.getText());
                int minSer=Integer.parseInt(min_ser.getText());
                int maxSer=Integer.parseInt(max_ser.getText());
                int nrCozi=Integer.parseInt(nr_cozi.getText());
                int simulare=Integer.parseInt(sim.getText());
                String strat=(String)strategie.getSelectedItem();
                System.out.printf(" %d\n %d\n %d\n %d\n %d\n %d\n %s\n",minSos,maxSos,minSer,maxSer,nrCozi,simulare,strat);
                GeneratorClient generator = new GeneratorClient(nrCozi,minSos,maxSos,minSer,maxSer,simulare,strat,Gui.this);
            }
        });

    }

    public void actualizare(String msg) {
        logText.append(msg+"\n");
    }

    public void print(Coada coada) {
        if(coada.getNrCoada()==1) {
            c1.setText("");
            c1.append(" ---------\n");
            for (int i = 0; i < coada.list.size(); i++) {
                c1.append("    *    " + coada.list.get(i).getTimpServire() + "\n");
            }
        }
        else if(coada.getNrCoada()==2) {
            c2.setText("");
            c2.append(" ---------\n");
            for (int i = 0; i < coada.list.size(); i++) {
                c2.append("    *    " + coada.list.get(i).getTimpServire() + "\n");
            }
        }
        else if(coada.getNrCoada()==3) {
            c3.setText("");
            c3.append(" ---------\n");
            for (int i = 0; i < coada.list.size(); i++) {
                c3.append("    *    " + coada.list.get(i).getTimpServire() + "\n");
            }
        }
        else if(coada.getNrCoada()==4) {
            c4.setText("");
            c4.append(" ---------\n");
            for (int i = 0; i < coada.list.size(); i++) {
                c4.append("    *    " + coada.list.get(i).getTimpServire() + "\n");
            }
        }
        else if(coada.getNrCoada()==5) {
            c5.setText("");
            c5.append(" ---------\n");
            for (int i = 0; i < coada.list.size(); i++) {
                c5.append("    *    " + coada.list.get(i).getTimpServire() + "\n");
            }
        }
        else if(coada.getNrCoada()==6) {
            c6.setText("");
            c6.append(" ---------\n");
            for (int i = 0; i < coada.list.size(); i++) {
                c6.append("    *    " + coada.list.get(i).getTimpServire() + "\n");
            }
        }
        else {
            c7.setText("");
            c7.append(" ---------\n");
            for (int i = 0; i < coada.list.size(); i++) {
                c7.append("    *    " + coada.list.get(i).getTimpServire() + "\n");
            }
        }

    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}
