package fenetres;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controleurs.ControleurCRUDProduit;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
	private ControleurCRUDProduit CCRUDP;

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(ControleurCRUDProduit CCRUDP) {	
		this.CCRUDP = CCRUDP;
		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantit� en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String Nom = txtNom.getText();
			String PrixHT = txtPrixHT.getText();
			String Qte = txtQte.getText();
			if(this.CCRUDP.ajouterProduit(Nom, Double.parseDouble(PrixHT), Integer.parseInt(Qte)) == true) {
				JOptionPane.showMessageDialog(null, Nom + " ajout�");
			}
			else {
				JOptionPane.showMessageDialog(null, Nom + " ajout �chou�");
			}
			this.dispose();
		}
		catch(Exception message) {
			JOptionPane.showMessageDialog(null,"ajout �chou� erreur : \n" + message);
		}
	}

}