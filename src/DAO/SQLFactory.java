package DAO;

import java.sql.*;

public class SQLFactory implements I_Factory {

    private static Connection cn;


    public SQLFactory(){
        try {
            String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
            String login = "bertrandf";
            String mdp = "1108019010P";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection(url, login, mdp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public I_ProduitDAO createProduit(){
        return new ProduitDAO(cn);
    }

    public I_CatalogueDAO createCatalogue(){
        return  new CatalogueDAO(cn);
    }
}
