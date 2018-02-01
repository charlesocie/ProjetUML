package interface_class;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAO.I_Factory;
import DAO.I_ProduitDAO;
import DAO.SQLFactory;
import DAO.XMLFactory;


public class Catalogue implements I_Catalogue {

	private String nom;
	private int id;
	private List<I_Produit> listProduits = new ArrayList<I_Produit>();
	private I_Factory factory;
	private I_ProduitDAO c;

	public Catalogue(String nom, I_Factory factory) {
		this.nom = nom;
		c = factory.createProduit();
        LoadCatalogue();
	}



    public void LoadCatalogue() {
        List<I_Produit> listeid = new ArrayList<I_Produit>();
        listeid.addAll(c.findAll(nom));
        if(listeid.size()!=0){
            for(int i=0; i<listeid.size() ; i++){
                String nomp = listeid.get(i).getNom();
                int qte = listeid.get(i).getQuantite();
                double prix = listeid.get(i).getPrixUnitaireHT();
                I_Produit produit = new Produit(nomp, prix, qte);
                this.addProduit(produit);
            }
        }
    }

    public Catalogue(I_Produit produit, String nom) {
		this.nom = nom;
	    this.listProduits.add(produit);
	}

	public Catalogue(I_Produit [] produit, String nom) {
        this.nom = nom;
		for(int i = 0; i<produit.length; i++) {
			this.listProduits.add(produit[i]);
		}
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		// TODO Auto-generated method stub
		try {
			int i;
			boolean doublenom = false;
			for(i = 0; i< this.listProduits.size(); i++) {
					String tabnom = produit.getNom().trim();
					if(this.listProduits.get(i).getNom().equals(produit.getNom()) || this.listProduits.get(i).getNom().equals(tabnom)){
						doublenom = true;
					}
			}
			if(produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0 && !doublenom){

				this.listProduits.add(produit);
				return true;
			}
			return false;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}

	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		// TODO Auto-generated method stub
		try {
			int i;
			boolean doublenom = false;
			String tabnom = nom.trim() ;
			for(i = 0; i< this.listProduits.size(); i++) {
					if(this.listProduits.get(i).getNom().equals(tabnom)){
						doublenom = true;
					}
			}
			I_Produit produit = new Produit(tabnom, prix, qte);
			if(produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0 && !doublenom){
				produit.setnomcat(nom);
				produit.setidcat(id);
				c.create(produit);
				this.listProduits.add(produit);
				return true;
			}
			return false;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}

	}

	@Override
	public int addProduits(List<I_Produit> l) {
		// TODO Auto-generated method stub
		try {
			int i, j;
			boolean doublenom = false;
			int nbproduitajouter = 0;
			for(i = 0; i<l.size(); i++) {
				String tabnom = l.get(i).getNom().trim() ;
				for(j = 0; j< this.listProduits.size(); j++) {
					if(this.listProduits.get(j).getNom().equals(tabnom)){
						doublenom = true;
					}
				}
				if(l.get(i).getPrixUnitaireHT() > 0 && l.get(i).getQuantite() >= 0 && doublenom == false){
					c.create(l.get(i));
					this.listProduits.add(l.get(i));
					nbproduitajouter ++;
				}
				doublenom = false;
			}
			return nbproduitajouter;
		}
		catch(Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public boolean removeProduit(String nom) {
		// TODO Auto-generated method stub
		try {
			for(int i = 0; i<this.listProduits.size(); i++) {
				if (this.listProduits.get(i).getNom() == nom) {
					c.supprimer(this.listProduits.get(i), this.id);
					this.listProduits.remove(this.listProduits.get(i));
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		try {
			if(qteAchetee > 0){
				for(int i = 0; i<this.listProduits.size(); i++) {
					if(this.listProduits.get(i).getNom().equals(nomProduit) == true) {
						this.listProduits.get(i).ajouter(qteAchetee);
						c.gestionStockProduit(this.listProduits.get(i), this.id	);
						return true;
					}
				}
				return false;
			}
			else{
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		// TODO Auto-generated method stub
		try {
			if(qteVendue > 0)
			{
				int i = 0;
				while(!this.listProduits.get(i).getNom().equals(nomProduit)) {
					i++;
				}
				if(this.listProduits.get(i).getNom().equals(nomProduit) && this.listProduits.get(i).getQuantite() >= qteVendue){
					this.listProduits.get(i).enlever(qteVendue);
					c.gestionStockProduit(this.listProduits.get(i), this.id);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public String[] getNomProduits() {
		// TODO Auto-generated method stub
		try {
			String [] tabProduits = new String [this.listProduits.size()];
			for(int i = 0; i<this.listProduits.size(); i++) {

				tabProduits[i] = this.listProduits.get(i).getNom().replaceAll("	", " ");
			}
			Arrays.sort(tabProduits);
			return tabProduits;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public double getMontantTotalTTC() {
		// TODO Auto-generated method stub
		try {
			double prixMontantTotalTTC = 0;
			for(int i = 0; i<this.listProduits.size(); i++) {
				prixMontantTotalTTC = this.listProduits.get(i).getPrixStockTTC() + prixMontantTotalTTC;
			}
			return (double)Math.round(prixMontantTotalTTC * 100) / 100 ;
		}
		catch(Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	public String toString() {
		final NumberFormat instance = NumberFormat.getNumberInstance();
		instance.setMinimumFractionDigits(2);
		instance.setMaximumFractionDigits(2);
		instance.setGroupingUsed(false);
		String Produit="Catalogue : " + this.nom +"\n";
		for( int  i = 0 ; i < this.listProduits.size(); i++) {
			Produit = Produit+this.listProduits.get(i).toString();
		}
		Produit = Produit + "\nMontant total TTC du stock : " + instance.format(this.getMontantTotalTTC()) + " €";
		return Produit;
	}

	@Override
	public void clear() {
		this.listProduits.clear();
	}

    @Override
    public String getNom() {
        return this.nom;
    }

	@Override
	public int getNbProduit() {
		return listProduits.size();
	}

	public void setId(int id) {
		this.id = id;
	}

}
