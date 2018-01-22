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
    public boolean create(String  catalogue) {
        try {
            String sql = "call nouveauCatalogueObjet('" + catalogue + "')";
            return (st.executeUpdate(sql) != 0);
        }
        catch(SQLException e) {
            return false;
        }
    }

    @Override
    public boolean supprimer(String catalogue) {
        try{
            String sql = "call proc_supp_prod('" + this.getid(catalogue) + "')";
            st.executeUpdate(sql);
            sql ="DELETE FROM CatalogueObjet WHERE nomcat = '"+ catalogue +"'" ;
            System.out.println(sql);
            return (st.executeUpdate(sql) != 0);
        }
        catch(SQLException e) {
            return false;
        }
    }

    @Override
    public List<String> findAll(boolean avecProduit) {
        try {
            String sql = "SELECT * FROM CatalogueObjet order by nomcat ";
            List<String> listecat = new ArrayList<String >();
            rs = st.executeQuery(sql);
            String cat;
            while(rs.next()){
                if (avecProduit == true) {
                   cat = rs.getString(2) + " : " + rs.getInt(3) + " produits";
                }
                else
                    cat = rs.getString(2);
                listecat.add(cat);
            }

            return listecat;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int getid(String nom) {
        try {
            String sql = "SELECT idcat FROM CatalogueObjet WHERE nomcat = '" + nom + "'";
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        }
        catch(SQLException e) {
            return 0;
        }
    }

}
