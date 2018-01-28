package DAO;

public class XMLFactory implements I_Factory {

    private  static XMLFactory instance;

    protected XMLFactory(){}

    public I_ProduitDAO createProduit(){
        return new ProduitDAOAdapter();
    }

    @Override
    public I_CatalogueDAO createCatalogue() {
        return new CatalogueDAOAdapteur();
    }

    public synchronized static XMLFactory getInstance(){
        if(instance == null){
            instance = new XMLFactory();
        }
        return instance;
    }
}
