package controleurs;

import DAO.I_CatalogueDAO;
import DAO.I_ProduitDAO;
import DAO.SQLFactory;
import interface_class.I_Catalogue;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class ControleurCatalogue {

    SQLFactory factory;
    I_CatalogueDAO listCat;

    public String[] listeCatalogueAvecouSansProduit(boolean avecProduit){
        List<String> toutLesCat;
        factory = new SQLFactory();
        listCat =  factory.createCatalogue();
        toutLesCat= listCat.findAll(avecProduit);
        String[] Catalogues = new String[toutLesCat.size()];
        for(int i = 0 ; i<toutLesCat.size(); i++){
            Catalogues[i] =  toutLesCat.get(i);
        }
        return Catalogues;
    }

    public void ajoutCat(String texteAjout) {
        listCat.create(texteAjout);
    }

    public void suppressionCat(String texteSupprime) {
        listCat.supprimer(texteSupprime);
    }
}
