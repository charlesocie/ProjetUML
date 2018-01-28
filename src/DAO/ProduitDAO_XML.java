package DAO;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import interface_class.I_Produit;
import interface_class.Produit;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;


public class ProduitDAO_XML {
	private String uri = "/home/charles/IdeaProjects/projet UML/src/Produits.xml";
	private Document doc;
	private String uricat = "/home/charles/IdeaProjects/projet UML/src/Catalogue.xml";
	private Document doc2;

	public ProduitDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
			doc2 = sdoc.build(uricat);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	public boolean creerProduit(I_Produit p) {
		try {
			Element root = doc.getRootElement();
			Element prod = new Element("produit");
			prod.setAttribute("nom", p.getNom());
			Element prix = new Element("prixHT");
			prod.addContent(prix.setText(String.valueOf(p.getPrixUnitaireHT())));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(p.getQuantite())));
			Element cat = new Element("catalogue");
			prod.addContent(cat.setText(String.valueOf(p.getNomCat())));
			root.addContent(prod);
			majcatnbproduit(p.getNomCat(), 1);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	public boolean maj(I_Produit p) {
		try {
			Element prod = chercheProduit(p.getNom(), p.getNomCat());
			if (prod != null) {
				prod.getChild("quantite").setText(String.valueOf(p.getQuantite()));
				return sauvegarde();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}
	}

	public boolean majcatnbproduit(String  c, int maj) {
		try {
			Element catalogue = chercheCatalogue(c);
			if (catalogue != null) {
				int qte = Integer.parseInt(catalogue.getChild("nbproduit").getText());
				catalogue.getChild("nbproduit").setText(String.valueOf(qte + maj));
				return sauvegardecat();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj catalogue");
			return false;
		}
	}

	public boolean supprimer(I_Produit p) {
		try {
			Element root = doc.getRootElement();
			Element prod = chercheProduit(p.getNom(), p.getNomCat());
			if (prod != null) {
				root.removeContent(prod);
				majcatnbproduit(p.getNomCat(), -1);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
	}

	public List<I_Produit> lireTous(String nomcat) {

		List<I_Produit> l = new ArrayList<I_Produit>();
		try {
			Element root = doc.getRootElement();
			List<Element> lProd = root.getChildren("produit");

			for (Element prod : lProd) {
				if(prod.getChild("catalogue").getText().equals(nomcat)) {
					String nomP = prod.getAttributeValue("nom");
					Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
					int qte = Integer.parseInt(prod.getChild("quantite").getText());
					l.add(new Produit(nomP, prix, qte));
				}
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
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

	private boolean sauvegardecat() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc2, new PrintWriter(uricat));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

	private Element chercheProduit(String nom, String nomcat) {
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("produit");
		int i = 0;
		while (i < lProd.size() && !lProd.get(i).getAttributeValue("nom").equals(nom) && !lProd.get(i).getChild("catalogue").getText().equals(nomcat))
			i++;
		if (i < lProd.size())
			return lProd.get(i);
		else
			return null;
	}

	private Element chercheCatalogue(String nom) {
		Element root = doc2.getRootElement();
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
