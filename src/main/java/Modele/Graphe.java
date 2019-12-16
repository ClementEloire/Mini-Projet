/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Clément Eloire
 */
public class Graphe {
    
    private String choix;
    private float chiffre;
    
    public Graphe(String choix, float chiffre){
        this.choix = choix;
        this.chiffre=chiffre;
    }
    
    public String getChoix(){
        return this.choix;
    }
    public float getChiffre(){
        return this.chiffre;
    }
    public void addChiffre(float chiffre){
        this.chiffre += chiffre;
    }
}
