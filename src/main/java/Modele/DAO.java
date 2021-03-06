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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        
        
        public List<Produit> listeDeProduit(int select) throws SQLException{
            List<Produit> result = new LinkedList<>();
            
            
            String sql = "SELECT * FROM Produit Where categorie = ?";
            try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, select);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) { // Tant qu'il y a des enregistrements
					// On récupère les champs nécessaires de l'enregistrement courant
					int ref = rs.getInt("REFERENCE");
                                        String nom = rs.getString("NOM");
                                        int fournisseur = rs.getInt("FOURNISSEUR");
                                        int categorie = rs.getInt("Categorie");
                                        String quantitéParUnite = rs.getString("QUANTITE_PAR_UNITE");
                                        float prixUnitaire = rs.getFloat("PRIX_UNITAIRE");
                                        int uniteEnStock = rs.getInt("UNITES_EN_STOCK");
                                        int uniteCommander = rs.getInt("UNITES_COMMANDEES");
                                        int niveauDeReappro = rs.getInt("NIVEAU_DE_REAPPRO");
                                        int indispo = rs.getInt("INDISPONIBLE");
					// On l'ajoute à la liste des résultats
                                        Produit p = new Produit(ref,nom, fournisseur,categorie,quantitéParUnite,prixUnitaire, uniteEnStock,uniteCommander,niveauDeReappro,indispo);
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
         * @return le nombre d'enregistrement changer
         * @throws Exception 
         */
        public int updateClient(Client client) throws Exception {
            int upd = 0;
            String sql = "UPDATE Client "
                    + "SET Societe = ?, Contact = ?, Fonction = ?, Adresse = ?, "
                    + "Ville = ?, Region = ?, Code_postal = ?, Pays = ?, "
                    + "Telephone = ?, Fax = ?"
                    + "WHERE Code = ?";
            
             try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
                 stmt.setString(1, client.getSociete());
                 stmt.setString(2, client.getContact());
                 stmt.setString(3, client.getFonction());
                 stmt.setString(4, client.getAdresse());
                 stmt.setString(5, client.getVille());
                 stmt.setString(6, client.getRegion());
                 stmt.setString(7, client.getCodePostal());
                 stmt.setString(8, client.getPays());
                 stmt.setString(9, client.getTel());
                 stmt.setString(10, client.getFax());
                 stmt.setString(11, client.getCode());
                 
                upd = stmt.executeUpdate();
             } catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }
            
             return upd;
        }
        
        public int updateProduit (Produit produit) throws SQLException{
            int result = 0;
		String sql = "UPDATE PRODUIT SET NOM = ? , FOURNISSEUR = ? ,"
                        + "Quantite_par_unite = ? , Prix_unitaire = ?, Unites_en_Stock = ?,"
                        + "Unites_Commandees =? , Niveau_de_reappro =? , Indisponible = ? WHERE Reference = ?";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, produit.getNom());
                        stmt.setInt(2,produit.getFournisseur());
                        stmt.setString(3,produit.getQuantiteParUnite());
                        stmt.setFloat(4,produit.getprixUnitaire());
                        stmt.setInt(5,produit.getUniteEnStock());
                        stmt.setInt(6,produit.getUniteCommande());
                        stmt.setInt(7,produit.getNiveauDeReaprovi());
                        stmt.setInt(8,produit.getIndisponible());
                        stmt.setInt(9,produit.getReference());
			result = stmt.executeUpdate();
		}
		return result;
        }
        
        public int addProduit (Produit produit) throws SQLException{
            int result = 0;
            String sql = "INSERT INTO PRODUIT VALUES (?,?,?,?,?,?,?,?,?,?)";
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setInt(1,produit.getReference());
			stmt.setString(2, produit.getNom());
                        stmt.setInt(3,produit.getFournisseur());
                        stmt.setInt(4, produit.getCategorie());
                        stmt.setString(5,produit.getQuantiteParUnite());
                        stmt.setFloat(6,produit.getprixUnitaire());
                        stmt.setInt(7,produit.getUniteEnStock());
                        stmt.setInt(8,produit.getUniteCommande());
                        stmt.setInt(9,produit.getNiveauDeReaprovi());
                        stmt.setInt(10,produit.getIndisponible());
			result = stmt.executeUpdate();
		}
		return result;
        }
        
        public List<Ligne> listeLigne(int commande) throws Exception {
            List<Ligne> liste = new LinkedList<>();
            String sql = "SELECT Nom, Quantite, Prix_unitaire FROM Ligne JOIN Produit ON Produit = Reference WHERE Commande = ?";
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, commande);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String produit = rs.getString("Nom");
                    int quantite = rs.getInt("Quantite");
                    double prix = rs.getDouble("Prix_unitaire");
                    Ligne ligne = new Ligne(commande, produit, quantite, prix);
                    
                    liste.add(ligne);
                }
            } catch(Exception ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }
            
            return liste;
        }
        
        public List<Commande> listeCommande(String code) throws Exception {
            String sql = "SELECT Numero FROM Commande WHERE Client = ?";
            List<Commande> liste = new LinkedList<>();
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, code);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Commande com = new Commande(rs.getInt("Numero"));
                    liste.add(com);
                }
            } catch(Exception ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }
            
            return liste;
        }
        
        public ProduitPanier infoProduit(int refProd, int qte) throws Exception {
            String sql = "SELECT Nom, Prix_unitaire FROM Produit WHERE Reference = ?";
            ProduitPanier panier = null;
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, refProd);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    panier = new ProduitPanier(refProd, rs.getString("Nom"), rs.getDouble("Prix_unitaire"), qte);
                }
            }catch(Exception ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }
            
            return panier;
        }
        
        public int creationCom(PanierClient panier) throws Exception {
            int result = 0;
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            
            Date aujourdhui = new Date();
            String date = formater.format(aujourdhui);
            String sql1 = "INSERT INTO Commande VALUES (?, ?,?, ?, 0.0, ?, ?, ?, ?, ?, ?, 0.0)";
            
            String sql2 = "INSERT INTO Ligne VALUES (?, ?, ?)";
            
            
            String sql3 = "SELECT Numero FROM Commande ORDER BY Numero DESC";
            
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    PreparedStatement stmt2 = connection.prepareStatement(sql2);
                    PreparedStatement stmt3 = connection.prepareStatement(sql3)) {
                try {
                    ResultSet rs3 = stmt3.executeQuery();
                    int numero = 0;
                    if(rs3.next()) {
                        numero = rs3.getInt("Numero")+1;
                    }

                    Client client = panier.getClient();
                    stmt1.setInt(1, numero);
                    stmt1.setString(2, client.getCode());
                    stmt1.setString(3,date);
                    stmt1.setString(4,date);
                    stmt1.setString(5, client.getSociete());
                    stmt1.setString(6, client.getAdresse());
                    stmt1.setString(7, client.getVille());
                    stmt1.setString(8, client.getRegion());
                    stmt1.setString(9, client.getCodePostal());
                    stmt1.setString(10, client.getPays());

                    result += stmt1.executeUpdate();
                    List<ProduitPanier> produitListe = panier.getProduitPanier();
                    for(ProduitPanier prod : produitListe) {
                            stmt2.setInt(1, numero);
                            stmt2.setInt(2,prod.getRef());
                            stmt2.setInt(3,prod.getQuantite());
                            result += stmt2.executeUpdate();
                    }
                }catch(Exception ex) {
                    throw ex;
                }
                
                
            }catch(Exception ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }
            
            return result;
            
        }
        
        /*public List<Graphe> graphePays(String dateDebut, String dateFin) throws SQLException{
        String sql1 ="SELECT SUM(QUANTITE*PRIX_UNITAIRE) AS TOTAL,COMMANDE.PAYS_LIVRAISON\n" +
                                "FROM PRODUIT INNER JOIN LIGNE ON LIGNE.PRODUIT = PRODUIT.REFERENCE\n" +
                                "INNER JOIN CATEGORIE ON PRODUIT.CATEGORIE=CATEGORIE.CODE\n" +
                                "INNER JOIN COMMANDE ON LIGNE.COMMANDE=COMMANDE.NUMERO\n" +
                                "WHERE COMMANDE.SAISIE_LE BETWEEN ? AND ? " +
                                "GROUP BY COMMANDE.PAYS_LIVRAISON ORDER BY TOTAL";
        List<Graphe> result = new LinkedList<>();
       
        try(
                 Connection connection = myDataSource.getConnection();
                 PreparedStatement stmt1 = connection.prepareStatement(sql1 )  ;
               ){
            if(dateDebut == null || dateFin==null){
                stmt1.setString(1, "1994-08-04");
                stmt1.setString(2, "1996-06-05");
            }else{
                stmt1.setString(1, dateDebut);
                stmt1.setString(2, dateFin);
            }
            
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next()){
            String pays = rs1.getString("PAYS_LIVRAISON");
            float prix = rs1.getFloat("TOTAL");
            result.add(new Graphe(pays, prix));
            }
          }
        return result;
    }   */
        
        public HashMap<String,Float> graphePays(String dateDebut, String dateFin) throws SQLException{
        String sql1 ="SELECT SUM(QUANTITE*PRIX_UNITAIRE) AS TOTAL,COMMANDE.PAYS_LIVRAISON\n" +
                                "FROM PRODUIT INNER JOIN LIGNE ON LIGNE.PRODUIT = PRODUIT.REFERENCE\n" +
                                "INNER JOIN CATEGORIE ON PRODUIT.CATEGORIE=CATEGORIE.CODE\n" +
                                "INNER JOIN COMMANDE ON LIGNE.COMMANDE=COMMANDE.NUMERO\n" +
                                "WHERE COMMANDE.SAISIE_LE BETWEEN ? AND ? " +
                                "GROUP BY COMMANDE.PAYS_LIVRAISON ORDER BY TOTAL";
        HashMap<String,Float> result = new HashMap<>();
       
        try(
                 Connection connection = myDataSource.getConnection();
                 PreparedStatement stmt1 = connection.prepareStatement(sql1 )  ;
               ){
            if(dateDebut == null || dateFin==null){
                stmt1.setString(1, "1994-08-04");
                stmt1.setString(2, "1996-06-05");
            }else{
                stmt1.setString(1, dateDebut);
                stmt1.setString(2, dateFin);
            }
            
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next()){
            String pays = rs1.getString("PAYS_LIVRAISON");
            float prix = rs1.getFloat("TOTAL");
            result.put(pays, prix);
            }
          }
        return result;
    }  
        
        
        public HashMap<String,Float> grapheCategorie(String dateDebut, String dateFin) throws SQLException{
        String sql1 ="SELECT SUM(QUANTITE*PRIX_UNITAIRE) AS TOTAL,LIBELLE\n" +
                                "FROM PRODUIT INNER JOIN LIGNE ON LIGNE.PRODUIT = PRODUIT.REFERENCE\n" +
                                "INNER JOIN CATEGORIE ON PRODUIT.CATEGORIE=CATEGORIE.CODE\n" +
                                "INNER JOIN COMMANDE ON LIGNE.COMMANDE=COMMANDE.NUMERO\n" +
                                "WHERE COMMANDE.SAISIE_LE BETWEEN ? AND ? " +
                                "GROUP BY CATEGORIE.LIBELLE ORDER BY TOTAL";
        HashMap<String,Float> result = new HashMap<>();
       
        try(
                 Connection connection = myDataSource.getConnection();
                 PreparedStatement stmt1 = connection.prepareStatement(sql1 )  ;
               ){
            if(dateDebut == null || dateFin==null){
                stmt1.setString(1, "1994-08-04");
                stmt1.setString(2, "1996-06-05");
            }else{
                stmt1.setString(1, dateDebut);
                stmt1.setString(2, dateFin);
            }
            
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next()){
            String pays = rs1.getString("LIBELLE");
            float prix = rs1.getFloat("TOTAL");
            result.put(pays, prix);
            }
          }
        return result;
    }  
         public HashMap<String,Float> grapheClient(String dateDebut, String dateFin) throws SQLException{
        String sql1 ="SELECT SUM(QUANTITE*PRIX_UNITAIRE) AS TOTAL,CLIENT.SOCIETE\n" +
                                "FROM PRODUIT INNER JOIN LIGNE ON LIGNE.PRODUIT = PRODUIT.REFERENCE\n" +
                                "INNER JOIN CATEGORIE ON PRODUIT.CATEGORIE=CATEGORIE.CODE\n" +
                                "INNER JOIN COMMANDE ON LIGNE.COMMANDE=COMMANDE.NUMERO\n" +
                                "INNER JOIN CLIENT ON COMMANDE.CLIENT=CLIENT.CODE\n"+
                                "WHERE COMMANDE.SAISIE_LE BETWEEN ? AND ? " +
                                "GROUP BY CLIENT.SOCIETE ORDER BY TOTAL";
        HashMap<String,Float> result = new HashMap<>();
       
        try(
                 Connection connection = myDataSource.getConnection();
                 PreparedStatement stmt1 = connection.prepareStatement(sql1 )  ;
               ){
            if(dateDebut == null || dateFin==null){
                stmt1.setString(1, "1994-08-04");
                stmt1.setString(2, "1996-06-05");
            }else{
                stmt1.setString(1, dateDebut);
                stmt1.setString(2, dateFin);
            }
            
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next()){
            String pays = rs1.getString("SOCIETE");
            float prix = rs1.getFloat("TOTAL");
            result.put(pays, prix);
            }
          }
        return result;
    }  
}
