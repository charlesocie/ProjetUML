package fenetres;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controleurs.ControleurAchatVenteProduit;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurAchatVenteProduit CAVP;

	public FenetreVente(ControleurAchatVenteProduit CAVP, String[] lesProduits) {
		this.CAVP = CAVP;
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.dispose();
			String Nom = (String)combo.getSelectedItem();
			String Qte = txtQuantite.getText();
			if(this.CAVP.venteStock(Nom, Integer.parseInt(Qte)) == true) {
				JOptionPane.showMessageDialog(null, Nom + " vendu");
			}
			else {
				JOptionPane.showMessageDialog(null, Nom + " vente �chou�");
			}
		}
		catch(Exception message) {
			JOptionPane.showMessageDialog(null,"vente �chou� erreur : \n" + message);
		}
	}

}
