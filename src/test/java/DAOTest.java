/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Modele.*;
import static Modele.DataSourceFactory.getDataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Ignore;


/**
 *
 * @author Alexis Jalabert
 */
public class DAOTest {
  
    
    private DAO dao; // L'objet à tester
    private DataSource myDataSource; // La source de données à utiliser
    @Before
    public void setUp() {
        myDataSource = DataSourceFactory.getDataSource();
        dao = new DAO(myDataSource); 
    }
    
    /*
    @Test
    public void testInfoClient() throws Exception {
        Client client = dao.infoClient("ALFKI");
        Client client2 = new Client("ALFKI", "Alfreds Futterkiste", "Maria Anders", "Représentant(e)", "Obere Str. 57", "Berlin", null, "12209     ", "Allemagne", "030-0074321", "030-0076545");
        assertEquals(client.getCode(),client2.getCode());
        assertEquals(client.getSociete(),client2.getSociete());
        assertEquals(client.getFonction(),client2.getFonction());
        assertEquals(client.getAdresse(),client2.getAdresse());
        assertEquals(client.getVille(),client2.getVille());
        assertEquals(client.getRegion(),client2.getRegion());
        assertEquals(client.getCodePostal(),client2.getCodePostal());
        assertEquals(client.getPays(),client2.getPays());
        assertEquals(client.getTel(),client2.getTel());
        assertEquals(client.getFax(),client2.getFax());
    }*/
    
    @Test
    public void testUpdateClient() throws Exception {
         Client client = new Client( "RICAR",  "Alexis Company", "Maria Anders", "Représentant(e)", "Obere Str. 57", "Frankfurt", null, "12209     ", "Allemagne", "030-0074321", "030-0076545");
         dao.updateClient(client);
         client = dao.infoClient("RICAR");
         System.out.println(client.getAdresse());// TEST VISUEL
    }
    
    @Test
    public void testListeCategorie() throws SQLException{
        List<Categorie> listeCat = dao.listeDeCategorie();
        assertEquals(listeCat.size(),8);  //Doit renvoyer 8 catégorie
    }
    
    @Test
    public void testListeProduit() throws SQLException {
        List<Produit> listeprod = dao.listeDeProduit(1);
        //assertEquals(listeprod.size(),12); 
    }
    
    @Test
	public void testDeleteProduit () throws SQLException {
            int code = 78;
            Produit produit = new Produit( 78,"Fanta",1,1,"6 x Canette (33cl)" ,6.75F, 30, 10,3,0);
            dao.addProduit(produit);
            assertEquals(1, dao.deleteProduit(code));
	}
     
    public void testUpdateProduit() throws Exception {
         Produit produit = new Produit( 66,"Doctor Pepper",1,1,"6 x Canette (33cl)" ,6.75F, 30, 10,3,0);
         assertEquals(1, dao.updateProduit(produit));
    }
    
    @Test
    public void testAddProduit() throws Exception {
         Produit produit = new Produit( 99,"Coca Cola",1,1,"6 x Canette (33cl)" ,6.75F, 30, 10,3,0);
         assertEquals(1, dao.addProduit(produit));
         dao.deleteProduit(99);
    }
    
    @Test
    public void testLoginClient() throws Exception {
        String nom1 = "Maria Anders";
        String mdp1 = "ALFKI";
        String nom2 = "Alexis Jalabert";
        String mdp2 = "JAJAJ";
        assertEquals(dao.loginClient(nom1, mdp1), true);
        assertEquals(dao.loginClient(nom2, mdp2), false);
    }
    
    
    @Test
    public void testListeLigne() throws Exception {
        List<Ligne> ligne = new LinkedList<>();
        ligne.add(new Ligne(10361, "Chartreuse verte",54, 90.0 ));
        ligne.add(new Ligne(10361, "Camembert Pierrot", 55, 170.0));
        List<Ligne> ligne2 = dao.listeLigne(10361);
        for(int i = 0 ; i < ligne.size() ; i++){
            Ligne lign = ligne.get(i);
            Ligne lign2 = ligne2.get(i);
            assertEquals(lign.getProduit(), lign2.getProduit());
            assertEquals(lign.getQuantite(), lign2.getQuantite());
            
        }
    }
    
    @Test
    public void testListeCommande() throws Exception {
        int[] listeCom = new int[4];
        List<Commande> liste = dao.listeCommande("ALFKI");
        listeCom[0] = 10702;
        listeCom[1] = 10835;
        listeCom[2] = 10952;
        listeCom[3] = 11011;
        for(int i = 0 ; i < listeCom.length ; i++) {
            Commande com = liste.get(i);
            assertEquals(listeCom[i], com.getCommande());
        }
       
    }
    
    @Test
    public void testInfoProduit() throws Exception {
        ProduitPanier prod1 = new ProduitPanier(59, "Raclette Courdavault", 275.0, 15);
        ProduitPanier prod2 = dao.infoProduit(59, 15);
        assertEquals(prod1.getRef(), prod2.getRef());
        assertEquals(prod1.getNom(), prod2.getNom());
        assertEquals(prod1.getQuantite(), prod2.getQuantite());
    }
    
    
    @Test
    public void testCreationCom() throws Exception {
        Client client = dao.infoClient("QUICK");
        ProduitPanier prod1 = dao.infoProduit(59,15);
        ProduitPanier prod2 = dao.infoProduit(2,28);
        PanierClient panier = new PanierClient(client);
        panier.setProduitPanier(prod1);
        panier.setProduitPanier(prod2);
        int expected = 3;
        assertEquals(expected, dao.creationCom(panier));
    }
    
    @Test
    public void testgraphPays() throws SQLException{
        HashMap<String,Float> res = dao.graphePays(null, null);
        assertEquals(21,res.size());
    }
    
    @Test
    public void testgraphCat() throws SQLException{
        HashMap<String,Float> res = dao.grapheCategorie(null, null);
        assertEquals(8,res.size());
    }
    
    @Test
    public void testgraphClient() throws SQLException{
        HashMap<String,Float> res = dao.grapheClient(null, null);
        assertEquals(89,res.size());
    }
    
    
    
}