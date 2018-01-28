package DAO;

import interface_class.I_Produit;

import java.util.List;

public interface I_ProduitDAO {

    public abstract boolean create(I_Produit produit);

    public abstract boolean supprimer(I_Produit produit, int id);

    public abstract boolean gestionStockProduit(I_Produit produit, int id);

    public abstract List<I_Produit> findAll(String nomcat);

}
