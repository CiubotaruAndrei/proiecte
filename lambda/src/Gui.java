import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends JPanel{

    private Task task = new Task();

    public Gui() {
        JFrame frame = new JFrame("Lambda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 260);

        JPanel panel = new JPanel();
        JButton task1 = new JButton("Task1");
        JButton task2 = new JButton("Task2");
        JButton task3 = new JButton("Task3");
        JButton task4 = new JButton("Task4");
        JButton task5 = new JButton("Task5");
        frame.add(task1);
        task1.setBounds(80,10,80,35);
        frame.add(task2);
        task2.setBounds(80,55,80,35);
        frame.add(task3);
        task3.setBounds(80,100,80,35);
        frame.add(task4);
        task4.setBounds(80,145,80,35);
        frame.add(task5);
        task5.setBounds(80,190,80,35);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        task1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long nr = task.task1();
                JOptionPane.showMessageDialog(frame,"Nr. activitati: " + nr);
            }
        });

        task2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    task.task2();
                    Desktop.getDesktop().open(new java.io.File("count_activities.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        task3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    task.task3();
                    Desktop.getDesktop().open(new java.io.File("day_activities.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        task4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    task.task4();
                    Desktop.getDesktop().open(new java.io.File("duration_activities.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        task5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    task.task5();
                    Desktop.getDesktop().open(new java.io.File("90%_activities.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }

}

