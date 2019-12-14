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
public class Commande {
    private int commande;
    private double montantTot;
    
    public Commande(int commande, double montantTot){
        this.commande = commande;
        this.montantTot = montantTot;
    }
}
