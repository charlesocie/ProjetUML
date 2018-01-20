package DAO;

import interface_class.I_Produit;

import java.util.List;

public class ProduitDAOAdapter  implements I_ProduitDAO {

    private ProduitDAO_XML dao;

    public ProduitDAOAdapter(){
        dao = new ProduitDAO_XML();
    }

    @Override
    public boolean create(I_Produit produit) {
       return dao.creer(produit);
    }

    @Override
    public boolean supprimer(I_Produit produit, int id) {
        return false;
    }

    @Override
    public boolean gestionStockProduit(I_Produit produit, int id) {
        return false;
    }


    public boolean supprimer(I_Produit produit) {
        return dao.supprimer(produit);
    }


    public boolean gestionStockProduit(I_Produit produit) {
        return dao.maj(produit);
    }

    @Override
    public List<I_Produit> findAll(String  idcat) {
        return null;
    }

    public List<I_Produit> findAll() {
        return dao.lireTous();
    }


}
