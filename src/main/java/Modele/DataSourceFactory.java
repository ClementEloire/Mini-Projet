/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author pedago
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DataSourceFactory {
        // Pré-chargement du driver, ne devrait pas âtre nécessaire avec des versions plus récentes
        static {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataSourceFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    	public static DataSource getDataSource() throws SQLException {
		org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
		ds.setDatabaseName("Projet");
		ds.setUser("clement");
		ds.setPassword("Projet");
		// The host on which Network Server is running
		ds.setServerName("localhost");
		// port on which Network Server is listening
		ds.setPortNumber(1527);
		return ds;
	}	
    
}