package presentation;

import businessLogic.ClientBL;
import businessLogic.ComandaBL;
import businessLogic.ProdusBL;
import model.Client;
import model.Comanda;
import model.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * relizeaza interfata aplicatiei
 */
public class Gui extends JPanel {
    /**
     * relizeaza fereastra principala
     */
    public Gui() {
        JFrame frame = new JFrame("Operatii");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        JPanel firstPanel = new JPanel();
        JButton clientOp = new JButton("Client");
        clientOp.setPreferredSize(new Dimension(100,50));
        JButton productOp = new JButton("Product");
        productOp.setPreferredSize(new Dimension(100,50));
        JButton orderOp = new JButton("Order");
        orderOp.setPreferredSize(new Dimension(100,50));
        firstPanel.add(clientOp,BorderLayout.LINE_START);
        firstPanel.add(productOp,BorderLayout.CENTER);
        firstPanel.add(orderOp,BorderLayout.LINE_END);

        clientOp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opClient();
            }
        });
        productOp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opProdus();
            }
        });
        orderOp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opOrder();
            }
        });
        frame.setContentPane(firstPanel);
        frame.setVisible(true);
    }

    /**
     * relizeaza fereastra cu operatiile specifice clientului
     */
    public void opClient() {
        final JFrame frame = new JFrame("Operatii Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        final JPanel panel1 = new JPanel();
        JLabel lid = new JLabel("id");
        final JTextField id = new JTextField("",3);
        JLabel lnume = new JLabel("nume");
        final JTextField nume = new JTextField("",12);
        JLabel ladresa = new JLabel("adresa");
        final JTextField adresa = new JTextField("",12);
        JLabel ltelefon = new JLabel("telefon");
        final JTextField telefon = new JTextField("",8);
        JLabel lemail = new JLabel("email");
        final JTextField email = new JTextField("",12);
        panel1.add(lid);
        panel1.add(id);
        panel1.add(lnume);
        panel1.add(nume);
        panel1.add(ladresa);
        panel1.add(adresa);
        panel1.add(ltelefon);
        panel1.add(telefon);
        panel1.add(lemail);
        panel1.add(email);
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        JButton find = new JButton("find by id");
        JButton add = new JButton("add");
        JButton edit = new JButton("edit");
        JButton delete = new JButton("delete");
        JButton view = new JButton("view");
        panel2.add(find);
        panel2.add(add);
        panel2.add(edit);
        panel2.add(delete);
        panel2.add(view);
        panel2.setLayout(new FlowLayout());
        final JPanel panel = new JPanel();
        find.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int findId;
                findId = Integer.parseInt(id.getText());
                ClientBL clientBL =new ClientBL();
                Client c =clientBL.findClientById(findId);
                if(c==null)
                    JOptionPane.showMessageDialog(panel,"Client inexistent!!!");
                else
                    JOptionPane.showMessageDialog(panel,c.toString());

            }
        });
        final JPanel panel3 = new JPanel();
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ClientBL clientBL = new ClientBL();
                JTable table = clientBL.findAll();
                table.setCellSelectionEnabled(false);
                table.setEnabled(false);
                JScrollPane t = new JScrollPane(table);
                t.setPreferredSize(new Dimension(700,150));
                panel3.add(t);
                panel.add(panel3);
                frame.setContentPane(panel);
                frame.setVisible(true);
            }
        });

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeAd,adresaAd,telefonAd,emailAd;
                numeAd = nume.getText();
                adresaAd = adresa.getText();
                telefonAd = telefon.getText();
                emailAd = email.getText();
                Client c = new Client(numeAd,adresaAd,telefonAd,emailAd);
                ClientBL clientBL = new ClientBL();
                if(clientBL.insert(c)==1)
                    JOptionPane.showMessageDialog(panel,"Succes!!!");
                else if(clientBL.insert(c)==2)
                    JOptionPane.showMessageDialog(panel,"Date invalide: nume invalid!!!");
                else if(clientBL.insert(c)==3)
                    JOptionPane.showMessageDialog(panel,"Date invalide: adresa invalida!!!");
                else if(clientBL.insert(c)==4)
                    JOptionPane.showMessageDialog(panel,"Date invalide: telefon invalid!!!");
                else if(clientBL.insert(c)==5)
                    JOptionPane.showMessageDialog(panel,"Date invalide: email invalid!!!");
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int delId;
                delId = Integer.parseInt(id.getText());
                ClientBL clientBL =new ClientBL();
                if(clientBL.delete(delId)==1)
                    JOptionPane.showMessageDialog(panel,"Succes: S-a sters clientul cu id = " + delId);
                else
                    JOptionPane.showMessageDialog(panel,"Client inexistent");

            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeUp,adresaUp,telefonUp,emailUp;
                int idUp;
                idUp = Integer.parseInt(id.getText());
                ClientBL clientBL = new ClientBL();
                Client c = clientBL.findClientById(idUp);
                if(c==null)
                    JOptionPane.showMessageDialog(panel, "ID Invalid");
                if(!nume.getText().equals("")) {
                    numeUp = nume.getText();
                    c.setNume(numeUp);
                }
                if(!adresa.getText().equals("")) {
                    adresaUp = adresa.getText();
                    c.setAdresa(adresaUp);
                }
                if(!telefon.getText().equals("")) {
                    telefonUp = telefon.getText();
                    c.setTelefon(telefonUp);
                }
                if(!email.getText().equals("")) {
                    emailUp = email.getText();
                    c.setEmail(emailUp);
                }
                if (clientBL.update(c,idUp)==1)
                    JOptionPane.showMessageDialog(panel, "Succes!!!");
                else
                    JOptionPane.showMessageDialog(panel, "Date invalide!!!");
            }
        });

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    /**
     * relizeaza fereastra cu operatiile specifice produsului
     */
    public void opProdus() {
        final JFrame frame = new JFrame("Operatii Produs");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        final JPanel panel1 = new JPanel();
        JLabel lid = new JLabel("id");
        final JTextField id = new JTextField("",3);
        JLabel ldenumire = new JLabel("denumire");
        final JTextField denumire = new JTextField("",12);
        JLabel lpret = new JLabel("pret");
        final JTextField pret = new JTextField("",5);
        JLabel lstoc = new JLabel("stoc");
        final JTextField stoc = new JTextField("",5);
        panel1.add(lid);
        panel1.add(id);
        panel1.add(ldenumire);
        panel1.add(denumire);
        panel1.add(lpret);
        panel1.add(pret);
        panel1.add(lstoc);
        panel1.add(stoc);
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        JButton find = new JButton("find by id");
        JButton add = new JButton("add");
        JButton edit = new JButton("edit");
        JButton delete = new JButton("delete");
        JButton view = new JButton("view");
        panel2.add(find);
        panel2.add(add);
        panel2.add(edit);
        panel2.add(delete);
        panel2.add(view);
        panel2.setLayout(new FlowLayout());
        final JPanel panel = new JPanel();
        find.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int findId;
                findId = Integer.parseInt(id.getText());
                ProdusBL produsBL = new ProdusBL();
                Produs p =produsBL.findClientById(findId);
                if(p==null)
                    JOptionPane.showMessageDialog(panel,"Produs inexistent!!!");
                else
                    JOptionPane.showMessageDialog(panel,p.toString());

            }
        });
        final JPanel panel3 = new JPanel();
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProdusBL produsBL = new ProdusBL();
                JTable table = produsBL.findAll();
                table.setCellSelectionEnabled(false);
                table.setEnabled(false);
                JScrollPane t = new JScrollPane(table);
                t.setPreferredSize(new Dimension(700,150));
                panel3.add(t);
                panel.add(panel3);
                frame.setContentPane(panel);
                frame.setVisible(true);

            }
        });

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String denumireAd;
                float pretAd;
                int stocAd;
                denumireAd = denumire.getText();
                pretAd = Float.parseFloat(pret.getText());
                stocAd = Integer.parseInt(stoc.getText());

                Produs p = new Produs(denumireAd,pretAd,stocAd);
                ProdusBL produsBL = new ProdusBL();
                if(produsBL.insert(p)==1)
                    JOptionPane.showMessageDialog(panel,"Succes!!!");
                else if(produsBL.insert(p)==2)
                    JOptionPane.showMessageDialog(panel,"Date invalide: denumire invalid!!!");
                else if(produsBL.insert(p)==3)
                    JOptionPane.showMessageDialog(panel,"Date invalide: pret invalid!!!");
                else if(produsBL.insert(p)==4)
                    JOptionPane.showMessageDialog(panel,"Date invalide: stoc invalid!!!");
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int delId;
                delId = Integer.parseInt(id.getText());
                ProdusBL produsBL = new ProdusBL();
                if(produsBL.delete(delId)==1)
                    JOptionPane.showMessageDialog(panel,"Succes: S-a sters produsul cu id = " + delId);
                else
                    JOptionPane.showMessageDialog(panel,"Produs inexistent");

            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String denumireUp;
                float pretUp;
                int idUp,stocUp;
                idUp = Integer.parseInt(id.getText());
                ProdusBL produsBL = new ProdusBL();
                Produs p = produsBL.findClientById(idUp);
                if(p==null)
                    JOptionPane.showMessageDialog(panel, "ID Invalid");
                if(!denumire.getText().equals("")) {
                    denumireUp = denumire.getText();
                    p.setDenumire(denumireUp);
                }
                if(!pret.getText().equals("")) {
                    pretUp = Float.parseFloat(pret.getText());
                    p.setPret(pretUp);
                }
                if(!stoc.getText().equals("")) {
                    stocUp = Integer.parseInt(stoc.getText());
                    p.setStoc(stocUp);
                }
                if(produsBL.update(p,idUp)==1)
                    JOptionPane.showMessageDialog(panel,"Succes!!!");
                else
                    JOptionPane.showMessageDialog(panel,"Date invalide!!!");
            }
        });

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        frame.setContentPane(panel);
        frame.setVisible(true);

    }

    /**
     * relizeaza fereastra cu operatiile specifice comenzii
     */
    public void opOrder() {
        JFrame frame = new JFrame("Comanda");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 200);
        final JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JLabel lid = new JLabel("idComanda");
        final JTextField id = new JTextField("",3);
        JLabel lidc = new JLabel("idClient");
        final JTextField idc = new JTextField("",3);
        JLabel lidp = new JLabel("idProdus");
        final JTextField idp = new JTextField("",3);
        JLabel lcant = new JLabel("cantitate");
        final JTextField cant = new JTextField("",3);
        panel1.add(lid);
        panel1.add(id);
        panel1.add(lidc);
        panel1.add(idc);
        panel1.add(lidp);
        panel1.add(idp);
        panel1.add(lcant);
        panel1.add(cant);

        JPanel panel2 = new JPanel();
        JButton add = new JButton("Adauga produs");
        JButton finish = new JButton("Plasare comanda");
        panel2.add(add);
        panel2.add(finish);

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idAdd,idcAdd,idpAdd,cantAdd;
                idAdd = Integer.parseInt(id.getText());
                idcAdd = Integer.parseInt(idc.getText());
                idpAdd = Integer.parseInt(idp.getText());
                cantAdd = Integer.parseInt(cant.getText());
                Comanda com = new Comanda(idAdd,idcAdd,idpAdd,cantAdd);
                ComandaBL c = new ComandaBL();
                int ok = c.insert(com);
                if(ok==2)
                    JOptionPane.showMessageDialog(panel,"ID Client Invalid!!!");
                else if(ok==3)
                    JOptionPane.showMessageDialog(panel,"ID Produs Invalid!!!");
                else if(ok==4)
                    JOptionPane.showMessageDialog(panel,"Cantitate Invalida!!!");
                else if(ok==5)
                    JOptionPane.showMessageDialog(panel,"Stoc Insuficient!!!");
                else if(ok==1)
                    JOptionPane.showMessageDialog(panel,"Produs adaugat cu succes!!!");
            }
        });

        finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idAdd,idcAdd,idpAdd,cantAdd;
                idAdd = Integer.parseInt(id.getText());
                idcAdd = Integer.parseInt(idc.getText());
                idpAdd = Integer.parseInt(idp.getText());
                cantAdd = Integer.parseInt(cant.getText());
                Comanda com = new Comanda(idAdd,idcAdd,idpAdd,cantAdd);
                ComandaBL c = new ComandaBL();
                try {
                    PrintWriter writer = new PrintWriter("factura.txt", "UTF-8");
                    writer.print(c.message(com));
                    writer.close();
                    JOptionPane.showMessageDialog(panel,"Comanda finalizata");
                } catch (FileNotFoundException a) {
                    a.printStackTrace();
                } catch (UnsupportedEncodingException a){
                    a.printStackTrace();
                }
            }
        });

        panel.add(panel1);
        panel.add(panel2);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

}
