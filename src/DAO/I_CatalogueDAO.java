package DAO;

import interface_class.I_Catalogue;

import java.util.List;

public interface I_CatalogueDAO {

    public abstract boolean create(I_Catalogue catalogue);

    public abstract boolean supprimer(I_Catalogue catalogue);

    public abstract List<I_Catalogue> findAll();
}
