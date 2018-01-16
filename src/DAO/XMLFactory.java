package DAO;

public class XMLFactory implements I_Factory {

    protected XMLFactory(){}

    public static I_ProduitDAO createProduit(){
        return new ProduitDAOAdapter();
    }

    @Override
    public I_CatalogueDAO createCatalogue() {
        return null;
    }
}