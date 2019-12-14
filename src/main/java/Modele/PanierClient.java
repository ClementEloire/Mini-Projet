/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alexis Jalabert
 */
public class PanierClient {
    private Client client;
    private List<ProduitPanier> produit;
    
    public PanierClient(Client client) {
        this.client = client;
        this.produit = new LinkedList<>();
    }
    
    public Client getClient() {
        return this.client;
    }
    
    public List<ProduitPanier> getProduitPanier() {
        return this.produit;
    }
    
    public void setProduitPanier(ProduitPanier prod) {
        this.produit.add(prod);
    }
    
    public void deleteProduitPanier(int index) {
        this.produit.remove(index);
    }
    
}
