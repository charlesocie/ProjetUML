package DAO;

import interface_class.I_Catalogue;

import java.util.ArrayList;
import java.util.List;

public class CatalogueDAOAdapteur implements I_CatalogueDAO {

    private CatalogueDAO_XML dao;
    private String cat;

    public CatalogueDAOAdapteur(){
        dao = new CatalogueDAO_XML();
    }

    @Override
    public boolean create(String catalogue) {
        this.cat = catalogue;
        return dao.creerCatalogue(catalogue);
    }

    @Override
    public boolean supprimer(String catalogue) {
        return dao.supprimer(catalogue);
    }

    @Override
    public List<String> findAll(boolean avecProduit) {
        List<String> nomcat = new ArrayList<String>();
        if(avecProduit == true){
            nomcat = dao.lireTousAvecProduit();
        }
        else {
            nomcat = dao.lireTous();
        }
        return nomcat;
    }

    @Override
    public int getid(String nom) {
        return 0;
    }
}
