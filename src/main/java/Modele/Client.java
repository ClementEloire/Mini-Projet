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
public class Client {
    private String code;
    private String societe;
    private String contact;
    private String fonction;
    private String adresse;
    private String ville;
    private String region;
    private String code_postal;
    private String pays;
    private String tel;
    private String fax;
    
    public Client(String code, String societe, String contact, String fonction, String adresse, String ville, String region, String code_postal, String pays, String tel, String fax) {
        this.code = code;
        this.societe = societe;
        this.contact = contact;
        this.fonction = fonction;
        this.adresse = adresse;
        this.ville = ville;
        this.region = region;
        this.code_postal = code_postal;
        this.pays = pays;
        this.tel = tel;
        this.fax = fax;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getSociete() {
        return this.societe;
    }
    
    public String getContact() {
        return this.contact;
    }
    
    public String getFonction() {
        return this.fonction;
    }
    
    public String getAdresse() {
        return this.adresse;
    }
    
    public String getVille() {
        return this.ville;
    }
    
    public String getRegion() {
        return this.region;
    }
    
    public String getCodePostal() {
        return this.code_postal;
    }
    
    public String getPays() {
        return this.pays;
    }
    
    public String getTel() {
        return this.tel;
    }
    
    public String getFax() {
        return this.fax;
    }
}
