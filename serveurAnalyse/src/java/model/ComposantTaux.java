/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author manohisoa
 */
public class ComposantTaux implements Serializable {
    private String composant;
    private Double valeur; 

    public ComposantTaux() {
    }

    public ComposantTaux(String composant, Double valeur) {
        this.composant = composant;
        this.valeur = valeur;
    }

    public String getComposant() {
        return composant;
    }

    public void setComposant(String composant) {
        this.composant = composant;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    
}
