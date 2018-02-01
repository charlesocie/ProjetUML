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
	private Connection cn = null;
	private ResultSet rs;

	public ProduitDAO() {
		try {
			String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
			String login = "bertrandf";
			String mdp = "1108019010P";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection(url, login, mdp);
			st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create(I_Produit produit) {
		try {
			String sql = "call nouveauProduit('" + produit.getNom() + "' , " + String.valueOf(produit.getQuantite()) + ", " +  String.valueOf(produit.getPrixUnitaireHT()) + ")";
			return (st.executeUpdate(sql) != 0);
		}
		catch(SQLException e) {
			return false;
		}
	}

	public boolean supprimer(I_Produit produit){
		try{
			String sql = "DELETE FROM Produit WHERE nom = '"+ produit.getNom() +"'" ;
			return (st.executeUpdate(sql) != 0);
		}
		catch(SQLException e) {
			return false;
		}
	}

	public boolean gestionStockProduit(I_Produit produit){
		try{
			String sql = "UPDATE Produit SET quantitestock = " + String.valueOf(produit.getQuantite()) + " WHERE nom = '" + produit.getNom() + "'" ;
			return (st.executeUpdate(sql) != 0);
		}
		catch(SQLException e) {
			return false;
		}
	}


	public List<I_Produit>findAll(){
		try {
			String sql = "SELECT * FROM Produit ";
			List<I_Produit> listeproduit = new ArrayList<I_Produit>();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Produit p = new Produit(rs.getString(3),  rs.getDouble(4), rs.getInt(2));
				listeproduit.add(p);
			}

			return listeproduit;
		}
		catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	}

}