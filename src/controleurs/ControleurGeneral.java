package controleurs;

import interface_class.Catalogue;
import interface_class.I_Catalogue;

public class ControleurGeneral {
	
	private ControleurAchatVenteProduit CAVP;
	private ControleurCRUDProduit CCRUDP;
	private ControleurEtatStock CES;
	
	public ControleurGeneral(){
		
		I_Catalogue cat = new Catalogue();
		setCAVP(new ControleurAchatVenteProduit(cat));
		setCCRUDP(new ControleurCRUDProduit(cat));
		setCES(new ControleurEtatStock(cat));
		
		}
	

	public ControleurCRUDProduit getCCRUDP() {
		return CCRUDP;
	}

	public void setCCRUDP(ControleurCRUDProduit cCRUDP) {
		CCRUDP = cCRUDP;
	}

	public ControleurEtatStock getCES() {
		return CES;
	}

	public void setCES(ControleurEtatStock cES) {
		CES = cES;
	}


	public ControleurAchatVenteProduit getCAVP() {
		return CAVP;
	}


	public void setCAVP(ControleurAchatVenteProduit cAVP) {
		CAVP = cAVP;
	}

}
