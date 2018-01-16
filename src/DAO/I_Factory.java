package DAO;

public interface I_Factory {

    public static I_ProduitDAO createProduit() {
        return null;
    }

    public I_CatalogueDAO createCatalogue();
}
