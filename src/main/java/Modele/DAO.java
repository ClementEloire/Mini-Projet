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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {

	protected final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        
        
        public List<String> listeDeCategorie() throws Exception {
		List<String> result = new LinkedList<>(); // Liste vIde

		String sql = "SELECT LIBELLE FROM Categorie";
		try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) { // Tant qu'il y a des enregistrements
					// On récupère les champs nécessaires de l'enregistrement courant
					String libelle = rs.getString("LIBELLE");
					// On l'ajoute à la liste des résultats
					result.add(libelle);
				}
			}
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new Exception(ex.getMessage());
		}

		return result;

	}
        
        /**
         * Récupération du client qui vient de s'authentifier
         * @param login champ Contact de la table Client
         * @param mdp champ Code de la table Client
         * @return toutes les informations du Client qui vient de se login, si aucun client n'est trouver alors renvoie null
         * @throws Exception 
         */
        public Client loginClient(String login, String mdp) throws Exception {
            Client client = null;
            String sql = "SELECT * FROM Client Where Code = ? AND Contact = ?";
            try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, mdp);
                stmt.setString(2, login);
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        String code = rs.getString("Code");
                        String societe = rs.getString("Societe");
                        String contact = rs.getString("Contact");
                        String fonction = rs.getString("Fonction");
                        String adresse = rs.getString("Adresse");
                        String ville = rs.getString("Ville");
                        String region = rs.getString("Region");
                        String code_postal = rs.getString("Code_postal");
                        String pays = rs.getString("Pays");
                        String tel = rs.getString("Telephone");
                        String fax = rs.getString("Fax");

                        client = new Client(code, societe, contact, fonction, adresse, ville, region, code_postal, pays, tel, fax);
                    } else {
                        client = null;
                    }
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }
            return client;
           
        } 
}
