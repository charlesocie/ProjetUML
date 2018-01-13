package fenetres;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controleurs.ControleurCRUDProduit;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private ControleurCRUDProduit CCRUDP;
	
	public FenetreSuppressionProduit(ControleurCRUDProduit CCRUDP, String lesProduits[]) {
		this.CCRUDP = CCRUDP;
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.dispose();
			String nom = (String)combo.getSelectedItem();
			if(this.CCRUDP.supprimerProduit(nom) == true) {
				JOptionPane.showMessageDialog(null, nom + " supprimer");
			}
			else {
				JOptionPane.showMessageDialog(null, nom + " suppression �chou�");
			}
		}
		catch(Exception message) {
			JOptionPane.showMessageDialog(null,"suppression �chou� erreur : \n" + message);
		}
		
	}

}
