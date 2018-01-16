package DAO;

import interface_class.Catalogue;
import interface_class.I_Catalogue;
import interface_class.I_Produit;
import interface_class.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO implements I_CatalogueDAO{

    private Statement st = null;
    private  Connection cn;
    private ResultSet rs;

    public CatalogueDAO(Connection cn){
        this.cn = cn;
        try {
            st = this.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean create(I_Catalogue catalogue) {
        try {
            String sql = "call nouveauCatalogue('" + catalogue.getNom() + "')";
            return (st.executeUpdate(sql) != 0);
        }
        catch(SQLException e) {
            return false;
        }
    }

    @Override
    public boolean supprimer(I_Catalogue catalogue) {
        try{
            String sql = "DELETE FROM Catalogue WHERE nom = '"+ catalogue.getNom() +"'" ;
            return (st.executeUpdate(sql) != 0);
        }
        catch(SQLException e) {
            return false;
        }
    }

    @Override
    public List<I_Catalogue> findAll() {
        try {
            String sql = "SELECT * FROM Catalogue ";
            List<I_Catalogue> listecat = new ArrayList<I_Catalogue>();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Catalogue cat = new Catalogue(rs.getString(2));
                listecat.add(cat);
            }

            return listecat;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
