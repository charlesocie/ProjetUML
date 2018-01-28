package DAO;

import interface_class.I_Produit;

import java.util.ArrayList;
import java.util.List;

public class ProduitDAOAdapter  implements I_ProduitDAO {

    private ProduitDAO_XML dao;
    private String nomcat;

    public ProduitDAOAdapter(){
        dao = new ProduitDAO_XML();
    }

    @Override
    public boolean create(I_Produit produit) {
        produit.setnomcat(this.nomcat);
        return dao.creerProduit(produit);
    }

    @Override
    public boolean supprimer(I_Produit produit, int id) {
        produit.setnomcat(this.nomcat);
        return dao.supprimer(produit);
    }

    @Override
    public boolean gestionStockProduit(I_Produit produit, int id) {
        produit.setnomcat(this.nomcat);
        return dao.maj(produit);
    }


    @Override
    public List<I_Produit> findAll(String  nomcat) {
        this.nomcat = nomcat;
        List<I_Produit> l = new ArrayList<I_Produit>();
        l= dao.lireTous(nomcat);
        for(int i = 0; i<l.size() ; i++ ){
            l.get(i).setnomcat(nomcat);
        }
        return l;
    }
}
