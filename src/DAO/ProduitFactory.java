package DAO;

public class ProduitFactory {

    protected ProduitFactory(){}

    public static I_ProduitDAO createProduit(){
        return new ProduitDAO();
    }
}
