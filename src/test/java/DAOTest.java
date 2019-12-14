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
import java.sql.SQLException;
import java.util.List;
import org.junit.Ignore;

/**
 *
 * @author Alexis Jalabert
 */
public class DAOTest {
    
    DAO dao = new DAO(DataSourceFactory.getDataSource());
    
    public DAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testInfoClient() throws Exception {
        Client client = dao.infoClient("ALFKI");
        Client client2 = new Client("ALFKI", "Alfreds Futterkiste", "Maria Anders", "Representant(e)", "Obere Str. 57", "Anderson", "", "12209     ", "Allemagne", "030-0074321", "030-0076545");
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
    }
    
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
        assertEquals(listeprod.size(),12); 
    }
    
    @Test
    @Ignore
	public void testDeleteProduit () throws SQLException {
            Produit produit = new Produit( 78,"Fanta",1,"6 x Canette (33cl)" ,6.75F, 30, 10,3,0);
            assertEquals(1, dao.deleteProduit(78));
	}
     
    public void testUpdateProduit() throws Exception {
         Produit produit = new Produit( 66,"Doctor Pepper",1,"6 x Canette (33cl)" ,6.75F, 30, 10,3,0);
         assertEquals(1, dao.updateProduit(produit));
    }
    public void testAddProduit() throws Exception {
         Produit produit = new Produit( 99,"Coca Cola",1,"6 x Canette (33cl)" ,6.75F, 30, 10,3,0);
         assertEquals(1, dao.updateProduit(produit));
    }
}

