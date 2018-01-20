package DAO;

import interface_class.I_Produit;

import java.util.List;

public interface I_ProduitDAO {

    public abstract boolean create(I_Produit produit);

    public abstract boolean supprimer(I_Produit produit);

    public abstract boolean gestionStockProduit(I_Produit produit);

    //public abstract boolean listeProduit();

    public abstract List<I_Produit> findAll(int idcat);

    /*public abstract String getNomProduits(int i);

    public abstract int getStockProduit(int i);

    public abstract double getPrixProduit(int i);*/
}
