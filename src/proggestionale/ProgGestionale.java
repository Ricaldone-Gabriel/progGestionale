/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proggestionale;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
    JButton pulsanteMenuRimuovi;
    JButton pulsanteRimuovi;
    JButton pulsanteVisione;
    JButton pulsanteIndietroRimuovi;
    JButton pulsanteIndietroContatti;

    JList<Contatto> nominativiLista;
    JList<Contatto> rimuoviLista;

    ActionListener indietro;
    ArrayList<Contatto> rubrica;

    ProgGestionale() {
        rubrica = new ArrayList<Contatto>();
        DefaultListModel<Contatto> listModel = new DefaultListModel<>();

        try {
            FileReader f = new FileReader("Rubrica.txt");
            BufferedReader fIn = new BufferedReader(f);
            String next = "";
            String[] result;
            do {
                next = fIn.readLine();
                if (next != null) {
                    result = next.split("-");
                    listModel.addElement(new Contatto(result[0], result[1]));
                    rubrica.add(new Contatto(result[0], result[1]));
                }
            } while (next != null);
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(ProgGestionale.class.getName()).log(Level.SEVERE, null, ex);
        }

        nominativiLista = new JList(listModel);
        rimuoviLista = new JList(listModel);
        rimuoviLista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        pannelloProg = new JPanel();
        pannelloRubrica = new JPanel();
        pannelloMenu = new JPanel();
        pannelloRimuovi = new JPanel();

        titolo = new JLabel("Rubrica");
        titolo.setFont(new Font(titolo.getName(), Font.PLAIN, 30));

        pulsanteAggiunta = new JButton("Aggiungi contatto");
        pulsanteMenuRimuovi = new JButton("Rimuovi contatto");
        pulsanteVisione = new JButton("Vedi contatti");
        pulsanteIndietroContatti = new JButton("Indietro");
        pulsanteIndietroRimuovi = new JButton("Indietro");
        pulsanteRimuovi = new JButton("Rimuovi");

        pannelloPopUp = new JPanel();
        numeroTel = new JTextField(10);
        nominativo = new JTextField(30);

        pannelloPopUp.add(new JLabel("Nominativo:"));
        pannelloPopUp.add(nominativo);
        pannelloPopUp.add(new JLabel("Telefono:"));
        pannelloPopUp.add(numeroTel);

        frame.setLayout(null);
        frame.setBounds(0, 0, 360, 500);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                try {
                    FileWriter f = new FileWriter("Rubrica.txt");
                    PrintWriter fOut = new PrintWriter(f);
                    for (Contatto contatto : rubrica) {
                        fOut.println(contatto);
                    }
                    fOut.close();
                    f.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProgGestionale.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });

        pannelloRimuovi.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pannelloRimuovi.setVisible(false);
        pannelloRimuovi.add(rimuoviLista);
        pannelloRimuovi.add(pulsanteRimuovi);
        pannelloRimuovi.add(pulsanteIndietroRimuovi);

        pannelloRubrica.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pannelloRubrica.setVisible(false);
        pannelloRubrica.add(nominativiLista);
        pannelloRubrica.add(pulsanteIndietroContatti);

        pannelloMenu.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pannelloMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));

        pannelloMenu.add(titolo);
        pannelloMenu.add(pulsanteAggiunta);
        pannelloMenu.add(pulsanteMenuRimuovi);
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

        pulsanteRimuovi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = rimuoviLista.getSelectedIndex();
                rubrica.remove(index);
                listModel.remove(index);
            }
        });

        pulsanteAggiunta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result;
                result = JOptionPane.showConfirmDialog(null, pannelloPopUp,
                        "Aggiungi un contatto", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    if (!(nominativo.getText().isBlank() || numeroTel.getText().isBlank() || nominativo.getText().isEmpty() || numeroTel.getText().isEmpty())) {
                        if (numeroTel.getText().length() == 9) {
                            rubrica.add(new Contatto(nominativo.getText(), numeroTel.getText()));
                            listModel.addElement(new Contatto(nominativo.getText(), numeroTel.getText()));
                            nominativo.setText(null);
                            numeroTel.setText(null);
                        }
                    }
                } else {
                    nominativo.setText(null);
                    numeroTel.setText(null);
                }
            }
        });

        pulsanteMenuRimuovi.addActionListener(new ActionListener() {
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
