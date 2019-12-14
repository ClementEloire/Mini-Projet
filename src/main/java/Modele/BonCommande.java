/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.List;

/**
 *
 * @author Alexis Jalabert
 */
public class BonCommande {
    private Commande commande;
    private List<Ligne> ligne;
    
    public BonCommande(Commande commande, List<Ligne> ligne) {
        this.commande = commande;
        this.ligne = ligne;
    }
}
