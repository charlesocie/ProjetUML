package DAO;

import interface_class.Catalogue;
import interface_class.I_Catalogue;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO_XML {

    private String uri = "/home/charles/IdeaProjects/projet UML/src/Catalogue.xml";
    private Document doc;
    private String uriprod = "/home/charles/IdeaProjects/projet UML/src/Produits.xml";
    private Document doc2;

    public CatalogueDAO_XML() {
        SAXBuilder sdoc = new SAXBuilder();
        try {
            doc = sdoc.build(uri);
            doc2 = sdoc.build(uriprod);
        } catch (Exception e) {
            System.out.println("erreur construction arbre JDOM");
        }
    }

    public boolean creerCatalogue(String c) {
        try {
            Element root = doc.getRootElement();
            Element cat = new Element("catalogue");
            cat.setAttribute("nom", c);
            Element nbProduits = new Element("nbproduit");
            cat.addContent(nbProduits.setText(String.valueOf(0)));
            root.addContent(cat);
            return sauvegarde();
        } catch (Exception e) {
            System.out.println("erreur creer catalogue");
            return false;
        }
    }

    public boolean supprimer(String c) {
        try {
            Element root = doc.getRootElement();
            Element catalogue = chercheCatalogue(c);
            if (catalogue != null) {
                supprimerTousLesProduit(c);
                root.removeContent(catalogue);
                return sauvegarde();
            } else
                return false;
        } catch (Exception e) {
            System.out.println("erreur supprimer Catalogue");
            return false;
        }
    }

    private boolean supprimerTousLesProduit(String c) {
        try {
            Element root = doc2.getRootElement();
            Element produit = chercheProduit(c);
            while (produit != null) {
                if (produit != null) {
                    root.removeContent(produit);
                    produit = chercheProduit(c);
                }
            }
            return sauvegardeprod();
        } catch (Exception e) {
            System.out.println(e+"erreur supprimer produit du catalogue");
            return false;
        }
    }

    public List<String> lireTous() {

        List<String> l = new ArrayList<String>();
        try {
            Element root = doc.getRootElement();
            List<Element> lcat = root.getChildren("catalogue");
            for (Element cat : lcat) {
                String nomC = cat.getAttributeValue("nom");
                l.add(nomC);
            }
        } catch (Exception e) {
            System.out.println("erreur lireTous tous les Catalogues");
        }
        return l;
    }

    public List<String> lireTousAvecProduit() {

        List<String> l = new ArrayList<String>();
        try {
            Element root = doc.getRootElement();
            List<Element> lcat = root.getChildren("catalogue");
            for (Element cat : lcat) {
                String nomC = cat.getAttributeValue("nom");
                nomC = nomC + ": " + Integer.parseInt(cat.getChild("nbproduit").getText());
                l.add(nomC);
            }
        } catch (Exception e) {
            System.out.println("erreur lireTous tous les Catalogues");
        }
        return l;
    }

    private boolean sauvegarde() {
        System.out.println("Sauvegarde");
        XMLOutputter out = new XMLOutputter();
        try {
            out.output(doc, new PrintWriter(uri));
            return true;
        } catch (Exception e) {
            System.out.println("erreur sauvegarde dans fichier XML");
            return false;
        }
    }

    private boolean sauvegardeprod() {
        System.out.println("Sauvegarde");
        XMLOutputter out = new XMLOutputter();
        try {
            out.output(doc2, new PrintWriter(uriprod));
            return true;
        } catch (Exception e) {
            System.out.println("erreur sauvegarde dans fichier XML");
            return false;
        }
    }

    private Element chercheProduit(String nomcat) {
        Element root = doc2.getRootElement();
        List<Element> lProd = root.getChildren("produit");
        int i = 0;
        while (i < lProd.size() && !lProd.get(i).getChild("catalogue").getText().equals(nomcat)) {
            i++;
        }
        if (i < lProd.size())
            return lProd.get(i);
        else
            return null;
    }

    private Element chercheCatalogue(String nom) {
        Element root = doc.getRootElement();
        List<Element> lCat = root.getChildren("catalogue");
        int i = 0;
        while (i < lCat.size() && !lCat.get(i).getAttributeValue("nom").equals(nom))
            i++;
        if (i < lCat.size())
            return lCat.get(i);
        else
            return null;
    }
}
