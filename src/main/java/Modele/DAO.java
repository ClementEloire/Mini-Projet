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
        
        
        
        public List<Categorie> listeDeCategorie() throws SQLException {
		List<Categorie> result = new LinkedList<>(); // Liste vIde

		String sql = "SELECT * FROM Categorie";
		try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) { // Tant qu'il y a des enregistrements
					// On récupère les champs nécessaires de l'enregistrement courant
					String libelle = rs.getString("LIBELLE");
                                        int code = rs.getInt("CODE");
                                        String desc = rs.getString("DESCRIPTION");
					// On l'ajoute à la liste des résultats
                                        Categorie c = new Categorie(code,libelle,desc);
					result.add(c);
				}
			}
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new SQLException(ex.getMessage());
		}

		return result;

	}
        
        
        public List<Produit> listeDeProduit(int categorie) throws SQLException{
            List<Produit> result = new LinkedList<>();
            
            
            String sql = "SELECT * FROM Produit Where categorie = ?";
            try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, categorie);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) { // Tant qu'il y a des enregistrements
					// On récupère les champs nécessaires de l'enregistrement courant
					int ref = rs.getInt("REFERENCE");
                                        String nom = rs.getString("NOM");
                                        int fournisseur = rs.getInt("FOURNISSEUR");
                                        String quantitéParUnite = rs.getString("QUANTITE_PAR_UNITE");
                                        float prixUnitaire = rs.getFloat("PRIX_UNITAIRE");
                                        int uniteEnStock = rs.getInt("UNITES_EN_STOCK");
                                        int uniteCommander = rs.getInt("UNITES_COMMANDEES");
                                        int niveauDeReappro = rs.getInt("NIVEAU_DE_REAPPRO");
                                        int indispo = rs.getInt("INDISPONIBLE");
					// On l'ajoute à la liste des résultats
                                        Produit p = new Produit(ref,nom, fournisseur,quantitéParUnite,prixUnitaire, uniteEnStock,uniteCommander,niveauDeReappro,indispo);
					result.add(p);
                        }
                    }
            }catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new SQLException(ex.getMessage());
		}
            return result;
        }
        
        
        /**
         * On regarde si les paramètres passer existent dans la table client
         * @param login champ Contact de la table Client
         * @param mdp champ Code de la table Client
         * @return TRUE si existant, FALSE sinon
         * @throws Exception 
         */
        public boolean loginClient(String login, String mdp) throws Exception {
            boolean log = false;
            String sql = "SELECT * FROM Client Where Code = ? AND Contact = ?";
            try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, mdp);
                stmt.setString(2, login);
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        log = true;
                    }
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }
            return log;
           
        } 
        
        public int deleteProduit(int code) throws SQLException {
		int result = 0;
		String sql = "DELETE FROM PRODUIT WHERE Reference = ?";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, code);
			result = stmt.executeUpdate();
		}
		return result;
	}
        
        /**
         * Récupère toutes les informations du client
         * @param code code du client
         * @return toutes les informations du client sous la forme d'un objet Client
         * @throws Exception 
         */
        public Client infoClient (String code) throws Exception {
            Client client = null;
            String sql = "SELECT * FROM Client Where Code = ?";
            try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, code);
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
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
        
        /**
         * Modifie toutes les informations d'un client
         * @param client toutes les informations du client à update
         */
        public void uptadeClient(Client client) {
            String sql = "UPDATE Client "
                    + "SET Societe = ?, Contact = ?, ";
        }
}
