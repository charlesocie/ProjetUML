package controleurs;

import DAO.*;
import interface_class.I_Catalogue;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class ControleurCatalogue {

    private  I_Factory factory;
    private I_CatalogueDAO listCat;

    public ControleurCatalogue(){
        factory = SQLFactory.getInstance();
        listCat =  factory.createCatalogue();
    }

    public String[] listeCatalogueAvecouSansProduit(boolean avecProduit){
        List<String> toutLesCat = listCat.findAll(avecProduit);
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

    public int getidCat(String nom) {
        return listCat.getid(nom);
    }

    public I_Factory getFactory() {
        return factory;
    }
}
