package controleurs;

import interface_class.I_Catalogue;

public class ControleurEtatStock {
	
	private I_Catalogue cat;
	
	public ControleurEtatStock(I_Catalogue cat){
		this.cat = cat;
	}
	
	public String afficher(){
		return cat.toString();
	}

}
