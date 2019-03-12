package GUI;

import Polinom.Polinom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button5Listener implements ActionListener {
    private JTextField pol,rez;
    private Polinom polinom;
    Button5Listener(JTextField pol,JTextField rez) {
        this.pol=pol;
        this.rez=rez;
    }

    public void actionPerformed(ActionEvent e) {
        String polString=pol.getText();
        polinom=new Polinom();
        if(polinom.construire(polString)==1) {
            polinom.integrare();
            rez.setText(polinom.afisarePolinom());
        }
        else {
            rez.setText("Introduceti un polinom!!!");
        }
    }
}
