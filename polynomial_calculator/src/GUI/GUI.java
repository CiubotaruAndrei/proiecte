package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends  JPanel{

    public GUI() {
        JFrame frame = new JFrame ("Calculator Polinoame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        JPanel panel1 = new JPanel();//creez un panel pentru primul polinom
        JLabel l1 = new JLabel ("Polinom 1");
        JTextField tf1 = new JTextField();
        tf1.setPreferredSize(new Dimension(400,30));//setez dimensiunea pentru textField
        panel1.add(l1);
        panel1.add(tf1);
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();//creez un panel pentru al doi-lea polinom
        JLabel l2 = new JLabel ("Polinom 2");
        JTextField tf2 = new JTextField();
        tf2.setPreferredSize(new Dimension(400,30));
        panel2.add(l2);
        panel2.add(tf2);
        panel2.setLayout(new FlowLayout());

        JPanel panel3 = new JPanel();//creez un panel pentru butoane
        JButton b1 = new JButton("+");
        b1.setPreferredSize(new Dimension(70,40));//setez dimnesiunea butonului
        JButton b2 = new JButton("-");
        b2.setPreferredSize(new Dimension(70,40));
        JButton b3 = new JButton("*");
        b3.setPreferredSize(new Dimension(70,40));
        JButton b4 = new JButton("'");
        b4.setPreferredSize(new Dimension(70,40));
        JButton b5 = new JButton("∫");
        b5.setPreferredSize(new Dimension(70,40));
        panel3.add(b1);
        panel3.add(b2);
        panel3.add(b3);
        panel3.add(b4);
        panel3.add(b5);
        panel3.setLayout(new FlowLayout());


        JPanel panel4 = new JPanel();//creez un panel pentru rezultat
        JLabel l4 = new JLabel("Rezultat");
        JTextField rez = new JTextField();
        rez.setPreferredSize(new Dimension(400,30));
        panel4.add(l4);
        panel4.add(rez);
        panel4.setLayout(new FlowLayout());

        b1.addActionListener(new Button1Listener(tf1,tf2,rez));
        b2.addActionListener(new Button2Listener(tf1,tf2,rez));
        b3.addActionListener(new Button3Listener(tf1,tf2,rez));
        b4.addActionListener(new Button4Listener(tf1,rez));
        b5.addActionListener(new Button5Listener(tf1,rez));

        JPanel p = new JPanel();//panel-ul principal
        //adaug celelalte panel-uri in panel-ul principal
        p.add(panel1);
        p.add(panel2);
        p.add(panel3);
        p.add(panel4);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        frame.setContentPane(p);
        frame.setVisible(true);
    }
}
