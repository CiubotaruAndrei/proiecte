import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Gui extends JPanel {

    private Bank bank;

    public Gui() {
        bank = new Bank();
        JFrame frame = new JFrame("Bank");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(400, 100);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                bank.serialization();
                e.getWindow().dispose();
            }
        });

        JPanel firstPanel = new JPanel();
        JButton personOp = new JButton("Person");
        personOp.setPreferredSize(new Dimension(100, 50));
        JButton accountOp = new JButton("Account");
        accountOp.setPreferredSize(new Dimension(100, 50));
        JButton operOp = new JButton("Operations");
        operOp.setPreferredSize(new Dimension(100, 50));
        firstPanel.add(personOp, BorderLayout.LINE_START);
        firstPanel.add(accountOp, BorderLayout.CENTER);
        firstPanel.add(operOp, BorderLayout.LINE_END);

        //add listeners
        personOp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opPerson();
            }
        });

        accountOp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opAccount();
            }
        });

        operOp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opOper();
            }
        });

        frame.setContentPane(firstPanel);
        frame.setVisible(true);
    }

    public void opPerson() {
        final JFrame frame = new JFrame("Person");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        JPanel panel1 = new JPanel();
        JLabel lid = new JLabel("id");
        final JTextField id = new JTextField("", 3);
        JLabel lnume = new JLabel("nume");
        final JTextField nume = new JTextField("", 12);
        JLabel ladresa = new JLabel("adresa");
        final JTextField adresa = new JTextField("", 12);
        JLabel ltelefon = new JLabel("telefon");
        final JTextField telefon = new JTextField("", 8);
        panel1.add(lid);
        panel1.add(id);
        panel1.add(lnume);
        panel1.add(nume);
        panel1.add(ladresa);
        panel1.add(adresa);
        panel1.add(ltelefon);
        panel1.add(telefon);
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        JButton add = new JButton("add");
        JButton edit = new JButton("edit");
        JButton delete = new JButton("delete");
        JButton view = new JButton("view");
        panel2.add(add);
        panel2.add(edit);
        panel2.add(delete);
        panel2.add(view);
        panel2.setLayout(new FlowLayout());
        final JPanel panel = new JPanel();

        panel.add(panel1);
        panel.add(panel2);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        final JTable table = new JTable();
        JScrollPane tpanel = new JScrollPane(table);
        panel.add(tpanel);

        frame.setContentPane(panel);
        frame.setVisible(true);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeAd, adresaAd, telefonAd;
                int idAd;
                if (id.getText().equals("") || nume.getText().equals("") || adresa.getText().equals("") || telefon.getText().equals(""))
                    JOptionPane.showMessageDialog(panel, "Date invalide");
                else {
                    idAd = Integer.parseInt(id.getText());
                    numeAd = nume.getText();
                    adresaAd = adresa.getText();
                    telefonAd = telefon.getText();
                    Person p = new Person(idAd, numeAd, adresaAd, telefonAd);
                    int ok = bank.addPerson(p);
                    if(ok==2)
                        JOptionPane.showMessageDialog(panel, "Id existent");
                    else if(ok==1)
                        JOptionPane.showMessageDialog(panel, "Succes");
                }
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object column[]={"Id","Nume","Adresa","Telefon"};
                Object rows[][] = new Object[50][50];
                List<Person> list = bank.getPersons();
                int i=0;
                for(Person p : list) {
                    rows[i][0] = p.getId();
                    rows[i][1] = p.getNume();
                    rows[i][2] = p.getAdresa();
                    rows[i][3] = p.getTelefon();
                    i++;
                }

                DefaultTableModel model = new DefaultTableModel(rows,column);
                table.setEnabled(false);
                table.setModel(model);

                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        int col = table.columnAtPoint(e.getPoint());
                        if(col==0) {
                            try {
                                int id = (Integer) table.getValueAt(row, col);
                                JOptionPane.showMessageDialog(panel, bank.findById(id).toString());
                            } catch (NullPointerException a) {
                                JOptionPane.showMessageDialog(panel, "empty");
                            }
                        }
                    }
                });
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int delId;
                if(id.getText().equals(""))
                    JOptionPane.showMessageDialog(panel,"Id invalid");
                else {
                    delId = Integer.parseInt(id.getText());
                    int ok;
                    ok = bank.removePerson(delId);
                    if(ok==1)
                        JOptionPane.showMessageDialog(panel,"Succes");
                    else if(ok==2)
                        JOptionPane.showMessageDialog(panel,"Perosana nu exista");
                }
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeUp, adresaUp, telefonUp;
                int idUp;
                if (id.getText().equals(""))
                    JOptionPane.showMessageDialog(panel, "Date invalide");
                else {
                    idUp = Integer.parseInt(id.getText());
                    numeUp = nume.getText();
                    adresaUp = adresa.getText();
                    telefonUp = telefon.getText();
                    int ok = bank.editPerson(idUp,numeUp,adresaUp,telefonUp);
                    if(ok==1)
                        JOptionPane.showMessageDialog(panel,"Succes");
                    else if(ok==2)
                        JOptionPane.showMessageDialog(panel,"Persoana nu exista");
                }
            }
        });
    }

    public void opAccount() {
        final JFrame frame = new JFrame("Account");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        JPanel panel1 = new JPanel();
        JLabel lpid = new JLabel("person id");
        final JTextField pid = new JTextField("", 4);
        JLabel lid = new JLabel("id");
        final JTextField id = new JTextField("", 4);
        String[] tipuri= {"SavingAccount","SpendingAccount"};
        final JComboBox tip = new JComboBox(tipuri);
        JLabel lval = new JLabel("valoare");
        final JTextField val = new JTextField("", 5);
        JLabel ldob = new JLabel("dobanda");
        final JTextField dob = new JTextField("", 5);
        panel1.add(lpid);
        panel1.add(pid);
        panel1.add(lid);
        panel1.add(id);
        panel1.add(tip);
        panel1.add(lval);
        panel1.add(val);
        panel1.add(ldob);
        panel1.add(dob);
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        JButton add = new JButton("add");
        JButton edit = new JButton("edit");
        JButton delete = new JButton("delete");
        JButton view = new JButton("view");
        panel2.add(add);
        panel2.add(edit);
        panel2.add(delete);
        panel2.add(view);
        panel2.setLayout(new FlowLayout());
        final JPanel panel = new JPanel();

        panel.add(panel1);
        panel.add(panel2);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        final JTable table = new JTable();
        JScrollPane tpanel = new JScrollPane(table);
        panel.add(tpanel);

        frame.setContentPane(panel);
        frame.setVisible(true);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idpAd, idAd;
                float valAd=0, dobAd=0;
                if (pid.getText().equals("") || id.getText().equals(""))
                    JOptionPane.showMessageDialog(panel, "Date invalide");
                else {
                    idAd = Integer.parseInt(id.getText());
                    idpAd = Integer.parseInt(pid.getText());
                    if(!dob.getText().equals(""))
                        dobAd = Float.parseFloat(dob.getText());
                    String tipacc = (String)tip.getSelectedItem();
                    if(dobAd<0)
                        JOptionPane.showMessageDialog(panel, "Date invalide");
                    else {
                        Account a;
                        if (tipacc.equals("SavingAccount"))
                            a = new SavingAccount(idAd, idpAd, "SavingAccount", valAd, dobAd);
                        else
                            a = new SpendingAccount(idAd, idpAd, "SpendingAccount", valAd);
                        int ok = bank.addAccount(a);
                        if(ok==1)
                            JOptionPane.showMessageDialog(panel, "Succes");
                        else if(ok==2)
                            JOptionPane.showMessageDialog(panel, "Id persoana invalid");
                        else if(ok==3)
                            JOptionPane.showMessageDialog(panel, "Contul exista");
                    }
                }
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object column[] = {"Person Id", "Id", "Tip", "Valoare", "Dobanda"};
                Object rows[][] = new Object[50][50];
                List<Account> list = bank.getAccounts();
                int i = 0;
                for (Account a : list) {
                    rows[i][0] = a.getPersonId();
                    rows[i][1] = a.getId();
                    rows[i][2] = a.getTip();
                    rows[i][3] = a.getValoare();
                    if (a.getTip().equals("SavingAccount"))
                        rows[i][4] = ((SavingAccount) a).getDobanda();
                    else rows[i][4] = "";
                    i++;
                }

                DefaultTableModel model = new DefaultTableModel(rows,column);
                table.setEnabled(false);
                table.setModel(model);

                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        int col = table.columnAtPoint(e.getPoint());
                        if(col==0) {
                            try {
                                int id = (Integer) table.getValueAt(row, col);
                                JOptionPane.showMessageDialog(panel, bank.findById(id).toString());
                            } catch (NullPointerException a) {
                                JOptionPane.showMessageDialog(panel, "empty");
                            }
                        }
                        else if(col==1) {
                            try {
                                int idp = (Integer) table.getValueAt(row, col-1);
                                int id = (Integer) table.getValueAt(row, col);
                                JOptionPane.showMessageDialog(panel, bank.findAcc(idp,id).toString());
                            } catch (NullPointerException a) {
                                JOptionPane.showMessageDialog(panel, "empty");
                            }
                        }

                    }
                });
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int delIdp, delId;
                if(pid.getText().equals("") || id.getText().equals(""))
                    JOptionPane.showMessageDialog(panel,"Id invalid");
                else {
                    delIdp = Integer.parseInt(pid.getText());
                    delId = Integer.parseInt(id.getText());
                    int ok;
                    ok = bank.removeAccount(delIdp,delId);
                    if(ok==1)
                        JOptionPane.showMessageDialog(panel,"Succes");
                    else if(ok==2)
                        JOptionPane.showMessageDialog(panel,"Perosana nu exista");
                    else if(ok==3)
                        JOptionPane.showMessageDialog(panel,"Contul nu exista");
                }
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUp, idpUp;
                float valUp=0, dobUp=0;
                if (pid.getText().equals("") || id.getText().equals(""))
                    JOptionPane.showMessageDialog(panel, "Date invalide");
                else {
                    idUp = Integer.parseInt(id.getText());
                    idpUp = Integer.parseInt(pid.getText());
                    if(!val.getText().equals(""))
                        valUp = Float.parseFloat(val.getText());
                    if(!dob.getText().equals(""))
                        dobUp = Float.parseFloat(dob.getText());
                    int ok = bank.editAccount(idpUp,idUp,valUp,dobUp);
                    if(ok==1)
                        JOptionPane.showMessageDialog(panel,"Succes");
                    else if(ok==2)
                        JOptionPane.showMessageDialog(panel,"Perosana nu exista");
                    else if(ok==3)
                        JOptionPane.showMessageDialog(panel,"Contul nu exista");
                }
            }
        });

    }

    public void opOper() {
        JFrame frame = new JFrame("Operations");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 200);
        final JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JLabel lidp = new JLabel("Id Persona");
        final JTextField idp = new JTextField("",3);
        JLabel lida = new JLabel("Id Cont");
        final JTextField ida = new JTextField("",3);
        JLabel lsum = new JLabel("Suma");
        final JTextField sum = new JTextField("",5);
        panel1.add(lidp);
        panel1.add(idp);
        panel1.add(lida);
        panel1.add(ida);
        panel1.add(lsum);
        panel1.add(sum);

        JPanel panel2 = new JPanel();
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        panel2.add(deposit);
        panel2.add(withdraw);

        panel.add(panel1);
        panel.add(panel2);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        frame.setContentPane(panel);
        frame.setVisible(true);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idpAd, idAd;
                float sumAd=0;
                if(idp.getText().equals("") || ida.getText().equals(""))
                    JOptionPane.showMessageDialog(panel,"Date invalide");
                else {
                    idpAd = Integer.parseInt(idp.getText());
                    idAd = Integer.parseInt(ida.getText());
                    if(!sum.getText().equals(""))
                        sumAd = Float.parseFloat(sum.getText());
                    if(sumAd<=0)
                        JOptionPane.showMessageDialog(panel,"Suma invalida");
                    else {
                        int ok=bank.deposit(idpAd, idAd, sumAd);
                        if(ok==1)
                            JOptionPane.showMessageDialog(panel,"Succes");
                        else if(ok==2)
                            JOptionPane.showMessageDialog(panel,"Perosana nu exista");
                        else if(ok==3)
                            JOptionPane.showMessageDialog(panel,"Contul nu exista");
                        else if(ok==4)
                            JOptionPane.showMessageDialog(panel,"SavingAccount- o singura depunere");
                    }
                }
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idpAd, idAd;
                float sumAd=0;
                if(idp.getText().equals("") || ida.getText().equals(""))
                    JOptionPane.showMessageDialog(panel,"Date invalide");
                else {
                    idpAd = Integer.parseInt(idp.getText());
                    idAd = Integer.parseInt(ida.getText());
                    if(!sum.getText().equals(""))
                        sumAd = Float.parseFloat(sum.getText());
                    if(sumAd<=0)
                        JOptionPane.showMessageDialog(panel,"Suma invalida");
                    else {
                        int ok=bank.withddraw(idpAd, idAd, sumAd);
                        if(ok==1)
                            JOptionPane.showMessageDialog(panel,"Succes");
                        else if(ok==2)
                            JOptionPane.showMessageDialog(panel,"Perosana nu exista");
                        else if(ok==3)
                            JOptionPane.showMessageDialog(panel,"Contul nu exista");
                        else if(ok==4)
                            JOptionPane.showMessageDialog(panel,"SavingAccount - cont gol");
                        else if(ok==5)
                            JOptionPane.showMessageDialog(panel,"Fonduri insuficiente");

                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}
