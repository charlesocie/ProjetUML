package controleurs;

import interface_class.I_Catalogue;

public class ControleurAchatVenteProduit {
	
	private I_Catalogue cat;
	
	public ControleurAchatVenteProduit(I_Catalogue cat){
		this.cat = cat;
	}

	public String[] NomProduits(){
		return cat.getNomProduits();		
	}
	
	public boolean achatStock(String nom, int qte) {
		return this.cat.acheterStock(nom, qte);
	}
	
	public boolean venteStock(String nom, int qte) {
		return this.cat.vendreStock(nom, qte);
	}
	
}
