package DAO;

public interface I_Factory {

    public I_ProduitDAO createProduit();

    public I_CatalogueDAO createCatalogue();
}
