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
public class Produit {
    
    private int reference;
    private String nom;
    private int fournisseur;
    private int categorie;
    private String quantiteParUnite;
    private float prixUnitaire;
    private int uniteEnStock;
    private int uniteCommande;
    private int niveauDeReaprovi;
    private int indisponible;
    
    public Produit(int reference,String nom,int fournisseur,int categorie, String quantiteParUnite ,float prixUnitaire,
    int uniteEnStock, int uniteCommande,int niveauDeReaprovi,int indisponible){
        
        this.reference = reference;
        this.nom =nom;
        this.fournisseur =fournisseur;
        this.categorie=categorie;
        this.quantiteParUnite =quantiteParUnite;
        this.prixUnitaire = prixUnitaire;
        this.uniteEnStock=uniteEnStock;
        this.uniteCommande = uniteCommande;
        this.niveauDeReaprovi = niveauDeReaprovi;
        this.indisponible=indisponible;
    }
    
    public int getReference(){
        return this.reference;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public int getFournisseur(){
        return this.fournisseur;
    }
    
    public int getCategorie(){
        return this.categorie;
    }
    
    public String getQuantiteParUnite(){
        return this.quantiteParUnite;
    }
    
    public float getprixUnitaire(){
        return this.prixUnitaire;
    }
    
    public int getUniteEnStock(){
        return this.uniteEnStock;
    }
    
    public int getUniteCommande(){
        return this.uniteCommande;
    }
    
    public int getNiveauDeReaprovi(){
        return this.niveauDeReaprovi;
    }
    
    public int getIndisponible(){
        return this.indisponible;
    }
    
    
    
    
}
