/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Alexis Jalabert
 */
public class Ligne {
    private int commande;
    private int produit;
    private int quantite;
    
    public Ligne(int commande, int produit, int quantite) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
    }
}
