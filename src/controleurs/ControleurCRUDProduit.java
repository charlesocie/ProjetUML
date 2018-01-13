package controleurs;

import interface_class.I_Catalogue;

public class ControleurCRUDProduit {
	
	private I_Catalogue cat;
	
	public ControleurCRUDProduit(I_Catalogue cat){
		this.cat = cat;
	}

	public boolean ajouterProduit(String txtNom, double txtPrixHT, int txtQte) {
		try{
		if(cat.addProduit(txtNom, txtPrixHT, txtQte)){
		return true;
		}
		else {
			return false;
		}
		}
		catch(Exception e){
			return false;
		}
	}
	
	public String[] NomProduits(){
		return cat.getNomProduits();		
	}
	
	public boolean supprimerProduit(String nom){
		return this.cat.removeProduit(nom);
	}
	
}
