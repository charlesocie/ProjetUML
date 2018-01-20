package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interface_class.I_Produit;
import interface_class.Produit;

public class ProduitDAO implements I_ProduitDAO {

	private Statement st = null;
	private  Connection cn;
	private ResultSet rs;

	public ProduitDAO(Connection cn) {
		this.cn = cn;
		try {
			st = this.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public boolean create(I_Produit produit) {
		try {
			String sql = "call nouveauProduitObjet(" + String.valueOf(produit.getQuantite()) + ", '" + produit.getNom() + "', " +  String.valueOf(produit.getPrixUnitaireHT()) + "," + String.valueOf(produit.getidcat()) + ")";
			return (st.executeUpdate(sql) != 0);
		}
		catch(SQLException e) {
			return false;
		}
	}

	public boolean supprimer(I_Produit produit, int id){
		try{
			String sql = "DELETE FROM ProduitObjet WHERE nom = '"+ produit.getNom() +"' AND idcat = " + String.valueOf(id) ;
			return (st.executeUpdate(sql) != 0);
		}
		catch(SQLException e) {
			return false;
		}
	}

	public boolean gestionStockProduit(I_Produit produit, int id){
		try{
			String sql = "UPDATE ProduitObjet SET quantitesstock = " + String.valueOf(produit.getQuantite()) + " WHERE nom = '" + produit.getNom() + "'  AND idcat = " + String.valueOf(id) ;
			System.out.println(sql);
			return (st.executeUpdate(sql) != 0);
		}
		catch(SQLException e) {
			return false;
		}
	}


	
	/*public int compterLigne(){
		try {
			String sql = "SELECT COUNT(*) FROM Produit";
			rs = st.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		}
		catch(SQLException e) {
			return 0;
		}
	}
	
	public boolean listeProduit(){
		try {
			String sql = "SELECT * FROM Produit";
			return (st.executeUpdate(sql) != 0);
		}
		catch(SQLException e) {
			return false;
		}
	}*/


	public List<I_Produit>findAll(String nomcat){
		try {
			String sql = "SELECT * FROM ProduitObjet NATURAL JOIN CatalogueObjet where nomcat = '" + nomcat + "'";
			List<I_Produit> listeproduit = new ArrayList<I_Produit>();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Produit p = new Produit(rs.getString(4),  rs.getDouble(5), rs.getInt(3));
				p.setidcat(rs.getInt(1));
				listeproduit.add(p);
			}

			return listeproduit;
		}
		catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	/*public String getNomProduits(int i) {
		try {
			String sql = "SELECT Nom FROM Produit WHERE idproduit = "+ i;
			rs = st.executeQuery(sql);
			rs.next();
			return rs.getString(1);
		}
		catch(SQLException e) {
			System.out.println(e);
			return String.valueOf(e);
		}
	}

	public int getStockProduit(int i) {
		try {
			String sql = "SELECT quantitestock FROM Produit WHERE idproduit = "+ i;
			rs = st.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		}
		catch(SQLException e) {
			return 0;
		}		
	}

	public double getPrixProduit(int i) {
		try {
			String sql = "SELECT prixunitaireht FROM Produit WHERE idproduit = "+ i;
			rs = st.executeQuery(sql);
			rs.next();
			return rs.getDouble(1);
		}
		catch(SQLException e) {
			return 0;
		}	
	}*/
	
}