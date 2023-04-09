/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proggestionale;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author gabriel.ricaldone
 */
public class ProgGestionale {

    JFrame frame = new JFrame("soos");
    JTextField nominativo;
    JTextField numeroTel;
    JPanel pannelloPopUp;
    JPanel pannelloMenu;
    JLabel titolo;
    JButton pulsanteAggiunta;
    JButton pulsanteRimuovi;
    JButton pulsanteVisione;

    ProgGestionale() {
        pannelloPopUp = new JPanel();
        numeroTel = new JTextField(10);
        nominativo = new JTextField(30);

        pannelloPopUp.add(new JLabel("Nominativo:"));
        pannelloPopUp.add(nominativo);
        pannelloPopUp.add(Box.createVerticalStrut(15)); // a spacer
        pannelloPopUp.add(new JLabel("Telefono:"));
        pannelloPopUp.add(numeroTel);

        pannelloMenu = new JPanel();
        titolo = new JLabel("Rubrica");

        frame.setLayout(null);
        frame.add(pannelloMenu);
        frame.setBounds(0, 0, 360, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pannelloMenu.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pannelloMenu.setLayout(new GridLayout(6, 0));

        pulsanteAggiunta = new JButton("Aggiungi contatto");
        pulsanteAggiunta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, pannelloPopUp,
                        "Aggiungi un contatto", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("x value: " + nominativo.getText());
                    System.out.println("y value: " + numeroTel.getText());
                }
            }

        });

        pulsanteRimuovi = new JButton("Rimuovi contatto");
        pulsanteRimuovi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        pulsanteVisione = new JButton("Vedi contatti");
        pulsanteVisione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        pannelloMenu.add(titolo);
        pannelloMenu.add(pulsanteAggiunta);
        pannelloMenu.add(pulsanteRimuovi);
        pannelloMenu.add(pulsanteVisione);

        frame.setVisible(true);
    }

    /*
     * Gestione inserimento, modifica, cancellazione ed ordinamento record.
     * Gestione menu. Gestione GUI Aggiungi contatto: Permette l'inserimento di
     * nome, cognome e numero di telefono. Rimuovi contatto: Rimuove tutti i
     * dati relativi ad un contatto. Lista contatti: Mostra visivamente i
     * contatti.
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ProgGestionale();
        /*
        myPanel.add(new JLabel("x:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("y:"));
        myPanel.add(yField);
        String resp = "hello";
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("x value: " + xField.getText());
            System.out.println("y value: " + yField.getText());
        }
        System.out.println(result);*/
    }

}
