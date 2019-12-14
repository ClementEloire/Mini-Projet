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
public class ProduitPanier {
    private int ref;
    private String nom;
    private double prix;
    private int quantite;
    
    public ProduitPanier(int ref, String nom, double prix, int quantite) {
        this.ref = ref;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }
}
