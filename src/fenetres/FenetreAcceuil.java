package fenetres;
import controleurs.ControleurCatalogue;
import controleurs.ControleurGeneral;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreAcceuil extends JFrame implements ActionListener {

    private JButton btAjouter, btSupprimer, btSelectionner;
    private JTextField txtAjouter;
    private JLabel lbNbCatalogues;
    private JComboBox cmbSupprimer, cmbSelectionner;
    private TextArea taDetailCatalogues;
    ControleurCatalogue CC;
    private String[] tab;

    public FenetreAcceuil() {

        CC = new ControleurCatalogue();
        setTitle("Catalogues");
        setBounds(500, 500, 200, 125);
        Container contentPane = getContentPane();
        JPanel panInfosCatalogues = new JPanel();
        JPanel panNbCatalogues = new JPanel();
        JPanel panDetailCatalogues = new JPanel();
        JPanel panGestionCatalogues = new JPanel();
        JPanel panAjouter = new JPanel();
        JPanel panSupprimer = new JPanel();
        JPanel panSelectionner = new JPanel();
        panNbCatalogues.setBackground(Color.white);
        panDetailCatalogues.setBackground(Color.white);
        panAjouter.setBackground(Color.gray);
        panSupprimer.setBackground(Color.lightGray);
        panSelectionner.setBackground(Color.gray);

        panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
        lbNbCatalogues = new JLabel();
        panNbCatalogues.add(lbNbCatalogues);

        taDetailCatalogues = new TextArea();
        taDetailCatalogues.setEditable(false);
        new JScrollPane(taDetailCatalogues);
        taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
        panDetailCatalogues.add(taDetailCatalogues);

        panAjouter.add(new JLabel("Ajouter un catalogue : "));
        txtAjouter = new JTextField(10);
        panAjouter.add(txtAjouter);
        btAjouter = new JButton("Ajouter");
        panAjouter.add(btAjouter);

        panSupprimer.add(new JLabel("Supprimer un catalogue : "));
        cmbSupprimer = new JComboBox();
        cmbSupprimer.setPreferredSize(new Dimension(100, 20));
        panSupprimer.add(cmbSupprimer);
        btSupprimer = new JButton("Supprimer");
        panSupprimer.add(btSupprimer);

        panSelectionner.add(new JLabel("Selectionner un catalogue : "));
        cmbSelectionner = new JComboBox();
        cmbSelectionner.setPreferredSize(new Dimension(100, 20));
        panSelectionner.add(cmbSelectionner);
        btSelectionner = new JButton("Selectionner");
        panSelectionner.add(btSelectionner);

        panGestionCatalogues.setLayout (new BorderLayout());
        panGestionCatalogues.add(panAjouter, "North");
        panGestionCatalogues.add(panSupprimer);
        panGestionCatalogues.add(panSelectionner, "South");

        panInfosCatalogues.setLayout(new BorderLayout());
        panInfosCatalogues.add(panNbCatalogues, "North");
        panInfosCatalogues.add(panDetailCatalogues, "South");

        contentPane.add(panInfosCatalogues, "North");
        contentPane.add(panGestionCatalogues, "South");
        pack();

        btAjouter.addActionListener(this);
        btSupprimer.addActionListener(this);
        btSelectionner.addActionListener(this);

        tab  = CC.listeCatalogueAvecouSansProduit(false) ;
        this.modifierNbCatalogues(tab.length);
        modifierListesCatalogues(tab);
        String[] tab2 = CC.listeCatalogueAvecouSansProduit(true) ;
        modifierDetailCatalogues(tab2);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAjouter)
        {
            boolean catexiste = false;
            String texteAjout = txtAjouter.getText();
            for( int i = 0 ; i<tab.length ; i++){
                if(tab[i].equals(texteAjout)){
                    catexiste = true;
                }
            }
            if (catexiste == false)
            {
                CC.ajoutCat(texteAjout);
                txtAjouter.setText(null);
                JOptionPane.showMessageDialog(null,texteAjout +" créée");
                updateWindows();
            }
            else {
                JOptionPane.showMessageDialog(null,texteAjout +" existe déja");
            }
        }
        if (e.getSource() == btSupprimer)
        {
            String texteSupprime = (String)cmbSupprimer.getSelectedItem();
            if (texteSupprime != null)
                CC.suppressionCat(texteSupprime);
                JOptionPane.showMessageDialog(null,texteSupprime +" supprimé");
                updateWindows();
        }
        if (e.getSource() == btSelectionner)
        {
            String texteSelection = (String)cmbSelectionner.getSelectedItem();
            if (texteSelection != null)
            {
                new FenetrePrincipale(texteSelection, CC.getidCat(texteSelection));
                JOptionPane.showMessageDialog(null," ouverture du Catalogue" + texteSelection + "effectué");
                this.dispose();
            }
        }
    }

    private  void updateWindows(){
        String[] tab  = CC.listeCatalogueAvecouSansProduit(false) ;
        this.modifierNbCatalogues(tab.length);
        modifierListesCatalogues(tab);
        String[] tab2 = CC.listeCatalogueAvecouSansProduit(true) ;
        modifierDetailCatalogues(tab2);
        setVisible(true);
    }

    private void modifierListesCatalogues(String[] nomsCatalogues) {
        cmbSupprimer.removeAllItems();
        cmbSelectionner.removeAllItems();
        if (nomsCatalogues != null)
            for (int i=0 ; i<nomsCatalogues.length; i++)
            {
                cmbSupprimer.addItem(nomsCatalogues[i]);
                cmbSelectionner.addItem(nomsCatalogues[i]);
            }
    }

    private void modifierNbCatalogues(int nb)
    {
        lbNbCatalogues.setText(nb + " catalogues");
    }

    private void modifierDetailCatalogues(String[] detailCatalogues) {
        taDetailCatalogues.setText("");
        if (detailCatalogues != null) {
            for (int i=0 ; i<detailCatalogues.length; i++)
                taDetailCatalogues.append(detailCatalogues[i]+"\n");
        }
    }

    public static void main(String[] args) {
        new FenetreAcceuil();
    }
}
