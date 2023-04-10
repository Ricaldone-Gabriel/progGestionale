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
import java.util.ArrayList;
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
    
    JPanel pannelloProg;
    JPanel pannelloPopUp;
    JPanel pannelloMenu;
    JPanel pannelloRubrica;
    JPanel pannelloRimuovi;
    
    JTextField nominativo;
    JTextField numeroTel;
    
    JLabel titolo;
    
    JButton pulsanteAggiunta;
    JButton pulsanteRimuovi;
    JButton pulsanteVisione;
    JButton pulsanteIndietroRimuovi;
    JButton pulsanteIndietroContatti;
    ActionListener indietro;
    ArrayList<Contatto> rubrica;
    
    ProgGestionale() {
        rubrica = new ArrayList<Contatto>();
        
        pannelloProg = new JPanel();
        pannelloRubrica = new JPanel();
        pannelloMenu = new JPanel();
        pannelloRimuovi = new JPanel();
        
        titolo = new JLabel("Rubrica");
        
        pulsanteAggiunta = new JButton("Aggiungi contatto");
        pulsanteRimuovi = new JButton("Rimuovi contatto");
        pulsanteVisione = new JButton("Vedi contatti");
        pulsanteIndietroContatti = new JButton("Indietro");
        pulsanteIndietroRimuovi = new JButton("Indietro");
        
        pannelloPopUp = new JPanel();
        numeroTel = new JTextField(10);
        nominativo = new JTextField(30);
        
        pannelloPopUp.add(new JLabel("Nominativo:"));
        pannelloPopUp.add(nominativo);
        pannelloPopUp.add(new JLabel("Telefono:"));
        pannelloPopUp.add(numeroTel);
        
        frame.setLayout(null);
        frame.setBounds(0, 0, 360, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pannelloRimuovi.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pannelloRimuovi.setBackground(Color.blue);
        pannelloRimuovi.setVisible(false);
        pannelloRimuovi.add(pulsanteIndietroRimuovi);
                
        pannelloRubrica.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pannelloRubrica.setBackground(Color.red);
        pannelloRubrica.setVisible(false);
        pannelloRubrica.add(pulsanteIndietroContatti);
        
        pannelloMenu.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pannelloMenu.setLayout(new GridLayout(6, 0));

        pannelloMenu.add(titolo);
        pannelloMenu.add(pulsanteAggiunta);
        pannelloMenu.add(pulsanteRimuovi);
        pannelloMenu.add(pulsanteVisione);
        
        frame.add(pannelloRubrica);
        frame.add(pannelloMenu);
        frame.add(pannelloRimuovi);
        
        indietro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pannelloMenu.setVisible(true);
                pannelloRubrica.setVisible(false);
                pannelloRimuovi.setVisible(false);
            }
        };
        
        pulsanteIndietroContatti.addActionListener(indietro);
        pulsanteIndietroRimuovi.addActionListener(indietro);
        
        pulsanteAggiunta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, pannelloPopUp,
                        "Aggiungi un contatto", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    rubrica.add(new Contatto(nominativo.getText(),numeroTel.getText()));
                }
            }
        });

        pulsanteRimuovi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pannelloMenu.setVisible(false);
                pannelloRimuovi.setVisible(true);
            }
        });
        
        pulsanteVisione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pannelloMenu.setVisible(false);
                pannelloRubrica.setVisible(true);
            }
        });

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
