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
    private String produit;
    private int quantite;
    private double montant;
    
    public Ligne(int commande, String produit, int quantite, double montant) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.montant = montant;
    }
    
    public String getProduit() {
        return this.produit;
    }
    
    public int getQuantite() {
        return this.quantite;
    }
    
    public double getMontant() {
        return this.montant;
    }
}
