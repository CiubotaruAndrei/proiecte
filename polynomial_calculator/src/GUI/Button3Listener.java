package GUI;

import Polinom.Polinom;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Button3Listener implements ActionListener {
    private JTextField pol1,pol2,rez;
    private Polinom polinom1,polinom2;
    Button3Listener(JTextField pol1,JTextField pol2,JTextField rez) {
        this.pol1=pol1;
        this.pol2=pol2;
        this.rez=rez;
    }

    public void actionPerformed(ActionEvent e){
        String polString1=pol1.getText();
        String polString2=pol2.getText();
        polinom1 = new Polinom();
        polinom2 = new Polinom();
        if(polinom1.construire(polString1)==1 && polinom2.construire(polString2)==1) {
            polinom1.inmultire(polinom2);
            rez.setText(polinom1.afisarePolinom());
        }
        else {
            rez.setText("Introduceti un polinom!!!");
        }
    }
}
