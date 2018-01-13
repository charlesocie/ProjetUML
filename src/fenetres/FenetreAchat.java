package fenetres;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controleurs.ControleurAchatVenteProduit;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurAchatVenteProduit CAVP;

	public FenetreAchat(ControleurAchatVenteProduit CAVP, String[] lesProduits) {
		this.CAVP = CAVP;
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� achet�e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.dispose();
			String Nom = (String)combo.getSelectedItem();
			String Qte = txtQuantite.getText();
			if(this.CAVP.achatStock(Nom, Integer.parseInt(Qte)) == true) {
				JOptionPane.showMessageDialog(null, Nom + " achet�");
			}
			else {
				JOptionPane.showMessageDialog(null, Nom + " achat �chou�");
			}
		}
		catch(Exception message) {
			JOptionPane.showMessageDialog(null,"a �chou� erreur : \n" + message);
		}
	}

}
