package DAO;

import interface_class.I_Catalogue;

import java.util.List;

public interface I_CatalogueDAO {

    public abstract boolean create(String catalogue);

    public abstract boolean supprimer(String catalogue);

    public abstract List<String> findAll(boolean avecProduit);

    public abstract int getid(String nom);
}
