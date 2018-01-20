package interface_class;

import java.text.NumberFormat;

public class Produit implements I_Produit {
	
	int quantiteStock;
	String nom;
	double prixUnitaireHT;
	double tauxTVA;
	int idcat = 0;
	
	
	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
			this.quantiteStock = quantiteStock;
			this.nom = nom;
			this.prixUnitaireHT = prixUnitaireHT;
			this.tauxTVA = 20;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		// TODO Auto-generated method stub
		this.quantiteStock = this.quantiteStock + qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		// TODO Auto-generated method stub
		this.quantiteStock = this.quantiteStock - qteVendue;
		return true;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom.replaceAll("	", " ");
	}

	@Override
	public int getQuantite() {
		// TODO Auto-generated method stub
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		// TODO Auto-generated method stub
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		// TODO Auto-generated method stub
		double PrixTTC = this.prixUnitaireHT * ((this.tauxTVA/100) + 1); 	
		return PrixTTC;
	}

	@Override
	public double getPrixStockTTC() {
		// TODO Auto-generated method stub
		double PrixStockTTC = this.getPrixUnitaireTTC() * this.quantiteStock;
		return PrixStockTTC;
	}

	@Override
	public int getidcat() {
		return idcat;
	}


	public String toString() {
		final NumberFormat instance = NumberFormat.getNumberInstance();
		instance.setMinimumFractionDigits(2);
		instance.setMaximumFractionDigits(2);
		instance.setGroupingUsed(false);
		String Produit = null;
			Produit = this.getNom() + " - prix HT : " + instance.format(this.getPrixUnitaireHT())+ " € - prix TTC : " + instance.format(this.getPrixUnitaireTTC()) + " € - quantité en stock : " + this.getQuantite() + "\n";
		return Produit;
	}

	@Override
	public void setidcat(int idcat) {
		this.idcat = idcat;
	}

}
